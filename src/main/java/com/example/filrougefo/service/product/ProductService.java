package com.example.filrougefo.service.product;
import com.example.filrougefo.entity.Product;
import com.example.filrougefo.exception.ProductNotFoundException;
import com.example.filrougefo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IntProductService{
    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    @Override
    public Product findById(int id) throws ProductNotFoundException {

        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("No product with id="+id+" was found !"));
    }
    @Override
    public List<Product> searchProductByNamePattern(String name) throws ProductNotFoundException {

        return productRepository
                .findAllByNameContainingIgnoreCase(name)
                .orElseThrow(()-> new ProductNotFoundException("No product with name="+name+" was found !"));
    }
}
