package com.example.filrougefo.repository;

import com.example.filrougefo.entity.Category;
import com.example.filrougefo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<List<Category>> findCategoriesByNameContainingIgnoreCase(String name);

    @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE CONCAT('%', LOWER(:keyword), '%')")
    List<Category> findCategoriesByPartialNameIgnoreCase(@Param("keyword") String keyword);
}
