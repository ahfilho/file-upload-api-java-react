package br.com.api.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.api.dto.ClientDto;
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
        //TODO filtro para verificar se o cpf ja está cadastrado e assim não permitir repetições
        client.setDataRegister(LocalDate.now());
        clientRepository.save(client);
    }

    public List<Client> clientList() throws Exception {
        List<Client> clientList = clientRepository.findAll();

        clientList.sort(Comparator.comparing(Client::getName));
        for (Client client : clientList) {
            Client client1 = client.getAddress().getClient();
            System.out.println(client1);
        }
        if (clientList.isEmpty()) {
            System.out.println("Não existe cliente cadastrado.");
        }
        return clientList;
    }

    public Client clientUpdate(Client client) throws Exception {
        Optional<Client> optionalClient = this.clientRepository.findById(client.getId());
        if (optionalClient.isPresent()) {
            Client cm = optionalClient.get();
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

}
