package kg.projectr1.projectr1.domain.images;

import kg.projectr1.projectr1.config.FileSystemProperties;
import org.apache.poi.util.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

@Controller
public class ImageController {


    private final String path;

    public ImageController(FileSystemProperties fileSystemProperties) {
        this.path = fileSystemProperties.getRoot();
    }

    @ResponseBody
    @RequestMapping(value = ImageApi.GET, method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPhoto(
            @RequestParam("cat") String category,
            @RequestParam("sub") String subCategory,
            @RequestParam("img") String img
    ) {
        try {
            String rootPath = getImagePath(category, subCategory, img);
            rootPath += File.separator + img;
            InputStream in = new FileInputStream(rootPath);
            byte[] imageBytes = IOUtils.toByteArray(in);

            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    private String getImagePath(String category, String subCategory, String img){
        File[] directories = new File(path).listFiles(File::isDirectory);

        //category
        File directory = Arrays.stream(directories).filter((dir)-> dir.getName().startsWith(category+".")).findFirst().get();

        //subcategory
        if(category.equals("1")){
            subCategory = "0";
        }
        String finalSubCategory = subCategory;

        File imgFolder = Arrays.stream(directory.listFiles(File::isDirectory))
                .filter((dir)-> dir.getName().startsWith(finalSubCategory +".")).findFirst().get();

        return imgFolder.getAbsolutePath();
    }
}
