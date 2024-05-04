package kg.projectr1.projectr1.domain.category;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kg.projectr1.projectr1._generic.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "category")
@ToString
public class Category extends AbstractEntity {

    private String name;
    private String description;
    private boolean enable = true;

//    @OneToMany(mappedBy="category",fetch = FetchType.EAGER)
//    private Set<SubCategory> subCategory;
}
