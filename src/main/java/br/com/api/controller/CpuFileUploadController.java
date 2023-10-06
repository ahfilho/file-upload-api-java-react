package br.com.api.controller;

import br.com.api.entity.*;
import br.com.api.service.CpuFileService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CpuFileUploadController {


        @Autowired
        private CpuFileService cpuFileService;

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

        // Envia apenas um arquivo por requisição

        // SALVA UMA IMAGEM, UM PRODUTO E UMA CATEGORIA
        @PostMapping
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, Cpu cpu,
                                                 CpuCategory cpuCategory) {

            try {
                cpuFileService.saveFile(file, cpu, cpuCategory);

                return ResponseEntity.status(HttpStatus.OK)
                        .body(String.format("sucesso no upload", file.getOriginalFilename()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(String.format("falha no upload %s", file.getOriginalFilename()));

            }
        }

        @GetMapping("terca")
        // @Scheduled(fixedRate = 2000)
        public List<FileResponse> teste3() {
            return cpuFileService.terca().stream().map(this::mapToQuery).collect(Collectors.toList());
        }

        private FileResponse mapTo_Terca_Feira(ImgCpu imgCpu) {
            long l1 = imgCpu.getId();
            String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                    .path(Long.toString(l1)).toUriString();
            FileResponse iqr = new FileResponse();
            iqr.setId(imgCpu.getId());
            iqr.setFileName(imgCpu.getFileName());
            iqr.setContentType(imgCpu.getContentType());
            iqr.setUrl(downloadURL);
            return iqr;
        }


        @GetMapping("teste")
        // @Scheduled(fixedRate = 2000) // Vou usar isso para exibir as determinadas
        // consultas de acordo com o dia da semana.
        public List<FileResponse> teste2() {
            return cpuFileService.getSql().stream().map(this::mapToQuery).collect(Collectors.toList());
        }

        private FileResponse mapToQuery(ImgCpu imgCpu) {

            long l1 = imgCpu.getId();
            String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                    .path(Long.toString(l1)).toUriString();
            FileResponse iqr = new FileResponse();
            iqr.setId(imgCpu.getId());
            iqr.setFileName(imgCpu.getFileName());
            iqr.setContentType(imgCpu.getContentType());
            iqr.setUrl(downloadURL);
            return iqr;
        }

        @GetMapping
        public List<FileResponse> list() {
            return cpuFileService.getAllFiles().stream().map(this::mapToFileResponse).collect(Collectors.toList());
        }

        private FileResponse mapToFileResponse(ImgCpu imgCpu) {

            long l2 = imgCpu.getId();
            String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
                    .path(Long.toString(l2)).toUriString();
            FileResponse offer = new FileResponse();
            offer.setId(imgCpu.getId());
            offer.setFileName(imgCpu.getFileName());
            offer.setContentType(imgCpu.getContentType());
            offer.setSize(imgCpu.getFileSize());
            offer.setUrl(downloadURL);

            return offer;
        }

        @GetMapping("/{id}")
        public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
            Optional<ImgCpu> file = cpuFileService.getFile(id);
            if (!file.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            ImgCpu imageOffer = file.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "teste de upload/ filename=" + imageOffer.getFileName() + "\"")
                    .contentType(MediaType.valueOf(imageOffer.getContentType())).body(imageOffer.getData());

        }

        @DeleteMapping("/{id}")
        public HttpStatus deleta(@PathVariable Long id) throws Exception {
            cpuFileService.imgDeleteDiretory(id);
            this.cpuFileService.imgDelete(id);
            return HttpStatus.OK;
        }
        @PutMapping("/{id}")
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {

            try {
                cpuFileService.updateImg(file, null);

                return ResponseEntity.status(HttpStatus.OK)
                        .body(String.format("sucesso no upload", file.getOriginalFilename()));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(String.format("falha no upload %s", file.getOriginalFilename()));

            }
        }
    }

