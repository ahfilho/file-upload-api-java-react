package br.com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.api.entity.CategoryProduct;
import br.com.api.entity.ImgProdutoModel;
import br.com.api.entity.Product;

public interface OfferImageRepository extends JpaRepository<ImgProdutoModel, Long> {

	@Query(value = "SELECT * FROM imagem", nativeQuery = true)
	public List<ImgProdutoModel> consulta_personalizada();

	@Query(value = "SELECT * FROM product", nativeQuery = true)
	public List<ImgProdutoModel> terca();

	void save(Product pm);

	void save(CategoryProduct cpm);

}
