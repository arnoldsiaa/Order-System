package com.example.demo.models.dto;

import java.util.List;

import com.example.demo.models.ShoppingCart;

public class OrderDto {
	
	  	private String customerName;
	  	private String customerAddress;
	    private List<ShoppingCart> cartItems;
	    
		public OrderDto() {
		}
		
		public OrderDto(String customerName, String customerAddress, List<ShoppingCart> cartItems) {
			this.customerName = customerName;
			this.customerAddress = customerAddress;
			this.cartItems = cartItems;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public String getCustomerAddress() {
			return customerAddress;
		}
		public void setCustomerAddress(String customerAddress) {
			this.customerAddress = customerAddress;
		}
		public List<ShoppingCart> getCartItems() {
			return cartItems;
		}
		public void setCartItems(List<ShoppingCart> cartItems) {
			this.cartItems = cartItems;
		}
	   
	   
}
