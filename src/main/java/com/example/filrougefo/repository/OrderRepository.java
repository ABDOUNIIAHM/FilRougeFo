package com.example.filrougefo.repository;
import com.example.filrougefo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByClientId(long id);
    List<Order> findAllByStatus_Id(long id);
    List<Order> findAllByStatus_Name(String name);
    List<Order> findAllByDateIsLessThanEqual(LocalDate date);
}
