package com.example.filrougefo.service.category;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface IntCategoryService {
    List<Category> findAll();
    Category findById(int id);
    List<Category> findBySearchedName(String name);
    List<Category> findAllCategoriesExceptProductCategory(Product product);

}
