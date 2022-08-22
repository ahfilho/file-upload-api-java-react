package br.com.api.controller;

import java.util.List;

import br.com.api.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.api.entity.Client;
import br.com.api.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> clientSave(@RequestBody Client client) {
        try {
            this.clientService.clientSave(client);
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Cliente: " + client.getName() + " cadastrado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(String.format("Não foi possível cadastrar o cliente: " + client.getName()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Client>> clientList() {
        return ResponseEntity.ok().body(this.clientService.clientList());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> clientUpdate(@PathVariable Long id, @RequestBody Client client)
            throws Exception {
        client.setId(id);
        return ResponseEntity.ok().body(this.clientService.clientUpdate(client));
    }

    @DeleteMapping("/{id}")
    public HttpStatus clientDelete(@PathVariable Long id) throws Exception {
        this.clientService.clientDelete(id);
        return HttpStatus.OK;
    }
}
