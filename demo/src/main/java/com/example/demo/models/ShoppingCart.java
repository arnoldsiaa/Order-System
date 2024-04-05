package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shooping_chart_tbl")
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer productId;
	private String productName;
	private double price;
	private int quantity;
	private String typeProduct;
	private double total;
	
	@ManyToOne
	@JoinColumn(name = "orders_id")
	@JsonBackReference
	private Orders orders;

	public ShoppingCart() {
	}

	public ShoppingCart(int productId, String productName, double price, int quantity, String typeProduct) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.typeProduct = typeProduct;
	}

	public ShoppingCart(int productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	

}
