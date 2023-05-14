package com.example.filrougefo.service.category;
import com.example.filrougefo.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private IntCategoryService categoryService;


    @Test
    void findAll() {

    }

    @Test
    void findById() {
    }

    @Test
    void findBySearchedName() {
    }

    @Test
    void findAllCategoriesExceptProductCategory() {
    }
}