package kg.projectr1.projectr1.domain.product.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String[] images;
    private Integer category;
    private Integer subCategory;
}
