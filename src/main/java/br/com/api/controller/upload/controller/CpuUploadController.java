package br.com.api.controller.upload.controller;

import br.com.api.entity.*;
import br.com.api.service.CpuFileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/files/cpu/")
public class CpuUploadController {

    /*
     * Envia mais de um arquivo por requisição; vai ficar aqui caso precise.
     *
     * @PostMapping public ResponseEntity<String>
     * uploadImgOferta(@RequestParam("file") MultipartFile[] file, ProdutoModel pm,
     * CategoriaProdutoModel cpm) {
     *
     * try { ofertasService.saveFile(file, pm, cpm);
     *
     * return ResponseEntity.status(HttpStatus.OK)
     * .body(String.format("sucesso no upload")); } catch (Exception e) { return
     * ResponseEntity.status(HttpStatus.OK)
     * .body(String.format("falha no upload %s"));
     *
     * } }
     */

    private final CpuFileService cpuFileService;

    public CpuUploadController(CpuFileService cpuFileService) {
        this.cpuFileService = cpuFileService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFileCpu(@RequestParam("file") MultipartFile file, Cpu cpu,
                                                ProductCategorySsd productCategorySsd) {
        try {
            cpuFileService.saveFile(file, cpu, productCategorySsd);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("sucesso no upload", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("falha no upload %s", file.getOriginalFilename()));

        }
    }

    @GetMapping
    public List<FileResponse> list() {
        return cpuFileService.getAllFiles().stream().map(this::mapToFileResponse).collect(Collectors.toList());
    }

    private FileResponse mapToFileResponse(ImgCpu imgCpu) {

        long l2 = imgCpu.getId();
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                .path(Long.toString(l2)).toUriString();
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(imgCpu.getId());
        fileResponse.setFileName(imgCpu.getFileName());
        fileResponse.setContentType(imgCpu.getContentType());
        fileResponse.setSize(imgCpu.getFileSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<ImgCpu> file = cpuFileService.getFile(id);
        if (!file.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ImgCpu imgCpu = file.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "teste de upload/ filename=" + imgCpu.getFileName() + "\"")
                .contentType(MediaType.valueOf(imgCpu.getContentType())).body(imgCpu.getData());

    }

}

