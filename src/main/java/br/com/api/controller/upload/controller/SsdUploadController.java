package br.com.api.controller.upload.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.api.entity.*;
import br.com.api.service.SsdFileService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/files/ssd/")
public class SsdUploadController {

    /*
     * Envia mais de um arquivo por requisição; vai ficar aqui caso precise.
     *
     * @PostMapping public ResponseEntity<String>
     * uploadImgOferta(@RequestParam("file") MultipartFile[] file, ProdutoModel pm,
     * CategoriaProdutoModel cpm) {
     * try { ofertasService.saveFile(file, pm, cpm);
     * return ResponseEntity.status(HttpStatus.OK)
     * .body(String.format("sucesso no upload")); } catch (Exception e) { return
     * ResponseEntity.status(HttpStatus.OK)
     * .body(String.format("falha no upload %s"));
     *
     * } }
     */
    private final SsdFileService ssdFileService;

    public SsdUploadController(SsdFileService ssdFileService) {
        this.ssdFileService = ssdFileService;
    }


    @PostMapping
    public ResponseEntity<String> uploadFileSsd(@RequestParam("file") MultipartFile file, Ssd ssd,
                                                SsdCategory ssdCategory) {
        try {
            ssdFileService.saveFile(file, ssd, ssdCategory);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("sucesso no upload", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("falha no upload %s", file.getOriginalFilename()));

        }
    }

//    @GetMapping("teste1")
//    // @Scheduled(fixedRate = 2000)
//    public List<FileResponse> ssdList() {
//        return ssdFileService.terca().stream().map(this::mapToQuery).collect(Collectors.toList());
//    }

//    private FileResponse mapToList(ImgSsd img) {
//        long l1 = img.getId();
//        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/ssd/")
//                .path(Long.toString(l1)).toUriString();
//        FileResponse iqr = new FileResponse();
//        iqr.setId(img.getId());
//        iqr.setFileName(img.getFileName());
//        iqr.setContentType(img.getContentType());
//        iqr.setUrl(downloadURL);
//        return iqr;
//    }


//    @GetMapping("teste2")
//    public List<FileResponse> ssdList2() {
//        return ssdFileService.getSql().stream().map(this::mapToQuery).collect(Collectors.toList());
//    }
//
//    private FileResponse mapToQuery(ImgSsd imgSsd) {
//
//        long l1 = imgSsd.getId();
//        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/ssd/")
//                .path(Long.toString(l1)).toUriString();
//        FileResponse iqr = new FileResponse();
//        iqr.setId(imgSsd.getId());
//        iqr.setFileName(imgSsd.getFileName());
//        iqr.setContentType(imgSsd.getContentType());
//        iqr.setUrl(downloadURL);
//        return iqr;
//    }

    @GetMapping
    public List<FileResponse> listarTodosSsd() {
        return ssdFileService.getAllFiles().stream().map(this::mapToFileResponseSsd).collect(Collectors.toList());
    }

    private FileResponse mapToFileResponseSsd(ImgSsd imgSsd) {

        long l2 = imgSsd.getId();
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/ssd/")
                .path(Long.toString(l2)).toUriString();
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(imgSsd.getId());
        fileResponse.setFileName(imgSsd.getFileName());
        fileResponse.setContentType(imgSsd.getContentType());
        fileResponse.setSize(imgSsd.getFileSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFileSsd(@PathVariable Long id) {
        Optional<ImgSsd> file = ssdFileService.getFile(id);
        if (!file.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ImgSsd imageOffer = file.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "teste de upload/ filename=" + imageOffer.getFileName() + "\"")
                .contentType(MediaType.valueOf(imageOffer.getContentType())).body(imageOffer.getData());

    }

}