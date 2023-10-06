package br.com.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "FILE_CPU")
public class ImgCpu {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @Column(name = "FILE_SIZE")
    private Long fileSize;

    @JsonIgnore
    @Column(name = "URL_FILE_CPU")
    private String urlCpu;

    @Lob
    @JsonIgnore
    private byte[] data;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CPU_ID")
    private Cpu cpu;


}
