package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
