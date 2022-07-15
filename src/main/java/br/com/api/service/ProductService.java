package br.com.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.List;
import javax.transaction.Transactional;

import br.com.api.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.api.entity.Category;
import br.com.api.entity.Product;
import br.com.api.repository.CategoryRepository;
import br.com.api.repository.ProductRepository;
import br.com.api.repository.OfferImageRepository;

@Transactional
@Service
public class ProductService {

	private final Path root = Paths.get("uploads");

	@Autowired
	private ProductRepository productRespository;
	@Autowired
	private CategoryRepository catRepository;
	@Autowired
	private OfferImageRepository imageRepository;

	public void init() {

		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("erro ao inicializar o diretório");
		}
	}

	public void saveProduct_file_category(Product productModel, MultipartFile file, Category catPm)
			throws IOException {
		Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		ProductImage img = new ProductImage();
		
		img.setName(StringUtils.cleanPath(file.getOriginalFilename()));
		img.setContentType(file.getContentType());
		img.setData(file.getBytes());
		img.setSize(file.getSize());

		this.productRespository.save(productModel);
		this.catRepository.save(catPm);
		this.imageRepository.save(img);

	}

	public List<Product> listProduct() {
		return this.productRespository.findAll();
	}

	public Product updateProduct(Product productModel) throws Exception {
		Optional<Product> opt = this.productRespository.findById(productModel.getId());
		if (opt.isPresent()) {
			Product pm = opt.get();
			pm.setProductName(productModel.getProductName());
			pm.setCategoryProduct(productModel.getCategoryProduct());
			pm.setImgProduct(productModel.getImgProduct());
			return pm;
		} else {
			throw new Exception("Erro ao atualizar o produto, categoria e a imagem." + productModel.getId());
		}
	}
	public void deleteProduct(Long id) throws Exception {
		Optional<Product> product = this.productRespository.findById(id);
		if(product.isPresent()) {
			this.productRespository.delete(product.get());
		} else {
			throw new Exception("Erro ao deletar");
		}
		
	}
}