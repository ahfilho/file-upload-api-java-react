package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.WhatsappRegistrationModel;

public interface WhatsappRegistrationRepository extends JpaRepository<WhatsappRegistrationModel, Long> {

}
