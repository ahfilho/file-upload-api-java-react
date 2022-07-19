package br.com.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Data
@Entity
@Table(name = "cpu")
@NoArgsConstructor
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
    private float clock;

    @Column(name = "overclock")
    private boolean overclock;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "name")
    private Image image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category")
    private Category category;

}
