package kg.projectr1.projectr1.services;


import kg.projectr1.projectr1.domain.category.Category;
import kg.projectr1.projectr1.domain.product.Product;
import kg.projectr1.projectr1.domain.subcategory.SubCategory;
import kg.projectr1.projectr1.domain.category.CategoryRepository;
import kg.projectr1.projectr1.domain.product.ProductRepository;
import kg.projectr1.projectr1.domain.subcategory.SubCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;


    public Category createCategory(Category category){
        categoryRepository.save(category);
        return category;
    }

    public SubCategory createSubCategory(SubCategory subCategory){
        subCategoryRepository.save(subCategory);
        return subCategory;
    }

    public Product createProduct(Product product){
        productRepository.save(product);
        return product;
    }

    public List<Category> getAllActiveCategories(){
        return categoryRepository.findAllByEnableIsTrue();
    }

    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name.trim());
    }

    public List<SubCategory> getAllSubcategories(){
        return subCategoryRepository.findAllByEnableIsTrue();
    }

    public SubCategory getSubcategoryByName(String name){
        return subCategoryRepository.findByName(name);
    }
}
