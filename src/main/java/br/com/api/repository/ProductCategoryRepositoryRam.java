package br.com.api.repository;

import br.com.api.entity.ProductCategoryRam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepositoryRam extends JpaRepository<ProductCategoryRam, Long> {
}
