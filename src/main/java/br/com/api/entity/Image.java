package br.com.api.entity;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "C_IMAGE")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "size")
    private Long size;

    @Column(name = "url")
    private String url;

    @Lob
    @JsonIgnore
    private byte[] data;

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
