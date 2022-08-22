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
@Table(name = "image")
public class Image implements Serializable {

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

    @OneToOne
    @JoinColumn(name = "ssd_id")
    private Ssd ssd;

    @OneToOne
    @JoinColumn(name = "ram_id")
    private Ram ram;
}
