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

import br.com.api.entity.Contact;
import br.com.api.service.ContactService;

@RestController
@RequestMapping("cadcontato")
public class ContactController {

	@Autowired
	private ContactService servico;

	@PostMapping
	public ResponseEntity<Contact> contactSave(@RequestBody Contact contactModel) {
		return ResponseEntity.ok().body(this.servico.contactSave(contactModel));

	}

	@GetMapping
	public ResponseEntity<List<Contact>> contactList() {
		return ResponseEntity.ok().body(this.servico.contactList());

	}

	@PutMapping("/{id}")
	public ResponseEntity<Contact> contactUpdate(@PathVariable Long id, @RequestBody Contact contactModel)
			throws Exception {
		contactModel.setId(id);
		return ResponseEntity.ok().body(this.servico.updateContact(contactModel));
	}

	@DeleteMapping("/{id}")
	public HttpStatus contactDelete(@PathVariable long id) throws Exception {
		this.servico.contactDelete(id);
		return HttpStatus.OK;
	}
}
