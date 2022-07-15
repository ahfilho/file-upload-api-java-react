package br.com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.entity.Category;
import br.com.api.entity.Image;
import br.com.api.entity.Ssd;

public interface OfferImageRepository extends JpaRepository<Image, Long> {

	@Query(value = "SELECT * FROM imagem", nativeQuery = true)
	public List<Image> consulta_personalizada();

	@Query(value = "SELECT * FROM product", nativeQuery = true)
	public List<Image> terca();

	void save(Ssd pm);

	void save(Category cpm);

}
