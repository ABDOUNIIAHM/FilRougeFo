package com.example.filrougefo;
import com.example.filrougefo.entity.*;
import com.example.filrougefo.repository.*;
import com.example.filrougefo.service.order.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
public class FilRougeFoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FilRougeFoApplication.class, args);
    }
@Autowired OrderService orderService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        orderService.getNonPendingOrders().stream().forEach(x-> System.out.println(x.getStatus().getName()));

    }
}

