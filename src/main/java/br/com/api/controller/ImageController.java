package br.com.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.entity.Category;
import br.com.api.entity.Image;
import br.com.api.entity.Ssd;
import br.com.api.entity.ImageResponse;
import br.com.api.service.OfferImageService;

@RestController
@RequestMapping("/files")
public class ImageController {
//
//    @Autowired
//    private OfferImageService ofertasService;
//
//    /*
//     * Envia mais de um arquivo por requisição; vai ficar aqui caso precise.
//     *
//     * @PostMapping public ResponseEntity<String>
//     * uploadImgOferta(@RequestParam("file") MultipartFile[] file, ProdutoModel pm,
//     * CategoriaProdutoModel cpm) {
//     *
//     * try { ofertasService.saveFile(file, pm, cpm);
//     *
//     * return ResponseEntity.status(HttpStatus.OK)
//     * .body(String.format("sucesso no upload")); } catch (Exception e) { return
//     * ResponseEntity.status(HttpStatus.OK)
//     * .body(String.format("falha no upload %s"));
//     *
//     * } }
//     */
//
//    // Envia apenas um arquivo por requisição
//
//    // SALVA UMA IMAGEM, UM PRODUTO E UMA CATEGORIA
//    @PostMapping
//    public ResponseEntity<String> uploadImgOferta(@RequestParam("file") MultipartFile file, Ssd ssd,
//                                                  Category category) {
//
//        try {
//            ofertasService.saveFile(file, ssd, category);
//
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(String.format("sucesso no upload", file.getOriginalFilename()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(String.format("falha no upload %s", file.getOriginalFilename()));
//
//        }
//    }
//
//    @GetMapping("terca")
//    // @Scheduled(fixedRate = 2000)
//    public List<ImageResponse> teste3() {
//        return ofertasService.terca().stream().map(this::mapToQuery).collect(Collectors.toList());
//    }
//
//    private ImageResponse mapTo_Terca_Feira(Image img) {
//        long l1 = img.getId();
//        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//                .path(Long.toString(l1)).toUriString();
//        ImageResponse iqr = new ImageResponse();
//        iqr.setId(img.getId());
//        iqr.setName(img.getName());
//        iqr.setContentType(img.getContentType());
//        // iqr.setSize(img.getSize());
//        iqr.setUrl(downloadURL);
//        return iqr;
//    }
//
//
//    @GetMapping("teste")
//    // @Scheduled(fixedRate = 2000) // Vou usar isso para exibir as determinadas
//    // consultas de acordo com o dia da semana.
//    public List<ImageResponse> teste2() {
//        return ofertasService.getSql().stream().map(this::mapToQuery).collect(Collectors.toList());
//    }
//
//    private ImageResponse mapToQuery(Image img) {
//
//        long l1 = img.getId();
//        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//                .path(Long.toString(l1)).toUriString();
//        ImageResponse iqr = new ImageResponse();
//        iqr.setId(img.getId());
//        iqr.setName(img.getName());
//        iqr.setContentType(img.getContentType());
//        iqr.setUrl(downloadURL);
//        return iqr;
//    }
//
//    @GetMapping
//    public List<ImageResponse> list() {
//        return ofertasService.getAllFiles().stream().map(this::mapToFileResponse).collect(Collectors.toList());
//    }
//
//    private ImageResponse mapToFileResponse(Image ofertasModel) {
//
//        long l2 = ofertasModel.getId();
//        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//                .path(Long.toString(l2)).toUriString();
//        ImageResponse offer = new ImageResponse();
//        offer.setId(ofertasModel.getId());
//        offer.setName(ofertasModel.getName());
//        offer.setContentType(ofertasModel.getContentType());
//        offer.setSize(ofertasModel.getSize());
//        offer.setUrl(downloadURL);
//
//        return offer;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
//        Optional<Image> file = ofertasService.getFile(id);
//        if (!file.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        Image imageOffer = file.get();
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "teste de upload/ filename=" + imageOffer.getName() + "\"")
//                .contentType(MediaType.valueOf(imageOffer.getContentType())).body(imageOffer.getData());
//
//    }
//
//    @DeleteMapping("/{id}")
//    public HttpStatus deleta(@PathVariable Long id) throws Exception {
//        ofertasService.imgDeleteDiretory(id);
//        this.ofertasService.imgDelete(id);
//        return HttpStatus.OK;
//    }
//
//    //TODO
//    // VERIFICAR AQUI DEPOIS
//    @PutMapping("/{id}")
//    public ResponseEntity<String> uploadImgOferta(@RequestParam("file") MultipartFile file) {
//
//        try {
//            ofertasService.updateImg(file, null);
//
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(String.format("sucesso no upload", file.getOriginalFilename()));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.OK)
//                    .body(String.format("falha no upload %s", file.getOriginalFilename()));
//
//        }
//    }
}