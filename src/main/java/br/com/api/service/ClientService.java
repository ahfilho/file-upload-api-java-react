package br.com.api.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.api.entity.Address;
import br.com.api.repository.AddressRepository;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.stereotype.Service;

import br.com.api.entity.Client;
import br.com.api.repository.ClientRepository;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    public void clientSave(Client client, Address address) {
        this.clientRepository.save(client);
        this.addressRepository.save(address);
    }

    public List<Client> clientList() throws Exception {
        List<Client> cli = clientRepository.findAll();
        if (cli.isEmpty()) {
            System.out.println("Não existe cliente cadastrado.");
        }
        return this.clientRepository.findAll();
    }

    public Client clientUpdate(Client client) throws Exception {
        Optional<Client> cmo = this.clientRepository.findById(client.getId());
        if (cmo.isPresent()) {
            Client cm = cmo.get();
            cm.setName(client.getName());
            cm.setEmail(client.getEmail());
            cm.setCpf(client.getCpf());
            cm.setContact(client.getContact());
            return cm;
        } else {
            throw new Exception("ERRO AO ATUALIZAR" + client.getId());
        }
    }

    public void clientDelete(Long clientId) throws Exception {
        Optional<Client> client = this.clientRepository.findById(clientId);
        if (client.isPresent()) {
            this.clientRepository.delete(client.get());
        } else {
            throw new Exception("ERRO AO DELETAR O ID" + clientId);
        }
    }

    public int searchCpf(String cpf) {
        List<Client> cli = null;
        cli = clientRepository.searchCpf(cpf);
        return cli.size();
    }
}
