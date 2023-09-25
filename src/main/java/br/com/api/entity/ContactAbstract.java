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
@Table(name = "contact")
public abstract class ContactAbstract {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "NAME")
	private String name;
	
	@NotNull
	@Column(name = "EMAIL")
	private String email;

	@NotNull
	@Column(name = "PHONE")
	private String phone;

	@NotNull
	@Column(name = "MESSAGE")
	private String message;

}
