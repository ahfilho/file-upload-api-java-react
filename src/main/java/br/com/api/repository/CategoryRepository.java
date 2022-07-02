package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
