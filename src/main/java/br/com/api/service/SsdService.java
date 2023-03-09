package br.com.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;

import br.com.api.controller.FileUploadController;
import br.com.api.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SsdRepository ssdRespository;
    @Autowired
    private CategoryRepository catRepository;
    @Autowired
    private FileRepository offerImageRepository;

    @Autowired
    private FileUploadController imageController;

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

        Date dateAtual = new Date();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //String dataFormatada = df.format(dateAtual);

        //DEPOIS VERIFICAR AS DATAS
//        ssd.setPurchaseDate(dateAtual);
//        ssd.setArrivalDate(dateAtual);


        ff.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        ff.setContentType(file.getContentType());
        ff.setData(file.getBytes());
        ff.setFileSize(file.getSize());

        this.ssdRespository.save(ssd);
        this.catRepository.save(category);
        this.offerImageRepository.save(ff);

    }

    public List<Ssd> listAllSsd() {
        List<Ssd> ssdALl = ssdRespository.findAll();
        for (Ssd s : ssdALl
        ) {
            System.out.println(s.getCategory());
            //System.out.println(s.getImage().getName());

        }
        return ssdALl;
    }


    //Não tem uso
    public Ssd updateProduct(Ssd ssd) throws Exception {
        Optional<Ssd> opt = this.ssdRespository.findById(ssd.getId());
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
        Optional<Ssd> optSsd = this.ssdRespository.findById(ssd.getId());
        if (optSsd.isPresent()) {

            Ssd objSsdAux = optSsd.get();
            objSsdAux.setBrand(ssd.getBrand());
            objSsdAux.setModel(ssd.getModel());
            objSsdAux.setArrivalDate(ssd.getArrivalDate());
            objSsdAux.setCategory(ssd.getCategory());
            objSsdAux.setPurchasePrice(ssd.getPurchasePrice());
            objSsdAux.setSaleValue(ssd.getSaleValue());
            objSsdAux.setSerialNumber(ssd.getSerialNumber());
            objSsdAux.setSize(ssd.getSize());

            File filee = new File();

            filee.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
            filee.setContentType(file.getContentType());
            filee.setData(file.getBytes());
            filee.setFileSize(file.getSize());

            this.ssdRespository.save(objSsdAux);
            this.catRepository.save(category);
            this.offerImageRepository.save(filee);

            return objSsdAux;
        } else {
            throw new Exception("Erro durante a atualização." + ssd.getId());
        }

    }

    public void deleteProduct(Long id) throws Exception {
        Optional<Ssd> product = this.ssdRespository.findById(id);
        if (product.isPresent()) {
            this.ssdRespository.delete(product.get());
        } else {
            throw new Exception("Erro ao deletar");
        }

    }

    public Ssd searchId(Long id) throws Exception {
        Optional<Ssd> result = this.ssdRespository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception("ERRO O BUSCAR PELO ID" + id);
        }
    }

}
