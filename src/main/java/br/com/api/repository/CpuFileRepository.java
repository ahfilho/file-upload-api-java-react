package br.com.api.repository;

import br.com.api.entity.Category;
import br.com.api.entity.ImgSsd;
import br.com.api.entity.ImgCpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CpuFileRepository extends JpaRepository<ImgCpu, Long> {

    @Query(value = "SELECT * FROM ImgCpu", nativeQuery = true)
    public List<ImgCpu> consulta_personalizada();

    @Query(value = "SELECT * FROM ImgCpu", nativeQuery = true)
    public List<ImgCpu> terca();

    void save(Category cpm);
    @Query("SELECT f FROM ImgCpu f WHERE f.fileName = :fileName")
    List<ImgCpu> deleteByName(@Param("fileName") String fileName);

}
