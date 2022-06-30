package br.com.api.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "al_imagem")
public class ImgProdutoModel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "content_type")
	private String contentType;

	@Column(name = "size")
	private Long size;

	@Column(name = "url")
	private String url;

	@Lob
	private byte[] data;

	@OneToOne
	@JoinColumn(name = "imagem_name")
	private Product produtoImg;

}
