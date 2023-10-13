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

import br.com.api.entity.ImgSsd;
import br.com.api.entity.ProductCategorySsd;
import br.com.api.repository.ProductCategoryRepositorySsd;
import br.com.api.repository.SsdFileRepository;
import org.springframework.stereotype.Service;

import br.com.api.entity.Ram;
import br.com.api.repository.RamRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class RamService {

    public final Path root = Paths.get("uploads");


    private RamRepository ramRepository;
    private SsdFileRepository offerImageRepository;

    private final ProductCategoryRepositorySsd productCategoryRepositorySsd;

    public RamService(RamRepository ramRepository, SsdFileRepository offerImageRepository, ProductCategoryRepositorySsd productCategoryRepositorySsd) {
        this.ramRepository = ramRepository;
        this.offerImageRepository = offerImageRepository;
        this.productCategoryRepositorySsd = productCategoryRepositorySsd;
    }

    public void ramSave(Ram ram, MultipartFile file, ProductCategorySsd productCategorySsd) throws IOException {

        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));

        Date dateAtual = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = df.format(dateAtual);
//        ram.setArrivalDate(dateAtual);
//        ram.setPurchaseDate(dateAtual);

        ImgSsd img = new ImgSsd();

        img.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        img.setContentType(file.getContentType());
        img.setData(file.getBytes());
        img.setFileSize(file.getSize());
        this.ramRepository.save(ram);
        this.offerImageRepository.save(img);
        this.productCategoryRepositorySsd.save(productCategorySsd);
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

