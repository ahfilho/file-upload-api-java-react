package br.com.api.repository;

import java.util.List;

import br.com.api.entity.CpuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.entity.ImgSsd;
import org.springframework.data.repository.query.Param;

public interface SsdFileRepository extends JpaRepository<ImgSsd, Long> {

	@Query(value = "SELECT * FROM ImgSsd", nativeQuery = true)
	public List<ImgSsd> consulta_personalizada();

	@Query(value = "SELECT * FROM ImgSsd", nativeQuery = true)
	public List<ImgSsd> terca();

	void save(CpuCategory cpm);
	@Query("SELECT f FROM ImgSsd f WHERE f.fileName = :fileName")
	List<ImgSsd> deleteByName(@Param("fileName") String fileName);


}
