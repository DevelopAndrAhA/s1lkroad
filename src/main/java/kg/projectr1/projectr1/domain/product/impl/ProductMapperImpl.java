package kg.projectr1.projectr1.domain.product.impl;

import kg.projectr1.projectr1.domain.product.Product;
import kg.projectr1.projectr1.domain.product.ProductMapper;
import kg.projectr1.projectr1.domain.product.dto.ProductResponseDto;
import kg.projectr1.projectr1.domain.product.dto.ProductSaveDto;
import kg.projectr1.projectr1.domain.product.dto.ProductUpdateDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product fromSaveDto(ProductSaveDto productSaveDto) {
        //TODO
        return null;
    }

    @Override
    public Product fromUpdateDto(ProductUpdateDto productUpdateDto) {
        //TODO
        return null;
    }

    @Override
    public ProductResponseDto toResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .images(formatImages(product))
                .category(product.getCategory())
                .subCategory(product.getSubCategoriesId())
                .build();
    }
    private String[] formatImages(Product product){
        String urlBase = "http://localhost:8080/public/api/v1/image?cat=%s&sub=%s&img=%s";
        return Arrays.stream(product.getImagePath().split(",")).map(
                (fileName)->String.format(urlBase,product.getCategory(), product.getSubCategoriesId(), fileName.trim() )
        ).toArray(String[]::new);
    }
}
