package com.code.bowwow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@Table(name="order_detail")
public class order_detail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_detail_num")
	private int order_detail_num;
	
	@Column(name="day_time")
	private Date day_time;
	
	@Column(name="order_num")
	private int order_num;
	
	@Column(name="product_count")
	private int product_count;
	
	@Column(name="product_name")
	private String product_name;
	
	@Column(name="product_price")
	private int product_price;
	
	@Column(name="order_state")
	private String order_state;
	

	public order_detail() {
		order_state="결제완료";
	}
	
	public order_detail(int order_num, int product_count, String product_name,
			int product_price, String order_state) {
		this.order_num = order_num;
		this.product_count = product_count;
		this.product_name = product_name;
		this.product_price = product_price;
		this.order_state=order_state;
	}

	
	public order_detail(int order_detail_num, Date day_time, int order_num, int product_count, String product_name,
			int product_price, String order_state) {
		super();
		this.order_detail_num = order_detail_num;
		this.day_time = day_time;
		this.order_num = order_num;
		this.product_count = product_count;
		this.product_name = product_name;
		this.product_price = product_price;
		this.order_state = order_state;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
	public Date getDay_time() {
		return day_time;
	}

	public void setDay_time(Date day_time) {
		this.day_time = day_time;
	}

	public int getOrder_detail_num() {
		return order_detail_num;
	}

	public void setOrder_detail_num(int order_detail_num) {
		this.order_detail_num = order_detail_num;
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public int getProduct_count() {
		return product_count;
	}

	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	@Override
	public String toString() {
		return "order_detail [order_detail_num=" + order_detail_num + ", day_time=" + day_time + ", order_num="
				+ order_num + ", product_count=" + product_count + ", product_name=" + product_name + ", product_price="
				+ product_price + ", order_state=" + order_state + "]";
	}
	
	
	

}
