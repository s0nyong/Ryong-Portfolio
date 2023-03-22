package com.bowwow.common.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="inquiries")
public class Inquiry {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(columnDefinition="TEXT")
	private String comment;
	
	@OneToOne
	@JoinColumn(name = "parent_id")
	private Inquiry parent;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private Set<Inquiry> children = new HashSet<>();
	
	@Column(name = "reg_date", nullable = false)
	private LocalDateTime regDate;

	public Inquiry() {
	}
	
	
	@Override
	public String toString() {
		return "Inquiry [id=" + id + ", product=" + product + ", user=" + user + ", comment=" + comment + ", children="
				+ children + ", regDate=" + regDate + "]";
	}


	public Inquiry(String comment) {
		this.comment = comment;
		this.regDate = LocalDateTime.now();
	}

	public Inquiry(Integer id) {
		this.id = id;
	}
	
	public Inquiry(Product product, User user, String comment) {
		this.product = product;
		this.user = user;
		this.comment = comment;
		this.regDate = LocalDateTime.now();
	}
	
	public Inquiry(Product product, User user, String comment, Inquiry parent) {
		super();
		this.product = product;
		this.user = user;
		this.comment = comment;
		this.parent = parent;
		this.regDate = LocalDateTime.now();
	}



	public Inquiry(Integer id, Product product, User user, String comment, Inquiry parent, Set<Inquiry> children,
			LocalDateTime regDate) {
		super();
		this.id = id;
		this.product = product;
		this.user = user;
		this.comment = comment;
		this.parent = parent;
		this.children = children;
		this.regDate = regDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Inquiry getParent() {
		return parent;
	}

	public void setParent(Inquiry parent) {
		this.parent = parent;
	}

	public Set<Inquiry> getChildren() {
		return children;
	}

	public void setChildren(Set<Inquiry> children) {
		this.children = children;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}



	
	
}
