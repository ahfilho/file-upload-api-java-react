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
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import br.com.api.FilePath;
import br.com.api.dto.ProductCategorySsdDto;
import br.com.api.entity.ImgSsd;
import br.com.api.entity.ProductCategorySsd;
import br.com.api.enume.CategoryEnum;
import br.com.api.repository.ProductCategoryRepositorySsd;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.entity.Ssd;
import br.com.api.repository.SsdRepository;
import br.com.api.repository.FileRepository;

@Transactional
@Service
public class SsdService {

    private final Path rootSsd = Paths.get("uploads/ssd");
    private final SsdRepository ssdRepository;
    private final ProductCategoryRepositorySsd productCategoryRepositorySsd;
    private final FileRepository fileRepository;

    public SsdService(SsdRepository ssdRepository, ProductCategoryRepositorySsd productCategoryRepositorySsd, FileRepository fileRepository) {
        this.ssdRepository = ssdRepository;
        this.productCategoryRepositorySsd = productCategoryRepositorySsd;
        this.fileRepository = fileRepository;

    }

    public void initalizeDirectory() {

        try {
            Files.createDirectory(rootSsd);
        } catch (IOException e) {
            throw new RuntimeException("erro ao inicializar o diretório");
        }
    }

    @Transactional
    public void serviceSaveSsd(Ssd ssd, MultipartFile file, ProductCategorySsd productCategorySsd )
            throws IOException {
        Files.copy(file.getInputStream(), this.rootSsd.resolve(file.getOriginalFilename()));
        ImgSsd imgSsd = new ImgSsd();


        productCategorySsd.setProductCategory(String.valueOf(CategoryEnum.SSD));

        imgSsd.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imgSsd.setContentType(file.getContentType());
        imgSsd.setData(file.getBytes());
        imgSsd.setFileSize(file.getSize());

        ssd.setImgSsd(imgSsd);
        ssd.setProductCategorySsd(productCategorySsd);
        this.ssdRepository.save(ssd);

    }

    public List<Ssd> serviceListAllSsd() {
        return ssdRepository.findAll();

    }

    public Ssd serviceUpdateSsd(Ssd ssd, MultipartFile file, ProductCategorySsd productCategorySsd) throws Exception {
        Optional<Ssd> optionalSsd = this.ssdRepository.findById(ssd.getId());

        Files.copy(file.getInputStream(), this.rootSsd.resolve(file.getOriginalFilename()));

        if (optionalSsd.isPresent()) {
            Ssd target = optionalSsd.get();
            copySsdProperties(ssd, target);

            // Obtem o ImgSsd anterior
            ImgSsd oldImgSsd = target.getImgSsd();

            // Associa o novo arquivo
            ImgSsd newImgSsd = new ImgSsd();
            initializeImgSsd(file, newImgSsd);

            // Define o mesmo ID do ImgSsd anterior no novo ImgSsd
            if (oldImgSsd != null) {
                newImgSsd.setId(oldImgSsd.getId());
            }
            // Associa o novo ImgSsd ao Ssd ---------
            target.setImgSsd(newImgSsd);

            // Salva o objeto atualizado com a nova imagem
            Ssd updatedSsd = this.ssdRepository.save(target);
            deleteFilesNotInDatabase();

            // Retorna o objeto atualizado
            return updatedSsd;
        } else {
            throw new Exception("Erro durante a atualização. ID: " + ssd.getId());
        }
    }

//
//    private void deleteOldFile(ImgSsd oldFileSsd) throws IOException {
//        if (oldFileSsd != null && oldFileSsd.getId() != null) {
//            Long ssdId = oldFileSsd.getId();
//
//            // Encontre o nome do arquivo antigo e seu ID em uma única consulta
//            String fileName = fileRepository.findFileNameById(ssdId);
//
//            if (fileName != null) {
//                // Deleta o arquivo do diretório
//                deleteFile(fileName);
//
//                // Deleta o arquivo do repositório
//                fileRepository.delete(oldFileSsd);
//            }
//        }
//    }

    private void copySsdProperties(Ssd source, Ssd target) {
        target.setBrand(source.getBrand());
        target.setModel(source.getModel());
        target.setArrivalDate(source.getArrivalDate());
        target.setPurchasePrice(source.getPurchasePrice());
        target.setPurchaseDate(source.getPurchaseDate());
        target.setSaleValue(source.getSaleValue());
        target.setSerialNumber(source.getSerialNumber());
        target.setSize(source.getSize());
    }

    private void initializeImgSsd(MultipartFile file, ImgSsd imgSsd) throws IOException {

        imgSsd.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imgSsd.setContentType(file.getContentType());
        imgSsd.setData(file.getBytes());
        imgSsd.setFileSize(file.getSize());


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
    public void deleteFilesNotInDatabase() {
        List<String> databaseFileNames = fileRepository.todosArquivos();
        File[] filesInDirectory = new File("uploads/ssd").listFiles();

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



    public Ssd searchId(Long id) throws Exception {
        Optional<Ssd> result = this.ssdRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        } else {
            throw new Exception("ERRO O BUSCAR PELO ID" + id);
        }
    }

    public List<String> dayOfSale() {
        List<String> saleDate = ssdRepository.dataDeVenda();
        List<String> result = new ArrayList<>();

        for (String dateString : saleDate) {
            LocalDate dateInicial = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dateAtual = LocalDate.now();
            long diferencaEmDias = ChronoUnit.DAYS.between(dateInicial, dateAtual);

            if (diferencaEmDias >= 90) {
                result.add(dateString);
                System.out.println(dateString + "igual ou maior que 90 dias");
                System.out.println("Quantidade de dias:" + diferencaEmDias);
            }
        }

        return result;
    }

}
