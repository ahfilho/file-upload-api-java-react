package br.com.api.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "al_ssd")
public class Ssd {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "brand")
	private String brand;

	@Column(name = "serial_number", length = 17)
	private String serial_number;

	@Column(name = "size_storage", length = 4)
	private int size;

	@Column(name = "purchase_price")
	private float purchase_price;

	@Column(name = "purchase_date")
	private Date purchase_date;

	@Column(name = "sale_value")
	private float sale_value;

	@Column(name = "arrival_date")
	private Date arrival_date;

	@Column(name = "model")
	private String model;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_product_id")
	private Image imgProduct;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_product_id")
	private Category categoryProduct;

}
