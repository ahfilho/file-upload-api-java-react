package br.com.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

//@AllArgsConstructor
//@NoArgsConstructor
@Data
@Entity
@Table(name = "al_cpu")
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "serial_number")
    private int serial_number ;

    @Column(name = "model")
    private String model;

    @Column(name = "purchase_price")
    private float purchase_price;

    @Column(name = "purchase_date")
    private Date purchase_date;

    @Column(name = "sale_value")
    private float sale_value;

    @Column(name = "arrival_date")
    private Date arrival_date ;

    @Column(name = "cores")
    private int cores;

    @Column(name = "threads")
    private int threads;

    @Column(name = "clock")
    private int clock;

    @Column(name = "overclock")
    private boolean overclock;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_product_id")
    private Image imgProduct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_product_id")
    private Category categoryProduct;
}
