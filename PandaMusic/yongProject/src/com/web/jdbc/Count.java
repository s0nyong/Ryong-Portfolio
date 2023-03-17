package com.web.jdbc;

public class Count{
	
	private int count_No;
	private int song_No;
	private String user_Id;
	
	public Count(int count_No, int song_No, String user_Id) {
		super();
		this.count_No = count_No;
		this.song_No = song_No;
		this.user_Id = user_Id;
	}
	
	public Count(int song_No, String user_Id) {
		super();
		this.song_No = song_No;
		this.user_Id = user_Id;
	}
	
	public Count(String user_Id) {
		super();
		this.user_Id = user_Id;
	}

	public int getCount_No() {
		return count_No;
	}

	public void setCount_No(int count_No) {
		this.count_No = count_No;
	}

	public int getSong_No() {
		return song_No;
	}

	public void setSong_No(int song_No) {
		this.song_No = song_No;
	}
	
	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	
	
	
}