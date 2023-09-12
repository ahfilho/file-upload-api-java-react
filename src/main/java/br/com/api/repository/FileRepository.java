package br.com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.entity.Category;
import br.com.api.entity.Img;
import br.com.api.entity.Ssd;
import org.springframework.data.repository.query.Param;

public interface FileRepository extends JpaRepository<Img, Long> {

	@Query(value = "SELECT * FROM imagem", nativeQuery = true)
	public List<Img> consulta_personalizada();

	@Query(value = "SELECT * FROM image", nativeQuery = true)
	public List<Img> terca();

	void save(Ssd pm);

	void save(Category cpm);
	@Query("SELECT f FROM Img f WHERE f.fileName = :fileName")
	List<Img> findByName(@Param("fileName") String fileName);


}
