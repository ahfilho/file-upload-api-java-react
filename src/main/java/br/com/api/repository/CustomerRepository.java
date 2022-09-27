package br.com.api.repository;

import br.com.api.entity.CustomerSale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerSale, Long> {
}
