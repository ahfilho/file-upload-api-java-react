package br.com.api.service;

import br.com.api.FilePath;
import br.com.api.controller.ProductController;
import br.com.api.entity.Cpu;
import br.com.api.entity.ImgCpu;
import br.com.api.entity.ProductCategoryCpu;
import br.com.api.entity.ProductCategorySsd;
import br.com.api.enume.CategoryEnum;
import br.com.api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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

@Service
@Transactional
public class CpuService extends ProductController<Cpu> {

    private final Path rootCpu = Paths.get("uploads/cpu");
    private final CpuRepository cpuRepository;
    private final CpuFileRepository cpuFileRepository;

    private final ProductCategoryRepositoryCpu productCategoryCpuRepository;
    private final FileRepository fileRepository;

    public CpuService(CpuRepository cpuRepository, CpuFileRepository cpuFileRepository, ProductCategoryRepositorySsd productCategoryRepositorySsd1, ProductCategoryRepositoryCpu productCategoryCpuRepository,
                      FileRepository fileRepository) {
        this.cpuRepository = cpuRepository;
        this.cpuFileRepository = cpuFileRepository;
        this.productCategoryCpuRepository = productCategoryCpuRepository;
        this.fileRepository = fileRepository;
    }

    public void init() {
        try {
            Files.createDirectory(rootCpu);
        } catch (IOException e) {
            throw new RuntimeException("ERRO AO INICIALIZAR O DIRETÓRIO");
        }
    }

    public void save(Cpu cpu, MultipartFile file, ProductCategoryCpu productCategoryCpu) throws IOException {

        Files.copy(file.getInputStream(), this.rootCpu.resolve(file.getOriginalFilename()));
        ImgCpu imgCpu = new ImgCpu();
        Date dateNow = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        productCategoryCpu.setProductCategory(String.valueOf(CategoryEnum.CPU));

        imgCpu.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imgCpu.setContentType(file.getContentType());
        imgCpu.setData(file.getBytes());
        imgCpu.setFileSize(file.getSize());

        cpu.setImgCpu(imgCpu);

        this.cpuRepository.save(cpu);
        productCategoryCpuRepository.save(productCategoryCpu);


    }

    public List<Cpu> listAll() throws Exception {
        return cpuRepository.findAll();
    }

    public Cpu update(MultipartFile file, ProductCategorySsd productCategorySsd, Cpu cpu) throws Exception {
        Optional<Cpu> fileCpu = this.cpuRepository.findById(cpu.getId());
        Files.copy(file.getInputStream(), this.rootCpu.resolve(file.getOriginalFilename()));

        if (fileCpu.isPresent()) {
            Cpu target = fileCpu.get();
            copyCpuProperties(cpu, target);

            ImgCpu oldFileCpu = target.getImgCpu();

            ImgCpu newFileCpu = new ImgCpu();
            initalizeFileCpu(file, newFileCpu);
            if (oldFileCpu != null) {
                newFileCpu.setId(oldFileCpu.getId());
            }
            target.setImgCpu(newFileCpu);
            Cpu updateCpu = this.cpuRepository.save(target);

            deleteFilesNotInDataBase();
            return updateCpu;
        } else {
            throw new Exception("ERRO AO ATUALIZAR" + cpu.getId());
        }

    }

    public void delete(Long id) throws Exception {
        Optional<Cpu> cpuOptional = cpuRepository.findById(id);
        if (cpuOptional.isPresent()) {
            Cpu cpu = cpuOptional.get();
            if (cpu.getImgCpu().getFileName() != null) {
                String fileNameCpu = cpu.getImgCpu().getFileName();
                System.out.println(cpu.getImgCpu().getFileName());
                deleteFile(fileNameCpu);
                cpuRepository.delete(cpuOptional.get());
                cpuFileRepository.delete(cpu.getImgCpu());
            } else {
                throw new Exception(("ERRO AO DELETAR CPU" + cpu));
            }
        }
    }

    private void deleteFile(String fileName) {
        List<ImgCpu> fileList = cpuFileRepository.deleteByName(fileName);

        for (ImgCpu imgCpu : fileList) {
            cpuFileRepository.delete(imgCpu);
            String imgFileNameCpu = imgCpu.getFileName();
            Path physicalFilePath = rootCpu.resolve(imgFileNameCpu);
            File physicalFile = physicalFilePath.toFile();
            File[] files = new File(String.valueOf(FilePath.rootCpu)).listFiles();
            System.out.println(Arrays.toString(files));
            for (File fileCpu : files) {
                System.out.println(fileCpu.getName());
            }
            if (physicalFile.exists()) {
                File fileCpu = new File("uploads/cpu");
                physicalFile.delete();
            }
        }

    }

    private void deleteFilesNotInDataBase() {
        List<String> databaseFileNameCpu = cpuFileRepository.allFiles();
        File[] filesInDirectory = new File("uploads/cpu").listFiles();

        if (filesInDirectory != null) {
            for (File file : filesInDirectory) {
                String fileNameDirectory = file.getName();
                if (!databaseFileNameCpu.contains(fileNameDirectory)) {
                    file.delete();
                    System.out.println("File deleted:" + fileNameDirectory);
                }
            }
        }

    }

    private void copyCpuProperties(Cpu source, Cpu target) {
        target.setBrand(source.getBrand());
        target.setModel(source.getModel());
        target.setPurchaseDate(source.getPurchaseDate());
        target.setPurchasePrice(source.getPurchasePrice());
        target.setSaleValue(source.getSaleValue());
        target.setSerialNumber(source.getSerialNumber());
        target.setThreadCount(source.getThreadCount());
        target.setArrivalDate(source.getArrivalDate());
        target.setClockCount(source.getClockCount());
        target.setCoreCount(source.getCoreCount());
    }

    public void initalizeFileCpu(MultipartFile file, ImgCpu imgCpu) throws Exception {
        imgCpu.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imgCpu.setContentType(file.getContentType());
        imgCpu.setData(file.getBytes());
        imgCpu.setFileSize(file.getSize());
    }

    public Cpu searchId(Long id) throws Exception {
        Optional<Cpu> resultSearchCpuId = cpuRepository.findById(id);
        if (resultSearchCpuId.isPresent()) {
            return resultSearchCpuId.get();

        } else {
            throw new Exception("Id inexistente ou erro na busca.");
        }
    }

}