package br.com.api.service;

import br.com.api.entity.*;
import br.com.api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RamFileService {

    private final Path rootRam = Paths.get("uploads/ram");
    private final RamFileRepository ramFileRepository;
    private final RamRepository ramRepository;
    private final ProductCategoryRepositoryRam productCategoryRepositoryRam;

    public RamFileService(ProductCategoryRepositoryRam productCategoryRepositoryRam, ProductCategoryRepositorySsd productCategoryRepositorySsd, RamFileRepository ramFileRepository, RamRepository ramRepository, ProductCategoryRepositoryRam productCategoryRepositoryRam1) {
        this.ramFileRepository = ramFileRepository;
        this.ramRepository = ramRepository;
        this.productCategoryRepositoryRam = productCategoryRepositoryRam1;
    }

    public void init() {
        try {
            Files.createDirectory(rootRam);
        } catch (IOException e) {
            throw new RuntimeException("erro ao inicializar o diretório");
        }
    }

    public void saveFile(MultipartFile file, Ram ram, ProductCategory productCategory)
            throws IOException {
        Files.copy(file.getInputStream(), this.rootRam.resolve(file.getOriginalFilename()));
        ImgRam imgRam = new ImgRam();

        imgRam.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imgRam.setContentType(file.getContentType());
        imgRam.setData(file.getBytes());
        imgRam.setFileSize(file.getSize());

        this.ramFileRepository.save(imgRam);
        this.ramRepository.save(ram);
        this.productCategoryRepositoryRam.save(productCategory);

    }

    /*
     * Envia mais de um arquivo por requisição; Vai ficar aqui para caso precise
     * modificar algo.
     *
     * public void saveFile(MultipartFile[] file, ProdutoModel product_name,
     * CategoriaProdutoModel product_category) throws IOException { for
     * (MultipartFile multipartFile : file) {
     * Files.copy(multipartFile.getInputStream(),
     * this.root.resolve(multipartFile.getOriginalFilename()));
     *
     * ImgProdutoModel ofertas = new ImgProdutoModel();
     * ofertas.setName(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
     * ofertas.setContentType(multipartFile.getContentType());
     * ofertas.setData(multipartFile.getBytes());
     * ofertas.setSize(multipartFile.getSize());
     *
     * this.ofertasRepository.save(ofertas);
     * this.ofertasRepository.save(product_name);
     * this.ofertasRepository.save(product_category); } }
     */
    public void saveCategoria(ProductCategorySsd productCategorySsd) {
    }

    public Optional<ImgRam> getFile(Long id) {
        return ramFileRepository.findById(id);
    }

    // LISTA COMPLETA
    public List<ImgRam> getAllFiles() {
        return ramFileRepository.findAll();
    }

    public List<ImgRam> getSql() {
        return ramFileRepository.consulta_personalizada();
    }

    public List<ImgRam> terca() {
        return ramFileRepository.terca();
    }

    public void imgDelete(Long id) throws Exception {
        Optional<ImgRam> im = this.ramFileRepository.findById(id);
        if (im.isPresent()) {
            this.ramFileRepository.delete(im.get());
        } else {
            throw new Exception("ERRO AO DELETAR IMAGEM" + id);
        }

    }

    public void imgDeleteDiretory(Long id) {
        ImgRam imgRam = new ImgRam();
        Optional<ImgRam> opt = this.ramFileRepository.findById(id);
        if (opt.isPresent()) {

            try {
                long l1 = imgRam.getId();
                boolean teste = FileSystemUtils.deleteRecursively(rootRam.resolve(Long.toString(l1)));
                System.out.println("Sucesso ao deletar imagem do path.s");
            } catch (Exception e) {
                System.out.println("Erro ao deletar arquivo de imagem do path");
                e.printStackTrace();
            }
        }
    }

    // VERIFICAR AQUI DEPOIS
    public ImgSsd updateImg(MultipartFile file, Ssd ssd) throws IOException {
        Files.copy(file.getInputStream(), this.rootRam.resolve(file.getOriginalFilename()));
        ImgSsd otherFiles = new ImgSsd();

        otherFiles.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        otherFiles.setContentType(file.getContentType());
        otherFiles.setData(file.getBytes());
        otherFiles.setFileSize(file.getSize());

        return otherFiles;
    }

}

