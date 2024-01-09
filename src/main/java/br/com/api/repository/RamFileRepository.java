package br.com.api.repository;

import java.util.List;

import br.com.api.entity.ImgRam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.entity.ImgSsd;
import org.springframework.data.repository.query.Param;

public interface RamFileRepository extends JpaRepository<ImgRam, Long> {

	@Query(value = "SELECT * FROM ImgRam", nativeQuery = true)
	public List<ImgRam> consulta_personalizada();

	@Query(value = "SELECT * FROM ImgRam", nativeQuery = true)
	public List<ImgRam> terca();

	void save(ProductCategoryRepositoryRam cpm);
	@Query("SELECT f FROM ImgRam f WHERE f.fileName = :fileName")
	List<ImgRam> deleteByName(@Param("fileName") String fileName);

	@Query("SELECT f.fileName FROM ImgRam f")
	List<String> ramTodosArquivos();

}
