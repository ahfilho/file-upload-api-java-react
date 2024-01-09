package br.com.api.repository;

import br.com.api.entity.ImgRam;
import br.com.api.entity.ImgSsd;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.Ram;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RamRepository extends JpaRepository<Ram, Long> {


    @Query("SELECT c.purchaseDate, CURRENT_DATE, AGE(CURRENT_DATE, c.purchaseDate) AS diferenca " +
            "FROM Ram c " +
            "WHERE AGE(CURRENT_DATE, c.purchaseDate) >= :diferencaLimite")
    public List<String> dataDeVendaRam();


    @Query("SELECT f FROM ImgRam f WHERE f.fileName = :fileName")
    List<ImgRam> findByName(@Param("fileName") String fileName);



}
