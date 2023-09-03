package br.com.api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "SALE_SALUE")
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SSD_ID")
    private Img img;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SSD_ID")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPU_ID")
    private Cpu cpu;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SSD_ID")
    private Client client;
}
