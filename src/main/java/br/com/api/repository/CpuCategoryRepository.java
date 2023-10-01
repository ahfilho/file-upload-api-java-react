package br.com.api.repository;

import br.com.api.entity.CpuCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpuCategoryRepository extends JpaRepository<CpuCategory, Long>{

}
