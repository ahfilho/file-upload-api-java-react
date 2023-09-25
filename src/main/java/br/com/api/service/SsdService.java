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
import java.util.Arrays;
import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;

import br.com.api.FilePath;
import br.com.api.entity.ImgSsd;
import br.com.api.enume.CategoryEnum;
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

    private final Path rootSsd = Paths.get("uploads/ssd");
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
            Files.createDirectory(rootSsd);
        } catch (IOException e) {
            throw new RuntimeException("erro ao inicializar o diretório");
        }
    }

    public void saveSsd(Ssd ssd, MultipartFile file, Category category)
            throws IOException {
        Files.copy(file.getInputStream(), this.rootSsd.resolve(file.getOriginalFilename()));
        ImgSsd img = new ImgSsd();
        category.setProductCategory(String.valueOf(CategoryEnum.SSD));
        img.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        img.setContentType(file.getContentType());
        img.setData(file.getBytes());
        img.setFileSize(file.getSize());

        ssd.setImgSsd(img);

        this.ssdRepository.save(ssd);
        this.categoryRepository.save(category);
//        this.fileRepository.save(img);

    }

    public List<Ssd> listAllSsd() {
        return ssdRepository.findAll();

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
            this.fileRepository.save(filee);

            return objSsdAux;
        } else {
            throw new Exception("Erro durante a atualização." + ssd.getId());
        }

    }

    public void deleteProduct(Long id) throws Exception {
        Optional<Ssd> ssdOptional = ssdRepository.findById(id);
        if (ssdOptional.isPresent()) {
            Ssd ssd = ssdOptional.get();
            if (ssd.getImgSsd().getFileName() != null) {
                String fileName = ssd.getImgSsd().getFileName();

                System.out.println(ssd.getImgSsd().getFileName());

                deleteFile(fileName);
                ssdRepository.delete(ssdOptional.get());
                fileRepository.delete(ssd.getImgSsd());
            } else {
                throw new Exception("Produto não encontrado");
            }
        }
    }


    private void deleteFile(String fileName) {
        List<ImgSsd> imgList = fileRepository.findByName(fileName);

        for (ImgSsd img : imgList) {
            fileRepository.delete(img);
            String imgFileName = img.getFileName();
            Path physicalFilePath = rootSsd.resolve(imgFileName);
            File physicalFile = physicalFilePath.toFile();
            File[] files = new File(String.valueOf(FilePath.rootSsd)).listFiles();
            System.out.println(Arrays.toString(files));
            System.out.println();

            for (File f : files
            ) {

                System.out.println(f.getName());

            }
            if (physicalFile.exists()) {
                File fil = new File("uploads/ssd");
                physicalFile.delete();
            }
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
