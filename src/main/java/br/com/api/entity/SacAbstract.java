package br.com.api.entity;

import javax.persistence.*;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "al_sac")
public abstract class SacAbstract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Column(name = "name")
	private String name;
	
	@NotNull
	@Column(name = "email")
	private String email;

	@NotNull
	@Column(name = "telephone")
	private String telephone;

	@NotNull
	@Column(name = "message")
	private String message;

}
