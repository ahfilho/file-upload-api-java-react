package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.Ram;

public interface RamRepository extends JpaRepository<Ram, Long> {

}
