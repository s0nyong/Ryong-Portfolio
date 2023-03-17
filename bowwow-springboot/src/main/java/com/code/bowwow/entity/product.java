package com.code.bowwow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="product_num")
	private int product_num;
	
	@Column(name="category")
	private String category;
	
	@Column(name="product_name")
	private String product_name;
	
	@Column(name="product_price")
	private int product_price;

	@Column(name="product_stock")
	private int product_stock;

	@Column(name="product_desc")
	private String product_desc;
	
	@Column(name="product_img_path")
	private String product_img_path;
	
	@Column(name="product_score")
	private Double product_score;
	
	@Column(name="product_date")
	private Date product_date;
	
	@Column(name="product_discount")
	private Double product_discount;

	public product() {
		
	}
	
	public product(int product_num, String category, String product_name, int product_price, int product_stock,
			String product_desc, String product_img_path, Double product_score, Date product_date) {
		super();
		this.product_num = product_num;
		this.category = category;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_stock = product_stock;
		this.product_desc = product_desc;
		this.product_img_path = product_img_path;
		this.product_score = product_score;
		this.product_date = product_date;
	}


	public product(int product_num, String category, String product_name, int product_price, int product_stock,
			String product_desc, String product_img_path, Double product_score, Date product_date,
			Double product_discount) {
		super();
		this.product_num = product_num;
		this.category = category;
		this.product_name = product_name;
		this.product_price = product_price;
		this.product_stock = product_stock;
		this.product_desc = product_desc;
		this.product_img_path = product_img_path;
		this.product_score = product_score;
		this.product_date = product_date;
		this.product_discount = product_discount;
	}

	public Double getProduct_discount() {
		return product_discount;
	}

	public void setProduct_discount(Double product_discount) {
		this.product_discount = product_discount;
	}

	public String getProduct_img_path() {
		return product_img_path;
	}

	public void setProduct_img_path(String product_img_path) {
		this.product_img_path = product_img_path;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getProduct_date() {
		return product_date;
	}

	public void setProduct_date(Date product_date) {
		this.product_date = product_date;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_num() {
		return product_num;
	}

	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	public Double getProduct_score() {
		return product_score;
	}

	public void setProduct_score(Double product_score) {
		this.product_score = product_score;
	}

	public int getProduct_stock() {
		return product_stock;
	}

	public void setProduct_stock(int product_stock) {
		this.product_stock = product_stock;
	}

	@Override
	public String toString() {
		return "product [product_num=" + product_num + ", category=" + category + ", product_name=" + product_name
				+ ", product_price=" + product_price + ", product_stock=" + product_stock + ", product_desc="
				+ product_desc + ", product_img_path=" + product_img_path + ", product_score=" + product_score
				+ ", product_date=" + product_date + ", product_discount=" + product_discount + "]";
	}

	
}
