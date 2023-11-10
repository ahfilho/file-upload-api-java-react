package br.com.api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "RAM")
@Entity
public class Ram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RAM_ID")
    private Long id;

    @Column(name = "BRAND")
    @NotNull
    private String brand;

    @Column(name = "MHZ")
    @NotNull
    private int mhz;

    @Column(name = "SIZE", length = 4)
    @NotNull
    private int size;

    @NotNull
    @Column(name = "PURCHASE_PRICE")
    private float purchasePrice;

    @NotNull
    @Column(name = "PURCHASE_DATE")
    private String purchaseDate;

    @NotNull
    @Column(name = "SALE_VALUE")
    private float saleValue;

    @NotNull
    @Column(name = "ARRIVAL_DATE")
    private String arrivalDate;

    @NotNull
    @Column(name = "MODEL")
    private String model;

    @Column(name = "URL")
    private String url;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "RAM_ID")
    private ImgRam imgRam;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "RAN_ID")
    private ProductCategory productCategory;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "RAM_ID")
//    private ImgSsd image;
//
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "RAM_ID")
//    private ProductCategory productCategory;

}

