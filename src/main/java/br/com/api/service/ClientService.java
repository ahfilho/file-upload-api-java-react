package br.com.api.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.api.entity.Address;
import br.com.api.entity.User;
import br.com.api.exceptions.ErrorHandling;
import br.com.api.repository.AddressRepository;
import br.com.api.repository.SsdRepository;
import org.springframework.stereotype.Service;

import br.com.api.entity.Client;
import br.com.api.repository.ClientRepository;

@Service
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    private final AddressRepository addressRepository;

    private final SsdRepository ssdRepository;

    private final ErrorHandling errorHandling;

    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository, SsdRepository ssdRepository, ErrorHandling errorHandling) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.ssdRepository = ssdRepository;
        this.errorHandling = errorHandling;
    }

    public void clientSave(Client client) {
        //TODO filtro para verificar se o cpf ja está cadastrado e assim não permitir repetições
        client.setDataRegister(LocalDate.now());
        clientRepository.save(client);
    }

    public List<Client> clientList() throws Exception {
        List<Client> clientList = clientRepository.findAll();
        try {
            clientList.sort(Comparator.comparing(Client::getName));
            for (Client client : clientList) {
                Client client1 = client.getAddress().getClient();
                System.out.println(client1);
            }
            if (clientList.isEmpty()) {
                System.out.println("Não existe cliente cadastrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientList;
    }

    public Client clientUpdate(Client client, Address address) throws Exception {
        Optional<Client> optionalClient = this.clientRepository.findById(client.getId());
        if (optionalClient.isPresent()) {
            Client cm = optionalClient.get();
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
