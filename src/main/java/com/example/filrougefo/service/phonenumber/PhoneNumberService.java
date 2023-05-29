package com.example.filrougefo.service.phonenumber;

import com.example.filrougefo.entity.Client;
import com.example.filrougefo.entity.PhoneNumber;
import com.example.filrougefo.exception.ClientNotFoundException;
import com.example.filrougefo.exception.PhoneNumberNotFoundException;
import com.example.filrougefo.repository.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhoneNumberService implements IntPhoneNumberService {
    private  PhoneNumberRepository phoneNumberRepository;

    @Override
    public PhoneNumber findById(long id) {
        return phoneNumberRepository
                .findById(id)
                .orElseThrow(() -> new PhoneNumberNotFoundException("No PhoneNumber found for id="+id));
    }

    @Override
    public List<PhoneNumber> findPhoneNumberByClient(Client client){
        return phoneNumberRepository.findPhoneNumberByClient(client);
    }
    @Override
    public void updatePhoneNumber(long phoneNumberId, String value) {
        // Récupérer le numéro de téléphone à mettre à jour
        PhoneNumber phoneNumber = phoneNumberRepository.findById(phoneNumberId)
                .orElseThrow(() -> new IllegalArgumentException("Numéro de téléphone introuvable"));

        // Mettre à jour la valeur du numéro de téléphone
        phoneNumber.setPhoneNumber(value);

        // Enregistrer les modifications
        phoneNumberRepository.save(phoneNumber);
    }

    @Override
    public void deletePhoneNumber(long idPhoneNumber) {
        phoneNumberRepository.deleteById(idPhoneNumber);
    }

    public void add(PhoneNumber phoneNumber) {
        phoneNumberRepository.save(phoneNumber);
    }


}
