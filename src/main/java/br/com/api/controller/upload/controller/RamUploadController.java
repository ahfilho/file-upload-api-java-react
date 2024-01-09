package br.com.api.controller.upload.controller;

import br.com.api.entity.*;
import br.com.api.service.RamFileService;
import br.com.api.service.RamService;
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
@RequestMapping("/files/ram/")
public class RamUploadController {
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
    private final RamService ramService;

    private final RamFileService ramFileService;

    public RamUploadController(RamService ramService, RamFileService ramFileService) {
        this.ramService = ramService;
        this.ramFileService = ramFileService;
    }


    @PostMapping
    public ResponseEntity<String> uploadFileRam(@RequestParam("file") MultipartFile file, Ram ram,
                                                ProductCategoryRam productCategory) {
        try {
            ramService.ramSave(ram, file, productCategory);

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
    public List<FileResponse> listarTodosRam() {
        return ramFileService.getAllFiles().stream().map(this::mapToFileResponseRam).collect(Collectors.toList());
    }

    private FileResponse mapToFileResponseRam(ImgRam imgRam) {

        long l2 = imgRam.getId();
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/ram/")
                .path(Long.toString(l2)).toUriString();
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(imgRam.getId());
        fileResponse.setFileName(imgRam.getFileName());
        fileResponse.setContentType(imgRam.getContentType());
        fileResponse.setSize(imgRam.getFileSize());
        fileResponse.setUrl(downloadURL);

        return fileResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFileRam(@PathVariable Long id) {
        Optional<ImgRam> file = ramFileService.getFile(id);
        if (!file.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ImgRam imgRam = file.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "teste de upload/ filename=" + imgRam.getFileName() + "\"")
                .contentType(MediaType.valueOf(imgRam.getContentType())).body(imgRam.getData());

    }

}


