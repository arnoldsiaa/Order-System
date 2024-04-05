package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Orders;
import com.example.demo.models.ShoppingCart;
import com.example.demo.models.dto.OrderDto;
import com.example.demo.services.OrderCartServices;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping
public class OrderCartController {

	@Autowired
	OrderCartServices orderCartServices;

	@GetMapping("/cart")
	public ResponseEntity<List<ShoppingCart>> getAllCartItems(HttpSession session) {
		List<ShoppingCart> cartItems = (List<ShoppingCart>) session.getAttribute("shoppingCarts");
		System.out.println(cartItems);
		if (cartItems == null || cartItems.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(cartItems, HttpStatus.OK);
		}
	}

	@GetMapping("/cart/{id}")
	public ResponseEntity<Orders> findById(@PathVariable("id") Integer id) {
		Orders orders = orderCartServices.getDetailOrder(id);
		return new ResponseEntity<Orders>(orders, HttpStatus.OK);
	}

//	@PostMapping("/cart")
//	public ResponseEntity<String> addToChart(@RequestBody OrderDto orderDto, HttpSession session) {
//		List<ShoppingCart> cartItems = (List<ShoppingCart>) session.getAttribute("shoppingCarts");
//		if (cartItems == null) {
//			cartItems = new ArrayList<>();
//			session.setAttribute("shoppingCarts", cartItems);
//		}
//		Orders orders = new Orders(orderDto.getCustomerName(), 
//				 orderDto.getCustomerAddress(),orderDto.getCartItems());
//		orderCartServices.saveOrders(orders);
//		orderCartServices.addProductToCart(orderDto.getCartItems(), orders);
//		
//		System.out.println(orders);
//	
//		cartItems.addAll(orderDto.getCartItems());
//		session.setAttribute("shoppingCarts", cartItems);
//
//		return new ResponseEntity<>("Berhasil menambah produk ke cart", HttpStatus.CREATED);
//	}
//
//	@PostMapping("/cart/saving")
//	public ResponseEntity<String> saveCartToDatabase(HttpSession session) {
//
//		List<ShoppingCart> cartItems = (List<ShoppingCart>) session.getAttribute("shoppingCarts");
//		if (cartItems == null || cartItems.isEmpty()) {
//			return new ResponseEntity<>("item pada shopping cart = null", HttpStatus.NO_CONTENT);
//		}
//
//		orderCartServices.saveShoppingCartToDb(cartItems);
//
//		session.removeAttribute("shoppingCarts");
//
//		return new ResponseEntity<>("item pada shopping cart berhasil dimasukkan ke db", HttpStatus.CREATED);
//	}

	@PostMapping("/cart")
	public ResponseEntity<String> buyProduct(@RequestBody OrderDto orderDto){
		Orders orders = new Orders(orderDto.getCustomerName(), 
				 orderDto.getCustomerAddress(),orderDto.getCartItems());
		orderCartServices.saveOrders(orders);
		orderCartServices.addProductToCart(orderDto.getCartItems(), orders);
		
		System.out.println(orders);
		
		return new ResponseEntity<String>("Berhasil menambah produk ke cart",
				HttpStatus.CREATED);
		
	}

}
