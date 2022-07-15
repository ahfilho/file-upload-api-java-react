package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.Ssd;

public interface ProductRepository extends JpaRepository<Ssd, Long> {


   // @Query("SELECT a from al_product inner join categoria ON (categoria.id = al_product.id) inner join image ON (imagem.id = al_product.id)")
   // public List<Product> teste();
   // public List<Product> teste();
}
