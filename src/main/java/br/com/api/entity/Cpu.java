package br.com.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Data
@Entity
@Table(name = "T_CPU")
@NoArgsConstructor
public class Cpu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CPU_ID")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "serial_number")
    private int serial_number;

    @Column(name = "model")
    private String model;

    @Column(name = "purchase_price")
    private float purchase_price;

    @Column(name = "purchase_date")
    private Date purchase_date;

    @Column(name = "sale_value")
    private float sale_value;

    @Column(name = "arrival_date")
    private Date arrival_date;

    @Column(name = "cores")
    private int cores;

    @Column(name = "threads")
    private int threads;

    @Column(name = "clock")
    private float clock;

    @Column(name = "overclock")
    private boolean overclock;

    @Column(name = "url")
    private String url;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CPU_ID")
    private Image image;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CPU_ID")
    private Category category;


}
