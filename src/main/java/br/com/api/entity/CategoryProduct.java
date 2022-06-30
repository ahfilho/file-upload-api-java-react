package br.com.api.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "al_categoria")
public class CategoryProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_category")
	private String productCategory;

	@OneToOne
	@JoinColumn(name = "product_category")
	private Product produtosCategoria;

}