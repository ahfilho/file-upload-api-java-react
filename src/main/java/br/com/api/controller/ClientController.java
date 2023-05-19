package br.com.api.controller;

import java.util.Collections;
import java.util.List;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.entity.Address;
import br.com.api.entity.Ssd;
import br.com.api.exceptions.ErrorHandling;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.api.entity.Client;
import br.com.api.service.ClientService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private ClientService clientService;

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
        // Lógica para lidar com a exceção
        // Pode retornar uma ResponseEntity com o status HTTP e uma mensagem de erro
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro durante o salvamento do cliente.");
    }

    @ExceptionHandler
    @PostMapping
    public ResponseEntity<String> clientSave(@RequestBody Client client) {
        try {
            if (client.getName().equals("") ||
                    client.getCpf().equals("") ||
                    client.getEmail().equals("") ||
                    client.getContact().equals("") ||
                    client.getAddress().getStreet().equals("") ||
                    client.getAddress().getNumber().equals("") ||
                    client.getAddress().getCity().equals("") ||
                    client.getAddress().getDistrict().equals(""))
            {
                return ResponseEntity.status(HttpStatus.OK).body(String.format("Os campos não podem ser vazios."));

            }
            clientService.clientSave(client);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Cliente: " + client.getName() + " cadastrado com sucesso!"));
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
