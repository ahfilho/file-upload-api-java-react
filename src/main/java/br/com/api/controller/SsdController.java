package br.com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.api.entity.Category;
import br.com.api.entity.ImageResponse;
import br.com.api.entity.Image;
import br.com.api.entity.Ssd;
import br.com.api.service.SsdService;

@RestController
@RequestMapping("/ssd")
public class SsdController {

    @Autowired
    private SsdService productService;

    @PostMapping
    public ResponseEntity<String> productSaveUploadImgCategory(@RequestParam("file") MultipartFile file,
                                                               Ssd ssd, Category category) {
        try {
            productService.saveProductFileCategory(ssd, file, category);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("sucesso no upload %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("Falha no upload %s", file.getOriginalFilename()));
        }
    }

    @GetMapping
    public List<Ssd> list() {
        return productService.listAllSsd();
    }

    private ImageResponse testaMap(Image imgModel) {
        long l1 = imgModel.getId();
        String download = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files").path(Long.toString(l1))
                .toUriString();
        ImageResponse imgOferta = new ImageResponse();
        imgOferta.setId(imgModel.getId());
        imgOferta.setName(imgModel.getName());
        imgOferta.setContentType(imgModel.getContentType());
        imgOferta.setSize(imgModel.getSize());
        imgOferta.setUrl(download);

        return imgOferta;
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProduct(@PathVariable Long id) throws Exception {
        productService.deleteProduct(id);
        return HttpStatus.OK;
    }
}