package br.com.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

import com.sun.istack.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLIENT_ID")
    private Long id;

//    @NotNull
    @Column(name = "name")
    private String name;

//    @NotNull
    @Email
    @Column(name = "email")
    private String email;

//    @NotNull
    @Column(name = "cpf")
    private String cpf;

//    @NotNull
    @Column(name = "contact")
    private int contact;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CLIENT_ID")
    private Address address;


}
