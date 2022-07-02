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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_product_id")
	private ProductImage imgProduct;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_product_id")
	private Category categoryProduct;

}
