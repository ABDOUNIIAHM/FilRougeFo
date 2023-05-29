package com.example.filrougefo.service.product;

import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntProductService {
    List<Product> findAll();
    Product findById(int id);
    List<Product> searchProductByNamePattern(String name);
    List<Product> findAllProductPerMonth(int id);
    List<Product> searchByKeyword(String keyword);
    List<Product> searchByKeywordForCategory(String keyword);

    List<Product> searchByKeywordForMonths(String keyword);

    List<Product> searchProductsGlobal(String keyword);

    List<Product> checkIfAvailableStock(Order order);

    void updateProductStock(Order order);
    void save(Product product);
}
