package br.com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.entity.Category;
import br.com.api.entity.ProductImage;
import br.com.api.entity.Product;

public interface OfferImageRepository extends JpaRepository<ProductImage, Long> {

	@Query(value = "SELECT * FROM imagem", nativeQuery = true)
	public List<ProductImage> consulta_personalizada();

	@Query(value = "SELECT * FROM product", nativeQuery = true)
	public List<ProductImage> terca();

	void save(Product pm);

	void save(Category cpm);

}
