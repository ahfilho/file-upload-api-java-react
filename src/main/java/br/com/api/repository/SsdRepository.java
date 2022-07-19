package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.Ssd;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SsdRepository extends JpaRepository<Ssd, Long> {



    @Query(value = "SELECT s FROM Ssd s inner join category (category.id = ssd.id) inner join image (image.id = ssd.id)",nativeQuery = true)
    public List<Ssd> teste();
}
