package br.com.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;

import br.com.api.entity.*;
import br.com.api.entity.ProductCategorySsd;
import br.com.api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
public class SsdFileService {

    private final Path rootSsd = Paths.get("uploads/ssd");
    private final ProductCategoryRepositorySsd productCategoryRepositorySsd;
    private final SsdFileRepository ssdFileRepository;
    private final SsdRepository ssdRepository;


    public SsdFileService(ProductCategoryRepositorySsd productCategoryRepositorySsd, SsdFileRepository ssdFileRepository, SsdRepository ssdRepository, SsdFileRepository ssdFileRepository1, SsdRepository ssdRepository1) {
        this.productCategoryRepositorySsd = productCategoryRepositorySsd;
        this.ssdFileRepository = ssdFileRepository1;
        this.ssdRepository = ssdRepository1;
    }

    public void init() {
        try {
            Files.createDirectory(rootSsd);
        } catch (IOException e) {
            throw new RuntimeException("erro ao inicializar o diretório");
        }
    }

    public void saveFile(MultipartFile file, Ssd ssd, ProductCategorySsd productCategorySsd)
            throws IOException {
        Files.copy(file.getInputStream(), this.rootSsd.resolve(file.getOriginalFilename()));
        ImgSsd imgSsd = new ImgSsd();

        imgSsd.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        imgSsd.setContentType(file.getContentType());
        imgSsd.setData(file.getBytes());
        imgSsd.setFileSize(file.getSize());

        this.ssdFileRepository.save(imgSsd);
        this.ssdRepository.save(ssd);
        this.productCategoryRepositorySsd.save(productCategorySsd);
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

    public Optional<ImgSsd> getFile(Long id) {
        return ssdFileRepository.findById(id);
    }

    // LISTA COMPLETA
    public List<ImgSsd> getAllFiles() {
        return ssdFileRepository.findAll();
    }

    public List<ImgSsd> getSql() {
        return ssdFileRepository.consulta_personalizada();
    }

    public List<ImgSsd> terca() {
        return ssdFileRepository.terca();
    }

    public void imgDelete(Long id) throws Exception {
        Optional<ImgSsd> im = this.ssdFileRepository.findById(id);
        if (im.isPresent()) {
            this.ssdFileRepository.delete(im.get());
        } else {
            throw new Exception("ERRO AO DELETAR IMAGEM" + id);
        }

    }

    public void imgDeleteDiretory(Long id) {
        ImgSsd imgSsd = new ImgSsd();
        Optional<ImgSsd> opt = this.ssdFileRepository.findById(id);
        if (opt.isPresent()) {

            try {
                long l1 = imgSsd.getId();
                boolean teste = FileSystemUtils.deleteRecursively(rootSsd.resolve(Long.toString(l1)));
                System.out.println("Sucesso ao deletar imagem do path.s");
            } catch (Exception e) {
                System.out.println("Erro ao deletar arquivo de imagem do path");
                e.printStackTrace();
            }
        }
    }

    // VERIFICAR AQUI DEPOIS
    public ImgSsd updateImg(MultipartFile file, Ssd ssd) throws IOException {
        Files.copy(file.getInputStream(), this.rootSsd.resolve(file.getOriginalFilename()));
        ImgSsd otherFiles = new ImgSsd();

        otherFiles.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
        otherFiles.setContentType(file.getContentType());
        otherFiles.setData(file.getBytes());
        otherFiles.setFileSize(file.getSize());

        return otherFiles;
    }

}
