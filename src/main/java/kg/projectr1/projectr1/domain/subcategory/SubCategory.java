package kg.projectr1.projectr1.domain.subcategory;

import jakarta.persistence.*;
import kg.projectr1.projectr1._generic.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sub_category")
public class SubCategory extends AbstractEntity {
    private String name;
    private String description;
    private int categoriesId;
    private boolean enable = true;

//    @ManyToOne
//    @JoinColumn(name="category_id", nullable=false)
//    private Category category;
//
//    @OneToMany(fetch = FetchType.EAGER,mappedBy="subCategory")
//    private Set<Product> products;
}
