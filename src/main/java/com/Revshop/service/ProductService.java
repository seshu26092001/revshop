package com.Revshop.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Revshop.exception.ProductNotFoundException;
import com.Revshop.models.Category;
import com.Revshop.models.Product;
import com.Revshop.repos.ProductRepository;

@Service
public class ProductService {
	
	@Autowired private CategoryService catsrv;
	@Autowired private ProductRepository productrepo;
	@Autowired ServletContext ctx;
	
	public List<Product> allProducts(){
		return productrepo.findByDeletedOrderByProdidDesc(false);
	}
	
	public List<Product> listAbouttoExpireProducts(){
		return productrepo.findByExpirydateLessThanEqualOrderByExpirydateDesc(LocalDate.now().plusMonths(1));
	}
	
	public List<Product> allCategoryProducts(int catid){
		return productrepo.findByCatidOrderByProdidDesc(catid);
	}
	
	public Product findProductById(int prodid) {
	    return productrepo.findById(prodid)
	        .orElseThrow(() -> new ProductNotFoundException(prodid));
	}

	public void deleteProduct(int prodid) {
		Optional<Product> p=productrepo.findById(prodid);
		if(p.isPresent()) {
			Product product=p.get();
			product.setDeleted(true);
			productrepo.save(product);
		}
	}
	
	public Product saveProduct(Product p,MultipartFile photo) {
		try {
			Files.copy(photo.getInputStream(), Paths.get(ctx.getRealPath("/pics/"), photo.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception ex) {
			System.err.println("Error "+ex.getMessage());
		}
		
		p.setPic("/pics/"+photo.getOriginalFilename());
		System.out.println(p);
		return productrepo.save(p);
	}
	
	public long totalProducts() {
		return productrepo.count();
	}

	
}
