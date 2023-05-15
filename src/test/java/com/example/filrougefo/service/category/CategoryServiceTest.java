package com.example.filrougefo.service.category;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.CategoryNotFoundException;
import com.example.filrougefo.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService underTest;

    @Test
    void ShouldReturnAllCategories() {

        List<Category> expected = List.of(
                new Category(1,"categ1",null)
                ,new Category(2,"categ2",null));

        when(categoryRepository.findAll()).thenReturn(expected);
        List<Category> result = underTest.findAll();

        assertEquals(expected,result);
    }

    @Test
    void ShouldReturnACategoryById() throws CategoryNotFoundException {

        Category expected = new Category(1, "categ1", null);

        when(categoryRepository.findById(any(int.class))).thenReturn(Optional.of(expected));
        Category result = underTest.findById(1);

        assertTrue(result instanceof Category);
        assertEquals(expected,result);
    }
    @Test
    void ShouldThrowExceptionWhenCategoryNotFoundById(){

        when(categoryRepository.findById(any(int.class))).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> underTest.findById(1));
    }

    @Test
    void ShouldReturnListOfCategoriesWhenSearchedByNamePattern() throws CategoryNotFoundException {

        List<Category> expected = List.of(
                new Category(1,"categ1",null)
                ,new Category(2,"categ2",null));

        when(categoryRepository.findCategoriesByNameContainingIgnoreCase(any(String.class)))
                .thenReturn(Optional.of(expected));
        List<Category> result = underTest.findBySearchedName("name");

        assertEquals(expected,result);
    }
    @Test
    void ShouldThrowExceptionWhenNoCategoryIsFoundByNamePattern() {

        when(categoryRepository.findCategoriesByNameContainingIgnoreCase(any(String.class)))
                .thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class,() -> underTest.findBySearchedName("name") );
    }

    @Test
    void findAllCategoriesExceptProductCategory() {

        Category cat1 = new Category(1,"categ1",null);
        Category cat2 = new Category(2,"categ2",null);
        Category cat3 = new Category(3,"categ3",null);

        Product product = new Product();
        product.setCategory(cat1);

        List<Category> allCategories = List.of(cat1,cat2,cat3);
        List<Category> expected = List.of(cat2,cat3);

        when(categoryRepository.findAll()).thenReturn(allCategories);

        List<Category> result = underTest.findAllCategoriesExceptProductCategory(product);

        AtomicInteger index = new AtomicInteger();

        assertTrue(result.size()==2);
        result
                .stream()
                .forEach(c -> {
                    assertEquals(expected.get(index.get()).getId(),c.getId());
                    index.getAndIncrement();
                });
    }
}