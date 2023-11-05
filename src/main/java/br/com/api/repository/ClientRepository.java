package br.com.api.repository;

import br.com.api.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client, Long> {


    @Query("SELECT x FROM Client x INNER JOIN Address a ON x.id = a.id")
    public List<Client> b();

    @Query("SELECT c FROM Client c WHERE c.cpf = ?1")
    public Client clientWithSameCpf(String cpf);

    @Query("SELECT c FROM Client c WHERE c.cpf = ?1")
    public Client find(String cpf);


}
