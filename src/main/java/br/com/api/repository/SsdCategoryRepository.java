package br.com.api.repository;

import br.com.api.entity.Ssd;
import br.com.api.entity.SsdCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SsdCategoryRepository extends JpaRepository<SsdCategory, Long> {
}
