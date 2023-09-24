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

import br.com.api.entity.ImgSsd;
import br.com.api.enume.CategoryEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.entity.Category;
import br.com.api.entity.Ssd;
import br.com.api.repository.CategoryRepository;
import br.com.api.repository.SsdRepository;
import br.com.api.repository.SsdFileRepository;

@Transactional
@Service
public class SsdService {

    private final Path rootSsd = Paths.get("uploads/ssd");
    private final SsdRepository ssdRepository;
    private final CategoryRepository categoryRepository;
    private final SsdFileRepository ssdFileRepository;

    public SsdService(SsdRepository ssdRepository, CategoryRepository categoryRepository, SsdFileRepository fileRepository) {
        this.ssdRepository = ssdRepository;
        this.categoryRepository = categoryRepository;
        this.ssdFileRepository = fileRepository;

    }

    public void init() {

        try {
            Files.createDirectory(rootSsd);
        } catch (IOException e) {
            throw new RuntimeException("erro ao inicializar o diretório");
        }
    }

    public void saveSsd(Ssd ssd, MultipartFile file, Category category)
            throws IOException {
        Files.copy(file.getInputStream(), this.rootSsd.resolve(file.getOriginalFilename()));
        ImgSsd imgSsd = new ImgSsd();
        category.setProductCategory(String.valueOf(CategoryEnum.SSD));
        imgSsd.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imgSsd.setContentType(file.getContentType());
        imgSsd.setData(file.getBytes());
        imgSsd.setFileSize(file.getSize());

        this.ssdRepository.save(ssd);
        this.categoryRepository.save(category);
        this.ssdFileRepository.save(imgSsd);

    }

    public List<Ssd> listAllSsd() {
        return ssdRepository.findAll();

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

            ImgSsd filee = new ImgSsd();

            filee.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
            filee.setContentType(file.getContentType());
            filee.setData(file.getBytes());
            filee.setFileSize(file.getSize());

            this.ssdRepository.save(objSsdAux);
            this.categoryRepository.save(category);
            this.ssdFileRepository.save(filee);

            return objSsdAux;
        } else {
            throw new Exception("Erro durante a atualização." + ssd.getId());
        }

    }

    public void deleteProduct(Long id) throws Exception {
        Optional<Ssd> product = ssdRepository.findById(id);
        if (product.isPresent()) {
            Ssd ssd = product.get();
            deleteFile(ssd.getImgSsd().getFileName());
            ssdRepository.delete(ssd);
            ssdFileRepository.delete(ssd.getImgSsd());
        } else {
            throw new Exception("Produto não encontrado");
        }
    }

    private void deleteFile(String fileName) {
        List<ImgSsd> imgList = ssdFileRepository.deleteByName(fileName);

        for (ImgSsd img : imgList) {
            ssdFileRepository.delete(img);
            String imgFileName = img.getFileName();
            Path physicalFilePath = rootSsd.resolve(imgFileName);
            File physicalFile = physicalFilePath.toFile();
            if (physicalFile.exists()) {
                File fileSsdImg = new File("uploads");
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
