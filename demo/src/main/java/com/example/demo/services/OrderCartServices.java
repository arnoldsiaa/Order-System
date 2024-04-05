package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Orders;
import com.example.demo.models.Product;
import com.example.demo.models.ShoppingCart;
import com.example.demo.repositories.CartRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderCartServices {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private HttpSession httpSession;

	public Orders getDetailOrder(int orderId) {
		Optional<Orders> orders = orderRepo.findById(orderId);
		if (orders.isPresent()) {
			return orders.get();
		} else {
			return null;
		}
	}

	public void addProductToCart(List<ShoppingCart> shoppingCarts, Orders orders) {

		for (ShoppingCart cart : shoppingCarts) {
			int productId = cart.getProductId();
			Optional<Product> productOpt = productRepo.findById(productId);
			if (productOpt.isPresent()) {
				Product product = productOpt.get();
				if (product.getQuantity() > cart.getQuantity() && cart.getQuantity() != 0) {
					cart.setProductName(product.getName());
					cart.setTypeProduct(product.getType());
					cart.setPrice(product.getPrice());
					cart.setQuantity(cart.getQuantity());
					cart.setTotal(cart.getPrice() * cart.getQuantity());
					cart.setOrders(orders);

					 // Simpan ke dalam session
                    List<ShoppingCart> sessionCarts = getSessionCarts();
                    sessionCarts.add(cart);
                    httpSession.setAttribute("shoppingCarts", sessionCarts);
                    
					
					cartRepo.save(cart);
					
				} else {
					throw new RuntimeException();
				}

			}
		}
//		httpSession.setAttribute("shoppingCarts", sessionCarts);
//		cartRepo.saveAll(shoppingCarts);
	}

//	public void saveShoppingCartToDb(List<ShoppingCart> cartItems) {
//		List<ShoppingCart> sessionCarts = (List<ShoppingCart>) httpSession.getAttribute("shoppingCarts");
//		if (sessionCarts != null && !sessionCarts.isEmpty()) {
//			cartRepo.saveAll(sessionCarts);
//			httpSession.removeAttribute("shoppingCarts");
//		}
//	}
	
	 private List<ShoppingCart> getSessionCarts() {
	        List<ShoppingCart> sessionCarts = (List<ShoppingCart>) httpSession.getAttribute("shoppingCarts");
	        if (sessionCarts == null) {
	            sessionCarts = new ArrayList<>();
	        }
	        return sessionCarts;
	    }

	public Orders saveOrders(Orders orders) {
		return orderRepo.save(orders);

	}

}
