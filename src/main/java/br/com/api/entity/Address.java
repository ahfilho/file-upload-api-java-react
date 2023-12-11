package br.com.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "ADDRESS")
@Entity
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "street")
    @NotNull
    private String street;

    @Column(name = "number")
    @NotNull
    private String number;

    @Column(name = "district")
    @NotNull
    private String district;

    @Column(name = "city")
    @NotNull
    private String city;


}
