package br.com.api.service;

import br.com.api.entity.Category;
import br.com.api.entity.Cpu;
import br.com.api.entity.File;
import br.com.api.repository.CategoryRepository;
import br.com.api.repository.CpuRepository;
import br.com.api.repository.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CpuService {

    private final Path root = Paths.get("uploads");
    private final CpuRepository cpuRepository;
    private final CategoryRepository categoryRepository;
    private final FileRepository offerImageRepository;


    public CpuService(CpuRepository cpuRepository, CategoryRepository categoryRepository, FileRepository offerImageRepository) {
        this.cpuRepository = cpuRepository;
        this.categoryRepository = categoryRepository;
        this.offerImageRepository = offerImageRepository;

    }

    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("ERRO AO INICIALIZAR O DIRETÓRIO");
        }
    }

    public void save(Cpu cpu, MultipartFile file, Category category) throws IOException {

        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        File img = new File();
        Date dateNow = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        img.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        img.setContentType(file.getContentType());
        img.setData(file.getBytes());
        img.setFileSize(file.getSize());

        this.cpuRepository.save(cpu);
        this.categoryRepository.save(category);
        this.offerImageRepository.save(img);

    }

    public List<Cpu> listAll() {
        return this.cpuRepository.findAll();
    }

    public void delete(Long cpu) throws Exception {
        Optional<Cpu> c = this.cpuRepository.findById(cpu);
        if (c.isPresent()) {
            this.cpuRepository.delete(c.get());
        } else {
            throw new Exception(("ERRO AO DELETAR CPU" + cpu));
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


}