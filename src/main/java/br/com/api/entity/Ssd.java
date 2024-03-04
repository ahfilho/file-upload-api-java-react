package br.com.api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    @NotBlank
    private String brand;

    @Column(name = "SERIAL_NUMBER", length = 17)
    @NotBlank
    private String serialNumber;

    @NotBlank
    @Column(name = "SIZE_STORAGE", length = 4)
    private String size;

    @NotBlank
    @Column(name = "PURCHASE_PRICE")
    private String purchasePrice;

    @NotBlank
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

    @NotBlank
    @Column(name = "MODEL")
    private String model;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ssdFile")
    private ImgSsd imgSsd;

    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_CATEGORY_SSD_ID")
    private ProductCategorySsd productCategorySsd;

    public void setImgSsd(ImgSsd imgSsd) {
        this.imgSsd = imgSsd;
        imgSsd.setSsdFile(this);
    }

}



















