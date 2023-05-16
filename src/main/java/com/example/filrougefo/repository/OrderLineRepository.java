package com.example.filrougefo.repository;

import com.example.filrougefo.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {
    Optional<List<OrderLine>> findAllByOrder_Id(long id);
    Optional<OrderLine> findAllByOrder_IdAndProduct_Id (long idOrder, long idProduct);


}
