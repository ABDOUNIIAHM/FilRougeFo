package com.example.filrougefo.service.category;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CategoryService implements IntCategoryService{
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Category findById(int id) {

        Category cat = categoryRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("No category found for Id:"+id));

        return cat;
    }

    @Override
    public List<Category> findBySearchedName(String name) {

        List<Category> searchedCategories = categoryRepository
                .findCategoriesByNameContainingIgnoreCase(name)
                .orElseThrow(()->new RuntimeException("No category found for: "+name));

        return searchedCategories;
    }
}
