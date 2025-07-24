package com.evento.api.service;

import com.evento.api.domain.address.Address;
import com.evento.api.domain.event.Event;
import com.evento.api.domain.event.EventRequestDTO;
import com.evento.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address createAdress(EventRequestDTO data, Event event){
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.uf());
        address.setEvent(event);

        return addressRepository.saveAndFlush(address);
    }
}
