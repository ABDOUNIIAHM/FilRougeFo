package com.example.filrougefo.repository;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,Long> {
    List<PhoneNumber> findPhoneNumberByClient(Client client);
}
