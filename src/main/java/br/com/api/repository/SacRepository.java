package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.Contact;

public interface SacRepository extends JpaRepository<Contact, Long> {

	

}
