package com.example.filrougefo.service.product;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.repository.CategoryRepository;
import com.example.filrougefo.repository.ProductRepository;
import com.example.filrougefo.service.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void findById() {
    }

    @Test
    void searchProductByNamePattern() {
    }
}