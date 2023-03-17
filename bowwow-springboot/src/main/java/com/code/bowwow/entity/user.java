package com.code.bowwow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class user {
	
	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	@Column(name="username")
	private String userName;
	@Column(name="phone")
	private int phone;
	@Column(name="address")
	private String address;
	@Column(name="grade")
	private String grade;
	
	public user() {
		grade="ROLE_일반";
	}
	
	@Override
	public String toString() {
		return "user [email=" + email + ", password=" + password + ", userName=" + userName + ", phone=" + phone
				+ ", address=" + address + ", grade=" + grade + "]";
	}
	public user(String email, String password, String userName, int phone, String address, String grade) {
		super();
		this.email = email;
		this.password = password;
		this.userName = userName;
		this.phone = phone;
		this.address = address;
		this.grade = grade;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = "{noop}"+password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
