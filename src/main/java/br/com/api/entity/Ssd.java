package br.com.api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "SSD")
@Entity
public class Ssd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SSD_ID")
    private Long id;

    @Column(name = "BRAND")
    @NotNull
    private String brand;

    @Column(name = "SERIAL_NUMBER", length = 17)
    @NotNull
    private String serialNumber;

    @NotNull
    @Column(name = "SIZE_STORAGE", length = 4)
    private String size;

    @NotNull
    @Column(name = "PURCHASE_PRICE")
    private String purchasePrice;

    @NotNull
    @Column(name = "PURCHASE_DATE")
    private String purchaseDate;

    @NotNull
    @Column(name = "SALE_VALUE")
    private float saleValue;

    @Column(name = "ARRIVAL_DATE")
    private String arrivalDate;

    @Column(name = "URL")
    private String url;

    @Column(name = "AMOUNT")
    private int amount;

    @NotNull
    @Column(name = "MODEL")
    private String model;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SSD_ID")
    private ImgSsd imgSsd;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SSD_ID")
    private ProductCategorySsd productCategorySsd;

}



















