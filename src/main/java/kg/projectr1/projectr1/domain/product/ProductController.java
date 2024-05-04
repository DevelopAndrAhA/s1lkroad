package kg.projectr1.projectr1.domain.product;


import com.fasterxml.jackson.databind.ObjectMapper;
import kg.projectr1.projectr1.common.pagination.PageRequestDto;
import kg.projectr1.projectr1.domain.product.dto.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;




	@GetMapping(ProductApi.PAGINATED)
	public ResponseEntity<Page<ProductResponseDto>> findPaginated(
			@RequestParam("cat") Long cat,
			@RequestParam("sub") Long sub,
			@RequestParam("pageRequestDto") String pageRequestDtoEncoded
	) throws Exception {
		var pageRequestJson = URLDecoder.decode(pageRequestDtoEncoded, StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			PageRequestDto pageRequestDto = objectMapper.readValue(pageRequestJson, PageRequestDto.class);
			Page<ProductResponseDto> products = productService.findPaginated(cat, sub, pageRequestDto);
			log.info("Found {} products", products.getTotalElements());
			return ResponseEntity.ok(products);
		} catch (IOException e) {
			//TODO handle exception
			throw new Exception();
		}

	}
	@GetMapping(ProductApi.FIND_BY_ID)
	public ResponseEntity<ProductResponseDto> findById(
			@PathVariable("id") Long id
	) throws Exception {
		return ResponseEntity.ok(productService.findById(id));
	}

	@GetMapping(ProductApi.SEARCH)
	public ResponseEntity<Page<ProductResponseDto>> search(
			@RequestParam("searchText") String searchText,
			@RequestParam("pageRequestDto") String pageRequestDtoEncoded
	) throws Exception {
		var pageRequestJson = URLDecoder.decode(pageRequestDtoEncoded, StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			PageRequestDto pageRequestDto = objectMapper.readValue(pageRequestJson, PageRequestDto.class);
			Page<ProductResponseDto> products = productService.search(searchText, pageRequestDto);
			log.info("Found {} products", products.getTotalElements());
			return ResponseEntity.ok(products);
		} catch (IOException e) {
			//TODO handle exception
			throw new Exception();
		}
	}
}


























