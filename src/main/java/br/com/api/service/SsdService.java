package br.com.api.service;

import java.io.File;
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

import br.com.api.entity.Img;
import br.com.api.enume.CategoryEnum;
import br.com.api.enume.Condition;
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

    private final Path root = Paths.get("uploads/");
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

    public void saveSsd(Ssd ssd, MultipartFile file, Category category)
            throws IOException {
        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        Img img = new Img();
        category.setProductCategory(String.valueOf(CategoryEnum.SSD));
        img.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        img.setContentType(file.getContentType());
        img.setData(file.getBytes());
        img.setFileSize(file.getSize());

        this.ssdRepository.save(ssd);
        this.categoryRepository.save(category);
        this.fileRepository.save(img);

    }

    public List<Ssd> listAllSsd() {
        List<Ssd> ssdALl = ssdRepository.findAll();
        for (Ssd s : ssdALl
        ) {
            System.out.println(s.getBrand());
        }
        return ssdALl;
    }

    //Não tem uso
    public Ssd updateSsd(Ssd ssd) throws Exception {
        Optional<Ssd> result = this.ssdRepository.findById(ssd.getId());
        if (result.isPresent()) {
            Ssd pm = result.get();
            pm.setModel(ssd.getModel());
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
            objSsdAux.setPurchasePrice(ssd.getPurchasePrice());
            objSsdAux.setPurchaseDate(ssd.getPurchaseDate());
            objSsdAux.setSaleValue(ssd.getSaleValue());
            objSsdAux.setSerialNumber(ssd.getSerialNumber());
            objSsdAux.setSize(ssd.getSize());

            Img filee = new Img();

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
        Optional<Ssd> product = ssdRepository.findById(id);
        if (product.isPresent()) {
            Ssd ssd = product.get();
            deleteFile(ssd.getImg().getFileName());
            ssdRepository.delete(ssd);
            fileRepository.delete(ssd.getImg());
        } else {
            throw new Exception("Produto não encontrado");
        }
    }

    private void deleteFile(String fileName) {
        List<Img> imgList = fileRepository.deleteByName(fileName);

        for (Img img : imgList) {
            fileRepository.delete(img);
            String imgFileName = img.getFileName();
            Path physicalFilePath = root.resolve(imgFileName);
            File physicalFile = physicalFilePath.toFile();
            if (physicalFile.exists()) {
                File fil = new File("uploads");
                physicalFile.delete();
            }
//            File f = new File(String.valueOf(root));
//            if (f.isDirectory()) {
//                File[] fi = f.listFiles();
//
//                // Excluindo arquivos no diretório correspondentes aos objetos Img
//                for (File ff : fi) {
//                    for (Img im : imgList) {
//                        if (ff.getName().equals(im.getFileName())) {
//                            ff.delete();
//                        }
//                    }
//                }
//            }
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
