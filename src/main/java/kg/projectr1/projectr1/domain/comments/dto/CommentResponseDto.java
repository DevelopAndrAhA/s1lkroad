package kg.projectr1.projectr1.domain.comments.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentResponseDto {
    private Long id;
    private String content;
    private String username;
    private String date;
    private Long productId;
    private Integer rating;
}
