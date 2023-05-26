package com.example.filrougefo.repository;

import com.example.filrougefo.entity.Month;
import com.example.filrougefo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<List<Product>> findAllByNameContainingIgnoreCase(String name);
    Optional<List<Product>> findAllByCategory_Id(int id);
    List<Product> findAllBySeasonalMonthsContaining(Month month);



        @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(:keyword), '%') OR LOWER(p.description) LIKE CONCAT('%', LOWER(:keyword), '%')")
        List<Product> findByPartialNameOrDescriptionIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT p FROM Product p WHERE LOWER(p.category.name) LIKE CONCAT('%', LOWER(:keyword), '%')")
    List<Product> findProductsByPartialCategoryNameIgnoreCase(@Param("keyword") String keyword);

}


