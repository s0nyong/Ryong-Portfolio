package com.code.bowwow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product_category")
public class product_category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column(name="category_name")
	private String category_name;
	
	@Column(name="category_num")
	private int category_num;

	public product_category(String category_name, int category_num) {
		super();
		this.category_name = category_name;
		this.category_num = category_num;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public int getCategory_num() {
		return category_num;
	}

	public void setCategory_num(int category_num) {
		this.category_num = category_num;
	}

	@Override
	public String toString() {
		return "product_category [category_name=" + category_name + ", category_num=" + category_num + "]";
	}
	
	
}
