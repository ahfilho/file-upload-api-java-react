package br.com.api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.api.FilePath;
import br.com.api.entity.ImgRam;
import br.com.api.entity.ProductCategoryRam;
import br.com.api.enume.CategoryEnum;
import br.com.api.repository.*;
import org.springframework.stereotype.Service;

import br.com.api.entity.Ram;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class RamService {

    private final Path rootRam = Paths.get("uploads/ram");

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

    public void ramSave(Ram ram, MultipartFile file, ProductCategoryRam productCategory) throws IOException {

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

    public Ram ramUpdateService(Ram ram, MultipartFile file, ProductCategoryRam productCategoryRam) throws Exception {
        Optional<Ram> optionalRam = this.ramRepository.findById(ram.getId());

        Files.copy(file.getInputStream(), this.rootRam.resolve(file.getOriginalFilename()));

        if (optionalRam.isPresent()) {
            Ram target = optionalRam.get();
            copyProperties(ram, target);

            ImgRam oldImgRam = target.getImgRam();
            ImgRam newImgRam = new ImgRam();

            initializeImgRam(file, newImgRam);

            if (oldImgRam != null) {
                newImgRam.setId(oldImgRam.getId());
            }
            target.setImgRam(newImgRam);

            Ram updateRam = ramRepository.save(target);
            deleteFilesNotInDatabase();

            return updateRam;
        } else {
            throw new Exception("Erro durante a atualização para o ID:" + ram.getId());
        }
    }

    private void copyProperties(Ram source, Ram target) {
        target.setArrivalDate(source.getArrivalDate());
        target.setBrand(source.getBrand());
        target.setMhz(source.getMhz());
        target.setModel(source.getModel());
        target.setSize(source.getSize());
        target.setSize(source.getSize());
        target.setSerialNumber(source.getSerialNumber());
        target.setSaleValue(source.getSaleValue());
        target.setPurchasePrice(source.getPurchasePrice());

    }

    private void initializeImgRam(MultipartFile file, ImgRam imgRam) throws IOException {
        imgRam.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imgRam.setContentType(file.getContentType());
        imgRam.setData(file.getBytes());
        imgRam.setFileSize(file.getSize());
    }

    public void delete(Long id) throws Exception {
        Optional<Ram> optionalRam = ramRepository.findById(id);
        if (optionalRam.isPresent()) {
            Ram ram = optionalRam.get();
            if (ram.getImgRam().getFileName() != null) {
                String fileName = ram.getImgRam().getFileName();
                System.out.println(ram.getImgRam().getFileName());
                deleteFile(fileName);

                ramRepository.delete(optionalRam.get());
                ramFileRepository.delete(ram.getImgRam());
            } else {
                throw new Exception("ERRO AO DELETAR O ID" + id);
            }
        }
    }

    private void deleteFile(String fileName) {
        List<ImgRam> imgRamList = ramRepository.findByName(fileName);

        for (ImgRam imgRam : imgRamList) {

            ramFileRepository.delete(imgRam);
            String imgFileName = imgRam.getFileName();
            Path physicalFilePath = rootRam.resolve(imgFileName);
            File physicalFile = physicalFilePath.toFile();
            File[] files = new File(String.valueOf(FilePath.rootRam)).listFiles();
            System.out.println(Arrays.toString(files));
            System.out.println();
            for (File f : files) {
                System.out.println(f.getName());
            }
            if (physicalFile.exists()) {
                File fil = new File("uploads/ram");
                physicalFile.delete();
            }

        }
    }

    public void deleteFilesNotInDatabase() {
        List<String> databaseFileNames = ramFileRepository.ramTodosArquivos();
        File[] filesInDirectory = new File("uploads/ram").listFiles();

        if (filesInDirectory != null) {
            for (File file : filesInDirectory) {
                String fileNameInDirectory = file.getName();

                if (!databaseFileNames.contains(fileNameInDirectory)) {
                    // O arquivo no diretório não está presente no banco de dados, então exclua
                    file.delete();
                    System.out.println("Arquivo excluído: " + fileNameInDirectory);
                }
            }
        }
    }

    public Ram searchId(Long id) throws Exception {
        Optional<Ram> result = ramRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception("Erro ao buscar o Id" + id);
        }
    }

    public List<String> dayOfSaleRam() {
        List<String> ls = ramRepository.dataDeVendaRam();
        return ls;
    }
}

