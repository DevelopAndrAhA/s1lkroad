
package kg.projectr1.projectr1.domain.product;

import jakarta.persistence.*;
import kg.projectr1.projectr1._generic.AbstractEntity;
import lombok.*;

import java.util.Comparator;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "product")
public class Product extends AbstractEntity implements Comparator<Product> {

    private String name;
    private Double price;
    private String brand;
    @Column(columnDefinition = "TEXT")
    private String imagePath;
    private Integer category;
    private Integer subCategoriesId;
    @Column(columnDefinition = "TEXT")
    private String description;

    private String currencyCode;
    private Float discount;

    @Override
    public int compare(Product o1, Product o2) {
        if(o1.getPrice()==null){
            o1.setPrice(0.0);
        }
        if(o2.getPrice()==null){
            o2.setPrice(0.0);
        }
        return o1.getPrice().compareTo(o2.getPrice());
    }
}
