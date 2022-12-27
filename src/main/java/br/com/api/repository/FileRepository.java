package br.com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.entity.Category;
import br.com.api.entity.File;
import br.com.api.entity.Ssd;

public interface FileRepository extends JpaRepository<File, Long> {

	@Query(value = "SELECT * FROM imagem", nativeQuery = true)
	public List<File> consulta_personalizada();

	@Query(value = "SELECT * FROM image", nativeQuery = true)
	public List<File> terca();

	void save(Ssd pm);

	void save(Category cpm);

}
