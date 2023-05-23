package com.example.filrougefo.repository;

import com.example.filrougefo.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    List<OrderStatus> findAllByNameIsNotContaining(String name);
}
