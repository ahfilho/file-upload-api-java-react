package br.com.api.controller;

import java.util.List;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.repository.ClientRepository;
import br.com.api.search.ClientSearchCpf;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import br.com.api.entity.Client;
import br.com.api.service.ClientService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/client")
public class ClientController {

    private final JWTTokenHelper jwtTokenHelper;
    private final ClientService clientService;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @PostMapping
    public ResponseEntity<String> clientSave(@RequestBody Client client) {

//        ModelMapper mapper = new ModelMapper();
//        ClientDto clientDto = mapper.map(client, ClientDto.class);
//        Address address = client.getAddress();
//        AddressDto addressDto = new AddressDto();
//        clientDto.setDataRegister(LocalDate.now());
//        addressDto.setStreet(address.getStreet());
//        addressDto.setNumber(address.getNumber());
//        addressDto.setCity(address.getCity());
//        addressDto.setDistrict(address.getDistrict());


        try {
            boolean existingCpf = clientSearchCpf.findByCpf(client.getCpf());
            if (existingCpf) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Não foi possível cadastrar o cliente: " + client.getName() + ", pois, o CPF:" + client.getCpf() + " já existe na base de dados."));
            } else {
                clientService.clientSave(client);
                return ResponseEntity.status(HttpStatus.OK).body(String.format(client.getName() + " cadastrado com sucesso. \n Cadastro realizado em: " + client.getDataRegister()));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Não foi possível cadastrar o cliente: " + client.getName() + "."));
        }
    }

    @ExceptionHandler
    @GetMapping
    public ResponseEntity<List<Client>> clientList() throws Exception {
        return ResponseEntity.ok().body(clientService.clientList());

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


}
