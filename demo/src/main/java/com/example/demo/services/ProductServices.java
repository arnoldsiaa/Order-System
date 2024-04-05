package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.models.dto.ProductDto;
import com.example.demo.repositories.ProductRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServices {

	@Autowired
	private ProductRepository productRepository;
	
	public List<ProductDto> findAll(){
		List<Product> product = productRepository.findAll();
		return mapProductToProductDtoList(product);
	}
	
	private List<ProductDto> mapProductToProductDtoList(List<Product> product) {
		List<ProductDto> productDtos = new ArrayList<ProductDto>();
		for (Product existingProduct : product) {
			productDtos.add(new ProductDto(existingProduct.getName(), 
					existingProduct.getType(), 
					existingProduct.getPrice(), 
					existingProduct.getQuantity()));
		}
		return productDtos;
	}

	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	public void saveProduct(Product product) {
		productRepository.save(product);
	}
	
	public void updateProduct(Integer id, ProductDto productDto) {
		Optional<Product> productOpt = productRepository.findById(id);
		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			product.setName(productDto.getName());
			product.setPrice(productDto.getPrice());
			product.setQuantity(productDto.getQuantity());
			product.setType(productDto.getType());
			productRepository.save(product);
			
		} else {
			throw new RuntimeException("Gagal Update Produk dengan id =" + id);
		}
	}
	
	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
	
}
