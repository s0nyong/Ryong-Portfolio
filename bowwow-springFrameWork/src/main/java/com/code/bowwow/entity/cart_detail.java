package com.code.bowwow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@Table(name="cart_detail")
public class cart_detail {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_detail_num")
	private int cart_detail_num;

	@Column(name="cart_num")
	private int cart_num;
	
	@Column(name="product_name")
	private String product_name;
	
	@Column(name="product_count")
	private int product_count;
	
	@Column(name="product_price")
	private int product_price;
	
	public cart_detail(int cart_detail_num, int cart_num, String product_name, int product_count, int product_price) {
		super();
		this.cart_detail_num = cart_detail_num;
		this.cart_num = cart_num;
		this.product_name = product_name;
		this.product_count = product_count;
		this.product_price = product_price;
	}
	
	public cart_detail() {
	}

	public int getCart_detail_num() {
		return cart_detail_num;
	}

	public void setCart_detail_num(int cart_detail_num) {
		this.cart_detail_num = cart_detail_num;
	}

	public int getCart_num() {
		return cart_num;
	}

	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_count() {
		return product_count;
	}

	public void setProduct_count(int count) {
		this.product_count = count;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	
	@Override
	public String toString() {
		return "cart_detail [cart_detail_num=" + cart_detail_num + ", cart_num=" + cart_num + ", product_name="
				+ product_name + ", product_count=" + product_count + ", product_price=" + product_price + "]";
	}

}
