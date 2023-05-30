package br.com.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;

import br.com.api.entity.File;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.entity.Category;
import br.com.api.entity.Ssd;
import br.com.api.repository.CategoryRepository;
import br.com.api.repository.SsdRepository;
import br.com.api.repository.FileRepository;

@Transactional
@Service
public class SsdService {

    private final Path root = Paths.get("uploads");
    private final SsdRepository ssdRepository;
    private final CategoryRepository categoryRepository;
    private final FileRepository fileRepository;

    public SsdService(SsdRepository ssdRepository, CategoryRepository categoryRepository, FileRepository fileRepository) {
        this.ssdRepository = ssdRepository;
        this.categoryRepository = categoryRepository;
        this.fileRepository = fileRepository;

    }

    public void init() {

        try {
            Files.createDirectory(root);

        } catch (IOException e) {
            throw new RuntimeException("erro ao inicializar o diretório");
        }
    }

    public void saveProductFileCategory(Ssd ssd, MultipartFile file, Category category)
            throws IOException {
        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        File ff = new File();

        ff.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        ff.setContentType(file.getContentType());
        ff.setData(file.getBytes());
        ff.setFileSize(file.getSize());

        this.ssdRepository.save(ssd);
        this.categoryRepository.save(category);
        this.fileRepository.save(ff);

    }

    public List<Ssd> listAllSsd() {
        List<Ssd> ssdALl = ssdRepository.findAll();
        for (Ssd s : ssdALl
        ) {
            System.out.println(s.getCategory());
        }
        return ssdALl;
    }

    //Não tem uso
    public Ssd updateProduct(Ssd ssd) throws Exception {
        Optional<Ssd> opt = this.ssdRepository.findById(ssd.getId());
        if (opt.isPresent()) {
            Ssd pm = opt.get();
            pm.setModel(ssd.getModel());
            pm.setCategory(ssd.getCategory());
            pm.setModel(ssd.getModel());
            return pm;
        } else {
            throw new Exception("Erro ao atualizar o produto, categoria e a imagem." + ssd.getId());
        }
    }

    public Ssd update(Ssd ssd, MultipartFile file, Category category) throws Exception {
        Optional<Ssd> optSsd = this.ssdRepository.findById(ssd.getId());
        if (optSsd.isPresent()) {

            Ssd objSsdAux = optSsd.get();
            objSsdAux.setBrand(ssd.getBrand());
            objSsdAux.setModel(ssd.getModel());
            objSsdAux.setArrivalDate(ssd.getArrivalDate());
            objSsdAux.setCategory(ssd.getCategory());
            objSsdAux.setPurchasePrice(ssd.getPurchasePrice());
            objSsdAux.setPurchaseDate(ssd.getPurchaseDate());
            objSsdAux.setSaleValue(ssd.getSaleValue());
            objSsdAux.setSerialNumber(ssd.getSerialNumber());
            objSsdAux.setSize(ssd.getSize());

            File filee = new File();

            filee.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
            filee.setContentType(file.getContentType());
            filee.setData(file.getBytes());
            filee.setFileSize(file.getSize());

            this.ssdRepository.save(objSsdAux);
            this.categoryRepository.save(category);
            this.fileRepository.save(filee);

            return objSsdAux;
        } else {
            throw new Exception("Erro durante a atualização." + ssd.getId());
        }

    }

    public void deleteProduct(Long id) throws Exception {
        Optional<Ssd> product = this.ssdRepository.findById(id);
        if (product.isPresent()) {
            this.ssdRepository.delete(product.get());
        } else {
            throw new Exception("Erro ao deletar");
        }

    }

    public Ssd searchId(Long id) throws Exception {
        Optional<Ssd> result = this.ssdRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception("ERRO O BUSCAR PELO ID" + id);
        }
    }

    public List<String> dayOfSale() {
        List<String> total = ssdRepository.dataDeVenda();
        List<String> result = new ArrayList<>();

        for (String dateString : total) {
            LocalDate dateInicial = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dateAtual = LocalDate.now();
            long diferencaEmDias = ChronoUnit.DAYS.between(dateInicial, dateAtual);

            if (diferencaEmDias >= 90) {
                result.add(dateString);
                System.out.println(dateString + "igual ou maior que 90 dias");
            }
        }

        return result;
    }

}
