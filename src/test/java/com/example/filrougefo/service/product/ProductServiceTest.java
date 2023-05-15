package com.example.filrougefo.service.product;
import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.repository.ProductRepository;
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
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService underTest;

    @Test
    void findAll() {

        Product p1 = new Product();
        Product p2 = new Product();
        p1.setId(1);
        p2.setId(2);
        List<Product> expected = List.of(p1,p2);

        when(productRepository.findAll()).thenReturn(expected);
        List<Product> result = underTest.findAll();

        assertEquals(expected,result);
    }

    @Test
    void ShouldReturnAProductById() {

        Product expected = new Product();
        expected.setId(1);

        when(productRepository.findById(any(int.class))).thenReturn(Optional.of(expected));
        Product result = underTest.findById(1);

        assertTrue(result instanceof Product);
        assertEquals(expected,result);
    }
    @Test
    void ShouldThrowExceptionWhenCategoryNotFoundById(){

        when(productRepository.findById(any(int.class))).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> underTest.findById(1));
    }

    @Test
    void searchProductByNamePattern() {
    }
}