package br.com.api.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "PRODUCT_CATEGORY_CPU")
public class ProductCategoryCpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "CPU_ID")
    @Column(name = "CPU_PRODUCT_CATEGORY")
    private String productCategory;


}