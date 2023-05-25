package com.example.filrougefo.service.phonenumber;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntPhoneNumberService {
    List<PhoneNumber> findPhoneNumberByClient(Client client);
    void updatePhoneNumber(long phoneNumberId, String phoneNumber);
}
