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
public class FilRougeFoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilRougeFoApplication.class, args);
    }

}

