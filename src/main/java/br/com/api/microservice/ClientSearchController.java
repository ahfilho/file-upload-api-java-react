package br.com.api.microservice;


import br.com.api.entity.Client;
import br.com.api.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class ClientSearchController {

    private final ClientService clientService;

    public ClientSearchController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/pesquisa/{cpf}")
    public ResponseEntity<?> pesquisa(@PathVariable String cpf) {
        try {
            Client client = clientService.findByCpf(cpf);
            if (client != null) {
                return ResponseEntity.ok(client);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar a requisição");
        }
    }

    @GetMapping("/find/{id}")
    public Client searchClientById(@PathVariable Long id) {
        return clientService.findClientById(id);
    }

}
