package com.bowwow.common.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="notice")
public class Notice {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Column(length = 64)
	private String title;
	
	@Column(columnDefinition="TEXT")
	private String comment;
	
	@Column
	private LocalDate date;
	
	
	public Notice() {
		this.date = LocalDate.now();
	}

	public Notice(String title, String comment) {
		this.title = title;
		this.comment = comment;
		this.date = LocalDate.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
}
