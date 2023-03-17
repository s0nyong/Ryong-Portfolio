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
@Table(name="orders")
public class order {

	
	
	@Column(name="day_time")
	private Date day_time;
	
	@Column(name="email")
	private String email;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_num")
	private int order_num;

	public order() {};
	public order(Date day_time, String email, int order_num) {
		super();
		this.day_time = day_time;
		this.email = email;
		this.order_num = order_num;
	}

	public order(String email) {
		// TODO Auto-generated constructor stub
		this.email = email;
	
	}

	public Date getDay_time() {
		return day_time;
	}

	public void setDay_time(Date day_time) {
		this.day_time = day_time;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	@Override
	public String toString() {
		return "order [day_time=" + day_time + ", email=" + email + ", order_num=" + order_num + "]";
	}
	
	
}
