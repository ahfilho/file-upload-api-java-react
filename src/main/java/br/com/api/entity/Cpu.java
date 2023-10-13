package br.com.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@Data
@Entity
@Table(name = "CPU")
@NoArgsConstructor
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CPU_ID")
    private Long id;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "PURCHASE_PRICE")
    private float purchasePrice;

    @Column(name = "PURCHASE_DATE")
    private String purchaseDate;

    @Column(name = "SALE_VALUE")
    private float saleValue;

    @Column(name = "ARRIVAL_DATE")
    private String arrivalDate;

    @Column(name = "CORE")
    private int coreCount;

    @Column(name = "THREADS")
    private int threadCount;

    @Column(name = "CLOCK")
    private float clockCount;

    @Column(name = "URL")
    private String url;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CPU_ID")
    private ImgCpu imgCpu;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CPU_ID")
    private ProductCategoryCpu cpuCategory;

}