package com.project.productservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.productservice.dto.ProductRequest;
import com.project.productservice.dto.ProductResponse;
import com.project.productservice.model.Product;
import com.project.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	@Autowired
	private final ProductRepository repository;
	
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
						.name(productRequest.getName())
						.description(productRequest.getDescription())
						.price(productRequest.getPrice())
						.build();
		repository.save(product);
		log.info("Product {} is saved", product.getId());
	}
	
	public List<ProductResponse> getAllProducts(){
		List<Product> products = repository.findAll();
		return products.stream().map(this::mapToProductResponse).toList();
	}
	
	public ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}
}
