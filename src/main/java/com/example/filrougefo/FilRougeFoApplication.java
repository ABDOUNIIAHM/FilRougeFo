package com.example.filrougefo;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.Order;
import com.example.filrougefo.entity.OrderLine;
import com.example.filrougefo.repository.ClientRepository;
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
public class FilRougeFoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilRougeFoApplication.class, args);
    }

}

