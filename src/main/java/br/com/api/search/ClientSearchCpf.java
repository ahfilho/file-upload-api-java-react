package br.com.api.search;


import br.com.api.entity.Client;
import br.com.api.repository.ClientRepository;
import org.springframework.stereotype.Controller;

@Controller
public class ClientSearchCpf {

    private final ClientRepository clientRepository;


    public ClientSearchCpf(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean findByCpf(String cpf) {
        Client existingCLient = clientRepository.clientWithSameCpf(cpf);
        return existingCLient != null;
    }

}
