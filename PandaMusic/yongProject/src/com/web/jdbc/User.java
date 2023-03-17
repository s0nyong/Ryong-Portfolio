package com.web.jdbc;

public class User {
	private int user_No;
	private String user_Id;
	private String user_Pw;
	private String user_Name;
	private String user_Pn;
	
	
	

	



	@Override
	public String toString() {
		return "User [user_No=" + user_No + ", user_Id=" + user_Id + ", user_Pw=" + user_Pw + ", user_Name=" + user_Name
				+ ", user_Pn=" + user_Pn + "]";
	}





	public User(String user_Id, String user_Pw) {
		super();
		this.user_Id = user_Id;
		this.user_Pw = user_Pw;
	}




	public User(int user_No) {
		super();
		this.user_No = user_No;
	}





	public User(int user_No, String user_Id, String user_Pw, String user_Name, String user_Pn) {
		super();
		this.user_No = user_No;
		this.user_Id = user_Id;
		this.user_Pw = user_Pw;
		this.user_Name = user_Name;
		this.user_Pn = user_Pn;
	}
	
	
	
	
	public User(String user_Id, String user_Pw, String user_Name, String user_Pn) {
		super();
		this.user_Id = user_Id;
		this.user_Pw = user_Pw;
		this.user_Name = user_Name;
		this.user_Pn = user_Pn;
	}

	public User(String user_Id, String user_Name, String user_Pn) {
		super();
		this.user_Id = user_Id;
		this.user_Name = user_Name;
		this.user_Pn = user_Pn;
	}


	public int getUser_No() {
		return user_No;
	}
	public void setUser_No(int user_No) {
		this.user_No = user_No;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getUser_Pw() {
		return user_Pw;
	}
	public void setUser_Pw(String user_Pw) {
		this.user_Pw = user_Pw;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getUser_Pn() {
		return user_Pn;
	}
	public void setUser_Pn(String user_Pn) {
		this.user_Pn = user_Pn;
	}
	
	

}
