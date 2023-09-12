package br.com.api.service;

import br.com.api.FilePath;
import br.com.api.entity.Category;
import br.com.api.entity.Cpu;
import br.com.api.entity.Img;
import br.com.api.enume.CategoryEnum;
import br.com.api.repository.CategoryRepository;
import br.com.api.repository.CpuRepository;
import br.com.api.repository.FileRepository;
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
public class CpuService {

    private final Path rootCpu = Paths.get("uploads/cpu");
    private final CpuRepository cpuRepository;
    private final CategoryRepository categoryRepository;
    private final FileRepository fileRepository;

    public CpuService(CpuRepository cpuRepository, CategoryRepository categoryRepository, FileRepository fileRepository) {
        this.cpuRepository = cpuRepository;
        this.categoryRepository = categoryRepository;
        this.fileRepository = fileRepository;
    }

    public void init() {
        try {
            Files.createDirectory(rootCpu);
        } catch (IOException e) {
            throw new RuntimeException("ERRO AO INICIALIZAR O DIRETÓRIO");
        }
    }

    public void save(Cpu cpu, MultipartFile file, Category category) throws IOException {

        Files.copy(file.getInputStream(), this.rootCpu.resolve(file.getOriginalFilename()));
        category.setProductCategory(String.valueOf(CategoryEnum.CPU));
        Img img = new Img();
        Date dateNow = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        img.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        img.setContentType(file.getContentType());
        img.setData(file.getBytes());
        img.setFileSize(file.getSize());

        this.cpuRepository.save(cpu);
        this.categoryRepository.save(category);
        this.fileRepository.save(img);

    }

    public List<Cpu> listAll() {
        return this.cpuRepository.findAll();
    }

    public void delete(Long id) throws Exception {
        Optional<Cpu> cpuOptional = this.cpuRepository.findById(id);
        if (cpuOptional.isPresent()) {
            Cpu objetoCpu = cpuOptional.get();
            if (objetoCpu.getImg().getFileName() != null) {
                String fileName = objetoCpu.getImg().getFileName();
                deleteFile(fileName);
                this.cpuRepository.delete(cpuOptional.get());
                fileRepository.delete(objetoCpu.getImg());
            }
        } else {
            throw new Exception(("ERRO AO DELETAR CPU"));
        }
    }

    private void deleteFile(String fileName) {
        List<Img> imgList = fileRepository.findByName(fileName);

        for (Img img : imgList) {
            fileRepository.delete(img);
            String imgFileName = img.getFileName();
            Path physicalFilePath = rootCpu.resolve(imgFileName);
            File physicalFile = physicalFilePath.toFile();
            File[] files = new File(String.valueOf(FilePath.rootCpu)).listFiles();
            System.out.println(Arrays.toString(files));
            System.out.println();

            for (File f : files
            ) {

                System.out.println(f.getName());

            }
            if (physicalFile.exists()) {
                File fil = new File("uploads/cpu");
                physicalFile.delete();
            }

        }
    }

    public Cpu update(MultipartFile file, Category category, Cpu cpu) throws Exception {
        Optional<Cpu> editCpu = this.cpuRepository.findById(cpu.getId());
        if (editCpu.isPresent()) {
            Cpu c = editCpu.get();
            c.setBrand(cpu.getBrand());
            c.setArrivalDate(cpu.getArrivalDate());
            c.setClockCount(cpu.getClockCount());
            c.setCoreCount(cpu.getCoreCount());
            c.setModel(cpu.getModel());
            c.setPurchaseDate(cpu.getPurchaseDate());
            c.setPurchasePrice(cpu.getPurchasePrice());
            c.setSaleValue(cpu.getSaleValue());
            c.setThreadCount(cpu.getThreadCount());
            return c;
        } else {
            throw new Exception("ERRO AO ATUALIZAR" + cpu.getId());
        }

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