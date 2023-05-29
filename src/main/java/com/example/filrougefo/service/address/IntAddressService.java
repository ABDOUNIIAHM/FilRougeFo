package com.example.filrougefo.service.address;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntAddressService {

    Address findById(long id);
    List<Address> findAddressesByClient(Client client);

    void updateAddress(long addressId, String title, String roadPrefix, String roadName, String city, String number, String complement, String zipCode);

    void deleteAddress(long addressId);
}
