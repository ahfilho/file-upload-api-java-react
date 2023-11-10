package br.com.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.api.entity.ImgRam;
import br.com.api.entity.ProductCategory;
import br.com.api.enume.CategoryEnum;
import br.com.api.repository.*;
import org.springframework.stereotype.Service;

import br.com.api.entity.Ram;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class RamService {

    public final Path rootRam = Paths.get("uploads/ram");

    private final RamRepository ramRepository;
    private final RamFileRepository ramFileRepository;
    private final ProductCategoryRepositoryRam productCategoryRepositoryRam;

    private final RamFileRepository fileRepository;

    public RamService(RamRepository ramRepository, RamFileRepository ramFileRepository, ProductCategoryRepositorySsd productCategoryRepositorySsd, ProductCategoryRepositoryRam productCategoryRepositoryRam, FileRepository fileRepository, RamFileRepository fileRepository1) {
        this.ramRepository = ramRepository;
        this.ramFileRepository = ramFileRepository;
        this.productCategoryRepositoryRam = productCategoryRepositoryRam;
        this.fileRepository = fileRepository1;
    }

    public void ramSave(Ram ram, MultipartFile file, ProductCategory productCategory) throws IOException {

        Files.copy(file.getInputStream(), this.rootRam.resolve(file.getOriginalFilename()));
        productCategory.setProductCategory(String.valueOf(CategoryEnum.RAM));

        Date dateAtual = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = df.format(dateAtual);
//        ram.setArrivalDate(dateAtual);
//        ram.setPurchaseDate(dateAtual);

        ImgRam imgRam = new ImgRam();

        imgRam.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imgRam.setContentType(file.getContentType());
        imgRam.setData(file.getBytes());
        imgRam.setFileSize(file.getSize());

        ram.setImgRam(imgRam);

        this.ramRepository.save(ram);
        this.ramFileRepository.save(imgRam);
    }

    public List<Ram> ramList() {
        return this.ramRepository.findAll();
    }

    public Ram ramUpdate(Ram ram) throws Exception {
        Optional<Ram> whatsOptional = this.ramRepository.findById(ram.getId());
        if (whatsOptional.isPresent()) {
            Ram ramAux = whatsOptional.get();
            ramAux.setBrand(ram.getBrand());
            ramAux.setModel(ram.getModel());
            ramAux.setMhz(ram.getMhz());
            ramAux.setSize(ram.getSize());
            ramAux.setPurchasePrice(ram.getPurchasePrice());
            ramAux.setSaleValue(ram.getSaleValue());
            ramAux.setArrivalDate(ram.getArrivalDate());
            ramAux.setPurchaseDate(ram.getPurchaseDate());

            return ramAux;
        } else {
            throw new Exception("ERRO AO ATUALIZAR A RAM" + ram.getId());
        }
    }

    public void delete(Long id) throws Exception {
        Optional<Ram> whats = this.ramRepository.findById(id);
        if (whats.isPresent()) {
            this.ramRepository.delete(whats.get());
        } else {
            throw new Exception("ERRO AO DELETAR O ID" + id);
        }
    }
}

