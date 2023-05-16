package com.example.filrougefo.service.category;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.CategoryControllerException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public interface IntCategoryService {
    List<Category> findAll();
    Category findById(int id) throws CategoryControllerException;
    List<Category> findBySearchedName(String name) throws CategoryControllerException;
    List<Category> findAllCategoriesExceptProductCategory(Product product);

}
