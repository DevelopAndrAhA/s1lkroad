package kg.projectr1.projectr1.services;


import kg.projectr1.projectr1.domain.product.Product;
import kg.projectr1.projectr1.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ExcelService {

    private final ProductRepository productRepository;

    @SneakyThrows
    public void f(InputStream fileInputStream) {
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row headerRow = sheet.getRow(0);
        int numberOfCells = headerRow.getPhysicalNumberOfCells() - 1;
        System.out.println(numberOfCells);
        for (Row row : sheet) {
            if (row.getRowNum() == 0)
                continue;

            Product product = new Product();
            product.setName(row.getCell(1).getStringCellValue());
            product.setPrice(row.getCell(2).getNumericCellValue());
            product.setBrand(row.getCell(3).getStringCellValue());
            product.setImagePath(row.getCell(4).getStringCellValue());
            product.setCategory((int) row.getCell(5).getNumericCellValue());
            product.setSubCategoriesId((int) row.getCell(6).getNumericCellValue());

            String description = "";
            for (int i = 7; i <= numberOfCells; i++) {

                if (row.getCell(i) != null && headerRow.getCell(i).getStringCellValue() != null && headerRow.getCell(i).getStringCellValue().equals("desc")) {
                    String columnValue = row.getCell(i).getStringCellValue();
                    String[] arr = columnValue.split(";");
                    for (String value : arr) {
                        String[] parts = value.split("::");
                        if (parts.length == 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                            description = description.concat(value).replace("::", ":").concat(";");
                        }
                    }
                }

                if (row.getCell(i) != null && headerRow.getCell(i).getStringCellValue() != null && !headerRow.getCell(i).getStringCellValue().equals("desc")) {
                    String columnName = headerRow.getCell(i).getStringCellValue();
                    String columnValue = row.getCell(i).getStringCellValue();
                    description = description.concat(columnName.concat(":").concat(columnValue).concat(";"));
                }

                if (i == numberOfCells) {
                    product.setDescription(description);
                    break;
                }
            }
            productRepository.save(product);
        }
    }
}
