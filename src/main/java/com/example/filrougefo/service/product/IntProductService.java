package com.example.filrougefo.service.product;

import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.ProductControllerException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntProductService {
    List<Product> findAll();
    Product findById(int id);
    List<Product> searchProductByNamePattern(String name);
    List<Product> findAllProductByCategoryId(int id);
}
