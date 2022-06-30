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

import br.com.api.entity.WhatsappRegistrationModel;
import br.com.api.service.WhatsappService;

@RestController
@RequestMapping("/cadwhats")
public class WhatsappRegistrationController {

	@Autowired
	private WhatsappService service;

	@PostMapping
	public ResponseEntity<WhatsappRegistrationModel> whatsappSave(@RequestBody WhatsappRegistrationModel whatsModel) {
		return ResponseEntity.ok().body(this.service.whatsappSave(whatsModel));
	}

	@GetMapping
	public ResponseEntity<List<WhatsappRegistrationModel>> whatsappList() {
		return ResponseEntity.ok().body(this.service.whatsappList());
	}

	@PutMapping("/{id}")
	public ResponseEntity<WhatsappRegistrationModel> whatsappUpdate(@PathVariable Long id,
			@RequestBody WhatsappRegistrationModel whats) throws Exception {
		whats.setId(id);
		return ResponseEntity.ok().body(this.service.whaytsappUpdate(whats));
	}

	@DeleteMapping("/{id}")
	public HttpStatus whatsappDelete(@PathVariable Long id) throws Exception {
		this.service.delete(id);
		return HttpStatus.OK;
	}

}
