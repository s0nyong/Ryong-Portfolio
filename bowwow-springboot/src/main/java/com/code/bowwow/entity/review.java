package com.code.bowwow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="review")
public class review {

	
	
	@Column(name="coment")
	private String coment;
	
	@Column(name="coment_date")
	private Date coment_date;
	
	@Column(name="product_num")
	private int product_num;
	
	@Column(name="product_score")
	private Double product_score;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_num")
	private int review_num;
	
	@Column(name="username")
	private String username;

	public review() {
		coment_date = new Date();
	}
	
	public review(String coment, Date coment_date, int product_num, Double product_score, int review_num,
			String username) {
		super();
		this.coment = coment;
		this.coment_date = coment_date;
		this.product_num = product_num;
		this.product_score = product_score;
		this.review_num = review_num;
		this.username = username;
	}

	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public Date getComent_date() {
		return coment_date;
	}

	public void setComent_date(Date coment_date) {
		this.coment_date = coment_date;
	}

	public int getProduct_num() {
		return product_num;
	}

	public void setProduct_num(int product_num) {
		this.product_num = product_num;
	}

	public Double getProduct_score() {
		return product_score;
	}

	public void setProduct_score(Double product_score) {
		this.product_score = product_score;
	}

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "review [coment=" + coment + ", coment_date=" + coment_date + ", product_num=" + product_num
				+ ", product_score=" + product_score + ", review_num=" + review_num + ", username=" + username + "]";
	}
	
	
}
