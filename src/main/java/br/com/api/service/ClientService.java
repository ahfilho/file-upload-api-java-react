package br.com.api.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.api.entity.Address;
import br.com.api.exceptions.ErrorHandling;
import br.com.api.producer.ClientProducer;
import br.com.api.repository.AddressRepository;
import br.com.api.repository.SsdRepository;
import org.springframework.stereotype.Service;

import br.com.api.entity.Client;
import br.com.api.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final AddressRepository addressRepository;

    private final SsdRepository ssdRepository;

    private final ErrorHandling errorHandling;

    final ClientProducer clientProducer;

    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository, SsdRepository ssdRepository, ErrorHandling errorHandling, ClientProducer clientProducer) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.ssdRepository = ssdRepository;
        this.errorHandling = errorHandling;
        this.clientProducer = clientProducer;
    }

    @Transactional
    public Client clientSave(Client client) {
        client.setDataRegister(LocalDate.now());
        client = clientRepository.save(client);
        clientProducer.sendMessageEmail(client);
        return client;
    }

    public List<Client> clientList() {
        List<Client> clientList = clientRepository.findAll();
        try {
            if (clientList.isEmpty()) {
                System.out.println("Não existe cliente cadastrado.");
            } else {
                clientList.sort(Comparator.comparing(client -> client.getName().substring(0, 1), String.CASE_INSENSITIVE_ORDER));
                clientList.forEach(client -> System.out.println(client));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }


    public Client clientUpdate(Client client) throws Exception {
        Optional<Client> optionalClient = this.clientRepository.findById(client.getId());
        if (optionalClient.isPresent()) {
            Client cm = optionalClient.get();
            cm.setId(client.getId());
            cm.setName(client.getName());
            cm.setEmail(client.getEmail());
            cm.setCpf(client.getCpf());
            cm.setContact(client.getContact());


            Address addressEdit = cm.getAddress();
            addressEdit.setCity(client.getAddress().getCity());
            addressEdit.setDistrict(client.getAddress().getDistrict());
            addressEdit.setNumber(client.getAddress().getNumber());
            addressEdit.setStreet(client.getAddress().getStreet());

            clientRepository.save(cm);
            addressRepository.save(addressEdit);

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

    public Client findClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }


    public Client findByCpf(String cpf) {
        return clientRepository.find(cpf);
    }
}
