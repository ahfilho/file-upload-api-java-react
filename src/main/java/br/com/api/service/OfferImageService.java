package br.com.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.entity.CategoryProduct;
import br.com.api.entity.ImgProdutoModel;
import br.com.api.entity.Product;
import br.com.api.repository.OfferImageRepository;

@Transactional
@Service
public class OfferImageService {

	private final Path root = Paths.get("uploads");

	@Autowired
	private OfferImageRepository ofertasRepository;

	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("erro ao inicializar o diretório");
		}
	}

	public void saveFile(MultipartFile file, Product productModel, CategoryProduct categoryModel)
			throws IOException {
		Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		ImgProdutoModel img = new ImgProdutoModel();

		img.setName(StringUtils.cleanPath(file.getOriginalFilename()));
		img.setContentType(file.getContentType());
		img.setData(file.getBytes());
		img.setSize(file.getSize());

		this.ofertasRepository.save(img);
		this.ofertasRepository.save(productModel);
		this.ofertasRepository.save(categoryModel);
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
	public void saveCategoria(CategoryProduct cpm) {
	}

	public void saveProduto(Product pm) {

	}

	public Optional<ImgProdutoModel> getFile(Long id) {
		return ofertasRepository.findById(id);
	}
	// LISTA COMPLETA
	public List<ImgProdutoModel> getAllFiles() {
		return ofertasRepository.findAll();
	}

	public List<ImgProdutoModel> getSql() {
		return ofertasRepository.consulta_personalizada();
	}

	public List<ImgProdutoModel> terca() {
		return ofertasRepository.terca();
	}

	public void imgDelete(Long id) throws Exception {
		Optional<ImgProdutoModel> im = this.ofertasRepository.findById(id);
		if (im.isPresent()) {
			this.ofertasRepository.delete(im.get());
		} else {
			throw new Exception("ERRO AO DELETAR IMAGEM" + id);
		}

	}

	public void imgDeleteDiretory(Long id) {
		ImgProdutoModel img = new ImgProdutoModel();
		Optional<ImgProdutoModel> opt = this.ofertasRepository.findById(id);
		if (opt.isPresent()) {

			try {
				long l1 = img.getId();
				boolean teste = FileSystemUtils.deleteRecursively(root.resolve(Long.toString(l1)));
				System.out.println("Sucesso ao deletar imagem do path.s");
			} catch (Exception e) {
				System.out.println("Erro ao deletar arquivo de imagem do path");
				e.printStackTrace();
			}
		}
	}

	// VERIFICAR AQUI DEPOIS
	public ImgProdutoModel updateImg(MultipartFile file, Product produtoModel) throws IOException {
		Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		ImgProdutoModel ofertas = new ImgProdutoModel();

		ofertas.setName(StringUtils.cleanPath(file.getOriginalFilename()));
		ofertas.setContentType(file.getContentType());
		ofertas.setData(file.getBytes());
		ofertas.setSize(file.getSize());

		return ofertas;
	}

}
