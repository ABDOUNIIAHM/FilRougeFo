package com.example.filrougefo.service.category;

import com.example.filrougefo.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IntCategoryService {
    List<Category> findAll();
    Optional<Category> findById(int id);

}
