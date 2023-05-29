package com.example.filrougefo.service.address;

import com.example.filrougefo.entity.Address;
import com.example.filrougefo.entity.Client;
import com.example.filrougefo.exception.AddressNotFoundException;
import com.example.filrougefo.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class AddressService implements IntAddressService {
    private final AddressRepository addressRepository;

    @Override
    public Address findById(long id) {
        return addressRepository
                .findById(id)
                .orElseThrow(() -> new AddressNotFoundException("No Address found for id="+id));
    }

    @Override
    public List<Address> findAddressesByClient(Client client) {
        return addressRepository.findAddressesByClient(client);
    }

    @Override
    public void updateAddress(long addressId, String title, String roadPrefix, String roadName, String city, String number, String complement, String zipCode) {
        // Récupérer l'adresse à mettre à jour en fonction de son ID
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new RuntimeException("Adresse non trouvée"));

        // Mettre à jour les informations de l'adresse
        address.setTitle(title);
        address.setRoadPrefix(roadPrefix);
        address.setRoadName(roadName);
        address.setCity(city);
        address.setNumber(number);
        address.setComplement(complement);
        address.setZipCode(zipCode);

        // Enregistrer les modifications dans la base de données
        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(long addressId) {
        addressRepository.deleteById(addressId);
    }
}
