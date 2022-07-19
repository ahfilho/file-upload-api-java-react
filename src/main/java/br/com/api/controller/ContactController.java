package br.com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.entity.Client;
import br.com.api.service.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService servico;

    @PostMapping
    public ResponseEntity<Client> contactSave(@RequestBody Client contactModel) {
        return ResponseEntity.ok().body(this.servico.contactSave(contactModel));

    }

    @GetMapping
    public ResponseEntity<List<Client>> contactList() {
        return ResponseEntity.ok().body(this.servico.contactList());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> contactUpdate(@PathVariable Long id, @RequestBody Client contactModel)
            throws Exception {
        contactModel.setId(id);
        return ResponseEntity.ok().body(this.servico.updateContact(contactModel));
    }

    @DeleteMapping("/{id}")
    public HttpStatus contactDelete(@PathVariable Long id) throws Exception {
        this.servico.contactDelete(id);
        return HttpStatus.OK;
    }
}
