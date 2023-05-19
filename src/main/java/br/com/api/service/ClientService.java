package br.com.api.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.api.entity.Address;
import br.com.api.repository.AddressRepository;
import br.com.api.repository.SsdRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SsdRepository ssdRepository;

    public void clientSave(Client client) {
        Address address = client.getAddress();

        client.setAddress(address);
        address.setClient(client);

        this.clientRepository.save(client);

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

    public String searchCpf(String cpf) {
        List<Client> cli = clientRepository.searchCpf(cpf);
        return cpf;
    }
}
