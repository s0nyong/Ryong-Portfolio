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
@Table(name="cart")
public class cart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cart_num")
	private int cart_num;

	@Column(name="user_email")
	private String user_mail;

	public cart() {
	}
	
	public cart(int cart_num, String user_mail) {
		super();
		this.cart_num = cart_num;
		this.user_mail = user_mail;
	}

	public int getCart_num() {
		return cart_num;
	}

	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	@Override
	public String toString() {
		return "cart [cart_num=" + cart_num + ", user_mail=" + user_mail + "]";
	}
	
	
}
