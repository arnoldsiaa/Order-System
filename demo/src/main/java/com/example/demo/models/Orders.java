package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "order_tbl")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String customerName;
	private String address;

	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<ShoppingCart> cartItems;

	public Orders() {

	}
	
	public Orders(Integer id, String customerName, String address) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.address = address;
	}

	public Orders(String customerName, String address, List<ShoppingCart> cartItems) {
		this.customerName = customerName;
		this.address = address;
		this.cartItems = cartItems;
	}	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ShoppingCart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingCart> cartItems) {
        this.cartItems = cartItems;
    }

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", customerName=" + customerName + ", address=" + address + ", cartItems="
				+ cartItems + "]";
	}

}
