package kg.projectr1.projectr1.domain.product;

import kg.projectr1.projectr1.common.pagination.PageRequestDto;
import kg.projectr1.projectr1.domain.product.dto.ProductResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<ProductResponseDto> findPaginated(Long cat, Long sub, PageRequestDto pageRequestDto);
    ProductResponseDto findById(Long id);
    Page<ProductResponseDto> search(String searchText, PageRequestDto pageRequestDto);
}
