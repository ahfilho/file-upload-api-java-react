package br.com.api.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "al_product")
public class Product {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_type")
	private String productType;

	@OneToOne(mappedBy = "produtoImg", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	private ImgProdutoModel imgProduto;

	@OneToOne(mappedBy = "produtosCategoria")
	private CategoryProduct categoriaProduto;

}
