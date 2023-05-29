package com.example.filrougefo.repository;

import com.example.filrougefo.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MonthRepository extends JpaRepository<Month,Integer> {

    Optional<Month> findByNameIgnoreCase(String name);
}
