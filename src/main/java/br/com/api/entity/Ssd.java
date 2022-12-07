package br.com.api.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @NotNull
    @Column(name = "brand")
    private String brand;

    @NotNull
    @Column(name = "serial_number", length = 17)
    private String serialNumber;

    @NotNull
    @Column(name = "size_storage", length = 4)
    private String size;

    @NotNull
    @Column(name = "purchase_price")
    private String purchasePrice;

    @NotNull
    @Column(name = "purchase_date")
    private String purchaseDate;

    @NotNull
    @Column(name = "sale_value")
    private float saleValue;
    @NotNull
    @Column(name = "arrival_date")
    private String arrivalDate;

    @Column(name = "url")
    private String url;

    @NotNull
    @Column(name = "model")
    private String model;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SSD_ID")
    private Image image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SSD_ID")
    private Category category;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPU_ID")
    private Cpu cpu;
}
