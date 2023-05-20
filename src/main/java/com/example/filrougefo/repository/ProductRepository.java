package com.example.filrougefo.repository;

import com.example.filrougefo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<List<Product>> findAllByNameContainingIgnoreCase(String name);
    Optional<List<Product>> findAllByCategory_Id(int id);

}
