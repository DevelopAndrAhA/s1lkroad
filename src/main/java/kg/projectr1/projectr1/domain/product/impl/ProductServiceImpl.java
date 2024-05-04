package kg.projectr1.projectr1.domain.product.impl;

import kg.projectr1.projectr1.common.pagination.PageRequestDto;
import kg.projectr1.projectr1.domain.product.Product;
import kg.projectr1.projectr1.domain.product.ProductMapper;
import kg.projectr1.projectr1.domain.product.ProductRepository;
import kg.projectr1.projectr1.domain.product.dto.ProductResponseDto;
import kg.projectr1.projectr1.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public Page<ProductResponseDto> findPaginated(Long cat, Long sub, PageRequestDto pageRequestDto) {
        Page<Product> page = productRepository
                .findAllByCategoryAndSubCategoriesId(Math.toIntExact(cat), Math.toIntExact(sub), pageRequestDto.toPageable());
        return page.map(mapper::toResponseDto);
    }

    @Override
    public ProductResponseDto findById(Long id) {
        return productRepository.findById(id).map(mapper::toResponseDto).orElse(null);
    }

    @Override
    public Page<ProductResponseDto> search(String searchText, PageRequestDto pageRequestDto) {
        String sanitized = searchText
                .replaceAll("[\\W_]+", " ")  // Replace non-word characters and underscores with space
                .trim()                      // Trim leading and trailing spaces
                .replaceAll("\\s+", " | ")   // Replace sequences of one or more spaces with ' | '
                .replaceAll("^\\| | \\|$", "");

        return productRepository.findSimilar(
                sanitized,
                pageRequestDto.toPageable()
        ).map(mapper::toResponseDto);
    }
}
