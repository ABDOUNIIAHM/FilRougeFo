package com.example.filrougefo.repository;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

 List<Address> findAddressesByClient(Client client);
}
