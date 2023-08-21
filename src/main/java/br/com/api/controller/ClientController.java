package br.com.api.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.api.auth.JWTTokenHelper;
import br.com.api.dto.AddressDto;
import br.com.api.dto.ClientDto;
import br.com.api.entity.Address;
import br.com.api.entity.Ssd;
import br.com.api.exceptions.ErrorHandling;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.modelmapper.ModelMapper;
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

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro durante o salvamento do cliente.");
    }

    @ExceptionHandler
    @PostMapping
    public ResponseEntity<String> clientSave(@RequestBody Client client) {

        ModelMapper mapper = new ModelMapper();
        ClientDto clientDto = mapper.map(client, ClientDto.class);

        Address address = client.getAddress();
        AddressDto addressDto = new AddressDto();

        clientDto.setDataRegister(LocalDate.now());
        addressDto.setStreet(address.getStreet());
        addressDto.setNumber(address.getNumber());
        addressDto.setCity(address.getCity());
        addressDto.setDistrict(address.getDistrict());

        try {
            clientService.clientSave(clientDto);
            return ResponseEntity.status(HttpStatus.OK).body(String.format(client.getName() + " cadastrado com sucesso. \n Cadastro realizado em: " + clientDto.getDataRegister()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Não foi possível cadastrar o cliente: " + clientDto.getName() + "."));
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
