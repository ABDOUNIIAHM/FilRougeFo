package com.example.filrougefo.repository;
import com.example.filrougefo.entity.Months;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MonthRepository extends JpaRepository<Months,Integer> {
}
