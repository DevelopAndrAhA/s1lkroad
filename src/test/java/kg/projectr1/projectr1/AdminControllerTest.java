package kg.projectr1.projectr1;


import com.opencsv.exceptions.CsvValidationException;
import kg.projectr1.projectr1.domain.admin.AdminController;
import kg.projectr1.projectr1.domain.product.ProductController;
import kg.projectr1.projectr1.domain.product.ProductRepository;
import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProjectR1Application.class)
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AdminController controller;

    @Autowired
    private ProductRepository repository;




    @AfterEach
    void tearDown() {
//        repository.deleteAll();
    }

    //NOT TEST
    //Just faster way to import data
    @Test
    void start_prod() throws CsvValidationException, IOException {


        try {
            controller.start_prod();
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
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