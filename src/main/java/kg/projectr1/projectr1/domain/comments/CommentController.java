package kg.projectr1.projectr1.domain.comments;


import kg.projectr1.projectr1.domain.comments.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	@GetMapping(CommentApi.FIND_BY_PRODUCT_ID)
	public ResponseEntity<List<CommentResponseDto>> findByProductId(
			@PathVariable("id") Long id
	) throws Exception {

		LocalDate date = LocalDate.now();
		return ResponseEntity.ok(List.of(
				CommentResponseDto.builder()
						.id(1l).content("Awesome item").username("Ivan ivanov").date(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))).rating(4)
						.build(),
				CommentResponseDto.builder()
						.id(1l).content("Awesome item").username("Ivan ivanov").date(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))).rating(3)
						.build(),
				CommentResponseDto.builder()
						.id(1l).content("Awesome item").username("Ivan ivanov").date(date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
						.build()
		));
		//return ResponseEntity.ok(commentService.findComments(id));
	}

}


























