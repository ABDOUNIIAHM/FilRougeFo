package com.example.filrougefo;

import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.repository.OrderLineRepository;
import com.example.filrougefo.repository.OrderRepository;
import com.example.filrougefo.service.orderline.OrderLineService;
import com.example.filrougefo.web.order.OrderLineMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class FilRougeFoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FilRougeFoApplication.class, args);
    }

    @Autowired
    OrderLineService orderLineService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderLineRepository orderLineRepository;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        long id = 9;

        orderLineRepository.deleteById(id);



    }
}

