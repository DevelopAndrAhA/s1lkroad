package kg.projectr1.projectr1.domain.admin;

import kg.projectr1.projectr1.config.FileSystemProperties;
import kg.projectr1.projectr1.domain.category.Category;
import kg.projectr1.projectr1.domain.category.CategoryRepository;
import kg.projectr1.projectr1.domain.product.Product;
import kg.projectr1.projectr1.domain.product.ProductRepository;
import kg.projectr1.projectr1.domain.subcategory.SubCategory;
import kg.projectr1.projectr1.domain.subcategory.SubCategoryRepository;
import kg.projectr1.projectr1.services.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/api")
public class AdminController {
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductRepository productRepository;
    private final ExcelService excelService;

    private String path;

    public AdminController(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository, ProductRepository productRepository, ExcelService excelService, FileSystemProperties fileSystemProperties) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.productRepository = productRepository;
        this.excelService = excelService;
        this.path = fileSystemProperties.getRoot();
    }


    @ResponseBody
    @RequestMapping(value = "/start_cat", method = RequestMethod.GET)
    public Object start_cat() throws IOException {


        String Category = "Category.csv";
        String SubCategory = "SubCategory.csv";

        File file = new File(path + File.separator + Category);
        int i = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (i > 0) {
                    kg.projectr1.projectr1.domain.category.Category category = new Category();
                    String[] str = line.split(";");
                    category.setName(str[1]);
                    try {
                        category.setDescription(str[2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    categoryRepository.save(category);
                }
                i++;
            }
            i = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        file = new File(path + File.separator + SubCategory);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (i > 0) {
                    kg.projectr1.projectr1.domain.subcategory.SubCategory subCategory = new SubCategory();
                    String[] str = line.split(";");
                    subCategory.setName(str[1]);
                    try {
                        subCategory.setDescription(str[2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    subCategory.setCategoriesId(Integer.parseInt(str[3]));
                    subCategoryRepository.save(subCategory);
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/start_prod", method = RequestMethod.GET)
    public Object start_prod() {
        File[] directories = new File(path).listFiles(File::isDirectory);
        for (File directory : directories){
            File[] files = directory.listFiles(File::isFile);
            for (File file : files){
                if (file.getName().endsWith("xlsx")){
                    save(file);
                }
            }
        }
        return "success";
    }

    private void save(File file) {

        ArrayList<Product> products = new ArrayList<>();

        try {
            FileInputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            int categoryId;
            int subCategoryId;
            try {
                categoryId= (int) sheet.getRow(2).getCell(5).getNumericCellValue();
            }catch (Exception e){
                categoryId= Integer.parseInt(sheet.getRow(2).getCell(5).getStringCellValue());
            }
            try {
                subCategoryId= (int) sheet.getRow(2).getCell(6).getNumericCellValue();
            }catch (Exception e){
                subCategoryId= Integer.parseInt(sheet.getRow(2).getCell(6).getStringCellValue());
            }


            for (int i = 1; i < sheet.getLastRowNum()+1; i++) {
                System.out.println(file.getAbsolutePath());
                Product product = new Product();
                Row row = sheet.getRow(i);
                product.setName(row.getCell(1).getStringCellValue());
                try {
                    product.setPrice(formatPrice(row.getCell(2).getStringCellValue()));
                }catch (Exception e){
                    product.setPrice(row.getCell(2).getNumericCellValue());
                }

                product.setBrand(row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "");
                product.setImagePath(row.getCell(4).getStringCellValue());
                product.setCategory(categoryId);
                product.setSubCategoriesId(subCategoryId);
                System.out.println(product);
                products.add(product);
            }
            inputStream.close();
            workbook.close();
        }catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        productRepository.saveAll(products);
    }





    @PostMapping("/uploadExcel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty())
            return new ResponseEntity<>("Please upload an Excel file!", HttpStatus.BAD_REQUEST);

        try {
            excelService.f(file.getInputStream());
            return new ResponseEntity<>("File uploaded successfully!", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private Double formatPrice(String price) {

        String priceStr = price;

        if(price.contains(".") && priceStr.contains(",")){
            priceStr = priceStr.replaceAll(",", "");
        }
        if(price.contains(",") && !priceStr.contains(".")){
            priceStr = priceStr.replaceAll(",", ".");
        }

        priceStr = priceStr.replaceAll("\\$", "");
        String[] priceList = priceStr.split("-");
        priceStr = priceList[0];
        //If we have 2 prices, we take last one
        if(priceList.length > 1) {
            priceStr = priceList[1];
        }
        return Double.parseDouble(priceStr);

    }
}
