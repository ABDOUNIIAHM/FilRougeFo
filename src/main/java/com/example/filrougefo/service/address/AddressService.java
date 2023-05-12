package com.example.filrougefo.service.address;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.repository.AddressRepository;

import java.util.List;

public class AddressService implements IntAddressService{
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
//    @Override
//    public List<Address> findByClientId(Long id) {
//        return addressRepository.findAllByClientId(id);
//    }
}
