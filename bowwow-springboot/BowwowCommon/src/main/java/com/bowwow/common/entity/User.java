package com.bowwow.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="users")
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Column(length = 128, nullable = false, unique = true)
	private String email;
	
	@Column(length = 64, nullable = false)
	private String password;
	
	@Column(length = 75, nullable = false)
	private String name;
	
	@Column(name = "nick_name", length = 128, nullable = false, unique = true)
	private String nickName;
	
	@Column(length = 45, nullable = false)
	private String role;
	
	@Column(length = 64)
	private String phone;
	
	@Column(length = 512)
	private String address;
	
	
	private int point;
	
	@Column
	private boolean enabled;
	
	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}


	public User(String email, String password, String name, String nickName, String role, int point, boolean enabled) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.role = role;
		this.point = point;
		this.enabled = enabled;
	}

	public User(Integer id, String email, String password, String name, String nickName, String role, String phone,
			String address, int point, boolean enabled) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.role = role;
		this.phone = phone;
		this.address = address;
		this.point = point;
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", nickName="
				+ nickName + ", role=" + role + ", phone=" + phone + ", address=" + address + ", point=" + point
				+ ", enabled=" + enabled + "]";
	}

	
}
