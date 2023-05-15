package com.example.filrougefo.service.product;

import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntProductService {
    List<Product> findAll();
    Product findById(int id) throws ProductNotFoundException;
    List<Product> searchProductByNamePattern(String name) throws ProductNotFoundException;
}
