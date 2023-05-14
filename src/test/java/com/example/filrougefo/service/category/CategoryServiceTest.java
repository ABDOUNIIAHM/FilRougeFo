package com.example.filrougefo.service.category;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;


    @Test
    void ShouldReturnAllCategories() {

        List<Category> expected = List.of(
                new Category(1,"categ1",null)
                ,new Category(2,"categ2",null));

        when(categoryRepository.findAll()).thenReturn(expected);
        List<Category> result = categoryService.findAll();

        assertEquals(expected,result);
    }

    @Test
    void ShouldReturnACategory() {

        Optional<Category> expected = Optional.of(new Category(1, "categ1", null));

        when(categoryRepository.findById(any(int.class))).thenReturn(expected);
        Category result = categoryService.findById(1);

        assertEquals(expected.get(),result);
    }
    @Test
    void ShouldThrowExceptionWhenCategoryNotFoundById(){

        when(categoryRepository.findById(any(int.class))).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> categoryService.findById(1));

    }

    @Test
    void findBySearchedName() {
    }

    @Test
    void findAllCategoriesExceptProductCategory() {
    }
}