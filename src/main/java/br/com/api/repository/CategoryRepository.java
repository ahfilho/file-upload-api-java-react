package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.CategoryProduct;

public interface CategoryRepository extends JpaRepository<CategoryProduct, Long>{

}
