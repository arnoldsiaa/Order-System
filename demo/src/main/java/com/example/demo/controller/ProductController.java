package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Product;
import com.example.demo.models.dto.ProductDto;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductServices;

@RestController
@RequestMapping
public class ProductController {

	@Autowired
	ProductServices productServices;

	@Autowired
	ProductRepository productRepo;

	@PostMapping("/product")
	public ResponseEntity<Product> saveProduct(@RequestBody ProductDto productDto) {
		Product newProduct = new Product();
		newProduct.setName(productDto.getName());
		newProduct.setPrice(productDto.getPrice());
		newProduct.setQuantity(productDto.getQuantity());
		newProduct.setType(productDto.getType());
		productServices.saveProduct(newProduct);

		return new ResponseEntity<Product>(newProduct, HttpStatus.CREATED);
	}

	@GetMapping("/product")
	public ResponseEntity<List<ProductDto>> getAllProduct() {
		return new ResponseEntity<List<ProductDto>>(productServices.findAll(), HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
		Optional<Product> productOpt = productServices.findById(id);
		if (productOpt.isPresent()) {
			return new ResponseEntity<Product>(productOpt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity("Produk tidak ditemukan", HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/product/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Integer id,
			@RequestBody ProductDto productDto) {
		productServices.updateProduct(id, productDto);
		return new ResponseEntity<ProductDto>(productDto, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id) {
		productServices.deleteProduct(id);
		return new ResponseEntity<Product>(HttpStatus.OK);

	}

	@GetMapping("/product/pagination")
	public ResponseEntity<List<Product>> getProductPagination(@RequestParam int page, @RequestParam int size) {
		Pageable pageable = PageRequest.of(page, size);
		List<Product> listOfProduct = productRepo.findAll(pageable).getContent();
		return new ResponseEntity<List<Product>>(listOfProduct, HttpStatus.OK);
	}

}
