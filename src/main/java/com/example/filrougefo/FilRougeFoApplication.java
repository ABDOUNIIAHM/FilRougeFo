package com.example.filrougefo;
import com.example.filrougefo.entity.*;
import com.example.filrougefo.repository.*;
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
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MonthRepository monthRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Verify the cascade for Address and PhoneNumber when saving Client
        Address address1 = new Address();
        address1.setTitle("M.");
        address1.setNumber("3 bis");
        address1.setRoadPrefix("Avenue");
        address1.setRoadName("Des Lilas");
        address1.setComplement("Appt 203");
        address1.setZipCode("44000");
        address1.setCity("Nantes");

        Address address2 = new Address();
        address2.setTitle("M.");
        address2.setNumber("4");
        address2.setRoadPrefix("Bld");
        address2.setRoadName("Des Anglais");
        address2.setComplement("Appt 104");
        address2.setZipCode("44000");
        address2.setCity("Nantes");

        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setPhoneNumber("0606060606");

        PhoneNumber phoneNumber2 = new PhoneNumber();
        phoneNumber2.setPhoneNumber("0707070707");

        Client client = new Client();
        client.setFirstName("Jean");
        client.setLastName("Michel");
        client.setEmail("jm@email.fr");
        client.setPassword("pwd");
        client.setAvatarUrl("avatarUrl");
        client.setAddressList(Arrays.asList(address1, address2));
        client.setPhoneNumberList(Arrays.asList(phoneNumber1, phoneNumber2));

        Order order = new Order();
        order.setClient(client);
        client.getOrderList().add(order);
        order.setStatus(orderStatusRepository.getReferenceById(1L));
        order.setPaymentMethod(paymentMethodRepository.getReferenceById(1L));
        order.setDate(LocalDate.now());

        OrderLine orderLine = new OrderLine();
        orderLine.setOrder(order);
        order.getOrderLines().add(orderLine);
        orderLine.setProduct(productRepository.getReferenceById(1));
        orderLine.setQuantity(BigDecimal.valueOf(0.250));

//        clientRepository.save(client);

        OrderLine orderLine2 = new OrderLine();
        orderLine2.setOrder(order);
        order.getOrderLines().add(orderLine2);
        orderLine2.setProduct(productRepository.getReferenceById(2));
        orderLine2.setQuantity(BigDecimal.valueOf(1));

//        orderRepository.save(order);



    }
}
