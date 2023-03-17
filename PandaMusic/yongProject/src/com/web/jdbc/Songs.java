package com.web.jdbc;

public class Songs {
	
	private int song_No;
	private String song_Name;
	private String song_Singer;
	private String song_Year;
	private String song_Genre;
	private String song_Mood;
	private int song_Count;
	private String song_Url;
	private String song_Id;
	
	public Songs(int song_No, String song_Name, String song_Singer, String song_Year, String song_Genre,
			String song_Mood, int song_Count, String song_Url, String song_Id) {
		super();
		this.song_No = song_No;
		this.song_Name = song_Name;
		this.song_Singer = song_Singer;
		this.song_Year = song_Year;
		this.song_Genre = song_Genre;
		this.song_Mood = song_Mood;
		this.song_Count = song_Count;
		this.song_Url = song_Url;
		this.song_Id = song_Id;
	}
	
	public Songs(int song_No) {
		this.song_No = song_No;
		// TODO Auto-generated constructor stub
	}
	public Songs(String song_Name) {
		this.song_Name=song_Name;
		// TODO Auto-generated constructor stub
	}
	
	
	public Songs(int song_No, String song_Name, String song_Singer, String song_Year, String song_Genre,
			String song_Mood, String song_Url,String song_Id) {
		super();
		this.song_No = song_No;
		this.song_Name = song_Name;
		this.song_Singer = song_Singer;
		this.song_Year = song_Year;
		this.song_Genre = song_Genre;
		this.song_Mood = song_Mood;
		this.song_Url = song_Url;
		this.song_Id = song_Id;
	}
	
	public Songs(int song_No, String song_Name, String song_Singer, String song_Year, String song_Genre,
			String song_Mood, int song_Count, String song_Url) {
		super();
		this.song_No = song_No;
		this.song_Name = song_Name;
		this.song_Singer = song_Singer;
		this.song_Year = song_Year;
		this.song_Genre = song_Genre;
		this.song_Mood = song_Mood;
		this.song_Count = song_Count;
		this.song_Url = song_Url;
	}
	

	 public Songs(String song_Name, String song_Singer2, String song_Year2, String song_Genre2,
	         String song_Mood2, String song_Url2, String song_Id ) {
	         // TODO Auto-generated constructor stub
	      this.song_Name=song_Name;
	      this.song_Singer = song_Singer2;
	      this.song_Year = song_Year2;
	      this.song_Genre = song_Genre2;
	      this.song_Mood =song_Mood2;
	      this.song_Url = song_Url2;
	      this.song_Id = song_Id;
	   }
	 public Songs(String song_Year2, String song_Genre2, String song_Mood2) {
			this.song_Year = song_Year2;
			this.song_Genre = song_Genre2;
			this.song_Mood = song_Mood2;
		}
	 public Songs(int song_No2, String song_Name2, String song_Singer2, String song_Year2, String song_Genre2,
				String song_Mood2, String song_Url2) {
			this.song_No = song_No2;
			this.song_Name = song_Name2;
			this.song_Singer = song_Singer2;
			this.song_Year = song_Year2;
			this.song_Genre = song_Genre2;
			this.song_Mood = song_Mood2;
			this.song_Url = song_Url2;
			
			// TODO Auto-generated constructor stub
		}


	public int getSong_No() {
		return song_No;
	}
	public void setSong_No(int song_No) {
		this.song_No = song_No;
	}
	public String getSong_Name() {
		return song_Name;
	}
	public void setSong_Name(String song_Name) {
		this.song_Name = song_Name;
	}
	public String getSong_Singer() {
		return song_Singer;
	}
	public void setSong_Singer(String song_Singer) {
		this.song_Singer = song_Singer;
	}
	public String getSong_Year() {
		return song_Year;
	}
	public void setSong_Year(String song_Year) {
		this.song_Year = song_Year;
	}
	public String getSong_Genre() {
		return song_Genre;
	}
	public void setSong_Genre(String song_Genre) {
		this.song_Genre = song_Genre;
	}
	public String getSong_Mood() {
		return song_Mood;
	}
	public void setSong_Mood(String song_Mood) {
		this.song_Mood = song_Mood;
	}
	public int getSong_Count() {
		return song_Count;
	}
	public void setSong_Count(int song_Count) {
		this.song_Count = song_Count;
	}
	public String getSong_Url() {
		return song_Url;
	}
	public void setSong_Url(String song_Url) {
		this.song_Url = song_Url;
	}

	public String getSong_Id() {
		return song_Id;
	}

	public void setSong_Id(String song_Id) {
		this.song_Id = song_Id;
	}

	
	
	
	
}

