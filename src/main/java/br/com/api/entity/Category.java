package br.com.api.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "C_CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_category")
    private String productCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SSD_ID")
    private Ssd ssd;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RAM_ID")
    private Ram ram;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPU_ID")
    private Cpu cpu;


}