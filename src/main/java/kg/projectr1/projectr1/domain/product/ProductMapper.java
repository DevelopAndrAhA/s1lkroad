package kg.projectr1.projectr1.domain.product;

import kg.projectr1.projectr1._generic.Mapper;
import kg.projectr1.projectr1.domain.product.dto.ProductResponseDto;
import kg.projectr1.projectr1.domain.product.dto.ProductSaveDto;
import kg.projectr1.projectr1.domain.product.dto.ProductUpdateDto;

public interface ProductMapper
        extends Mapper<Product, ProductResponseDto, ProductSaveDto, ProductUpdateDto> {
}
