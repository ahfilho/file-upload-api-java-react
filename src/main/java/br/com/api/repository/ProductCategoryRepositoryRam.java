package br.com.api.repository;

import br.com.api.entity.ProductCategory;
import br.com.api.entity.Ram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepositoryRam extends JpaRepository<ProductCategory, Long> {
}
