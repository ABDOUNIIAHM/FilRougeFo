package com.example.filrougefo.service.address;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AddressService implements IntAddressService{
    private final AddressRepository addressRepository;
//    @Override
//    public List<Address> findByClientId(long id) {
//        return addressRepository.findAllByClientId(id);
//    }
}
