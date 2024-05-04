package kg.projectr1.projectr1.domain.product.impl;

import com.opencsv.exceptions.CsvValidationException;
import kg.projectr1.projectr1.ProjectR1Application;
import kg.projectr1.projectr1.domain.product.ProductRepository;
import kg.projectr1.projectr1.domain.product.ProductService;
import kg.projectr1.projectr1.domain.product.dto.ProductResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ProjectR1Application.class)
@AutoConfigureMockMvc
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;


    @Test
    void test_get_similar_products() {
        Long productId = 117724L;
        String productName = "Chairs";
        try {
//            List<ProductResponseDto> list = productService.findSimilar(productName, );
//            list.forEach((productResponseDto) -> {
//                System.out.println(productResponseDto.getName());
//            });
        }catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}