package kg.projectr1.projectr1.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryAndSubCategoriesId(Integer category, Integer subCategoriesId);
    Page<Product> findAllByCategoryAndSubCategoriesId(Integer category, Integer subCategoriesId, Pageable pageable);

    @Query(value = "select * from product p where to_tsvector(p.name)"
            + " @@ cast(replace(cast(to_tsquery(:currentText) as text), '&', '|') as tsquery)" +
            " order by p.id desc", nativeQuery = true)
    Page<Product> findSimilar(String currentText, Pageable pageable);
}
