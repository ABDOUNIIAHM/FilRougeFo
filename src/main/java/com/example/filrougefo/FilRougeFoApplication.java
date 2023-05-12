package com.example.filrougefo;
import com.example.filrougefo.repository.MonthRepository;
import com.example.filrougefo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class FilRougeFoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FilRougeFoApplication.class, args);
    }
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MonthRepository monthRepository;
    @Override
    @Transactional
    public void run(String... args) throws Exception {

    }
}
