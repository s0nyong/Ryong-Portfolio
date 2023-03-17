package com.web.jdbc;

public class Review{
	
	private int review_No;
	private String review_Id;
	private String review_Text;
	private String review_Time;
	
	
	
	
	
	
	public Review(int review_No, String review_Id, String review_Text, String review_Time) {
		super();
		this.review_No = review_No;
		this.review_Id = review_Id;
		this.review_Text = review_Text;
		this.review_Time = review_Time;
	}
	
	public Review(int review_No) {
		this.review_No = review_No;
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	public Review(int review_No2, String review_Id2, String review_Text2) {
		this.review_No = review_No2;
		this.review_Id = review_Id2;
		this.review_Text = review_Text2;
		// TODO Auto-generated constructor stub
	}

	public Review(int review_No2, String review_text2) {
		this.review_No = review_No2;
		this.review_Text=review_text2;
		// TODO Auto-generated constructor stub
	}

	public int getReview_No() {
		return review_No;
	}
	public void setReview_No(int review_No) {
		this.review_No = review_No;
	}
	public String getReview_Id() {
		return review_Id;
	}
	public void setReview_Id(String review_Id) {
		this.review_Id = review_Id;
	}
	public String getReview_Text() {
		return review_Text;
	}
	public void setReview_Text(String review_Text) {
		this.review_Text = review_Text;
	}
	public String getReview_Time() {
		return review_Time;
	}
	public void setReview_Time(String review_Time) {
		this.review_Time = review_Time;
	}
	

	
	
	
}
