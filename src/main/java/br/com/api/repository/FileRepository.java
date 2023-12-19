package br.com.api.repository;

import java.util.List;

import br.com.api.dto.ImgSsdDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.entity.ImgSsd;
import br.com.api.entity.Ssd;
import org.springframework.data.repository.query.Param;

public interface FileRepository extends JpaRepository<ImgSsd, Long> {

    @Query(value = "SELECT * FROM ImgSsd", nativeQuery = true)
    public List<ImgSsd> consulta_personalizada();

    @Query(value = "SELECT * FROM ImgSsd", nativeQuery = true)
    public List<ImgSsd> terca();

    void save(Ssd pm);

    void save(ProductCategoryRepositorySsd cpm);

    @Query("SELECT f FROM ImgSsd f WHERE f.fileName = :fileName")
    List<ImgSsd> findByName(@Param("fileName") String fileName);

    @Query("SELECT f.fileName FROM ImgSsd f WHERE f.id = :ssdId")
    public Long findFileNameBySsdId(@Param("ssdId") Long ssdId);


    @Query("SELECT f.fileName FROM ImgSsd f WHERE f.fileName = :fileName")
    List<ImgSsd> findFileForName(@Param("fileName") String fileName);

    @Query("SELECT f.fileName from ImgSsd f WHERE f.id = :ssdId")
    public String findFileNameById(@Param("ssdId") Long ssdId);

    @Query("SELECT f.fileName FROM ImgSsd f")
    List<String> todosArquivos();
}
