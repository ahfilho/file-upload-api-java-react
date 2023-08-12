package br.com.api.service;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.dto.AddressDto;
import br.com.api.dto.ClientDto;
import br.com.api.entity.Address;
import br.com.api.repository.AddressRepository;
import br.com.api.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private JWTTokenHelper jwtTokenHelper;

    private ClientRepository clientRepository;

    private AddressRepository addressRepository;


    public AddressService(JWTTokenHelper jwtTokenHelper, ClientRepository clientRepository, AddressRepository addressRepository) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;

    }

    public void addressSave(AddressDto addressDto) {
        addressRepository.save(addressDto);
    }

}
