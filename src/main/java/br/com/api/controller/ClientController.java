package br.com.api.controller;

import br.com.api.auth.token.JWTTokenHelper;
import br.com.api.dto.ClientDto;
import br.com.api.entity.Client;
import br.com.api.repository.ClientRepository;
import br.com.api.search.ClientSearchCpf;
import br.com.api.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/client")
public class ClientController {

    private final JWTTokenHelper jwtTokenHelper;
    final ClientService clientService;
    private final ClientSearchCpf clientSearchCpf;
    private final ClientRepository clientRepository;

    public ClientController(JWTTokenHelper jwtTokenHelper, ClientService clientService, ClientSearchCpf clientSearchCpf,
                            ClientRepository clientRepository) {
        this.jwtTokenHelper = jwtTokenHelper;
        this.clientService = clientService;
        this.clientSearchCpf = clientSearchCpf;
        this.clientRepository = clientRepository;
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro durante o salvamento do cliente.");
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @PostMapping
    public ResponseEntity<String> clientSave(@RequestBody @Valid ClientDto clientDto) {

        try {
            String clientCPF = clientDto.getCpf();
//            boolean existingCpf = clientSearchCpf.findByCpf(clientDto.getCpf());
            String url = "http://localhost:9090/search/pesquisa/" + clientCPF;
            ResponseEntity<?> responseEntity = restTemplate().getForEntity(url, Client.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok().body(
                        String.format("Não foi possível cadastrar o cliente: " + clientDto.getName() + ", pois, o CPF:" + clientDto.getCpf() + " já existe na base de dados."));
            }
            Client client = new Client();
            ModelMapper modelMapper = new ModelMapper();
            client = modelMapper.map(clientDto, Client.class);
            clientService.clientSave(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    String.format(clientDto.getName() + ": cadastrado com sucesso. \n Cadastro realizado em: " + clientDto.getDataRegister()));

        } catch (HttpClientErrorException.NotFound e) {
            Client cl = new Client();
            ModelMapper mp = new ModelMapper();
            cl = mp.map(clientDto, Client.class);
            clientService.clientSave(cl);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Cliente cadsatrado com sucesso: " + clientDto.getName() + "."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    String.format("Não foi possível cadastrar o cliente: %s.", clientDto.getName()));
        }
    }

    @ExceptionHandler
    @GetMapping
    public List<Client> clientList() throws Exception {
        return clientService.clientList();

    }

    @ExceptionHandler
    @PutMapping("/{id}")
    public ResponseEntity<Client> clientUpdate(@PathVariable Long id, @RequestBody Client client)
            throws Exception {
        client.setId(id);
        return ResponseEntity.ok().body(clientService.clientUpdate(client));
    }

    @ExceptionHandler
    @DeleteMapping("/{id}")
    public HttpStatus clientDelete(@PathVariable Long id) throws Exception {
        this.clientService.clientDelete(id);
        return HttpStatus.OK;
    }

//    @GetMapping("/search/{cpf}")
//    public ResponseEntity<?> pesquisa(@PathVariable String cpf) {
//        Client client = clientService.findByCpf(cpf);
//        if (client != null) {
//            return ResponseEntity.ok(client);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @GetMapping("/find/{id}")
//    public Client searchClientById(@PathVariable Long id) {
//        return clientService.findClientById(id);
//    }


}
