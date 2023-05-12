package com.example.filrougefo.service.category;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryService implements IntCategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }
}
