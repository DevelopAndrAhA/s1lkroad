package kg.projectr1.projectr1.domain.comments;

import jakarta.persistence.*;
import kg.projectr1.projectr1._generic.AbstractEntity;
import kg.projectr1.projectr1.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "comment")
public class Comment extends AbstractEntity {
    private String content;
    private String username;
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    private Product product;
    private Integer rating;
}
