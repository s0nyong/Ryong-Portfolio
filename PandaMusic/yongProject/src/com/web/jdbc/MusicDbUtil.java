package com.web.jdbc;

import java.net.URLDecoder;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class MusicDbUtil {
	
private DataSource dataSource;
	
public MusicDbUtil(DataSource theDataSource) {
	dataSource = theDataSource;
}
public void close(Connection conn, Statement mySt, ResultSet myRs) {
	try {
		if(myRs != null) {
			myRs.close();
			System.out.println("ResultSet closed");
		}
		if(mySt != null) {
			mySt.close();
			System.out.println("Statement closed");
		}
		if(conn != null) {
			conn.close();
			System.out.println("Connection closed");
		}
	}catch (SQLException e) {
		System.out.println("Couldn't close:" + e.getMessage());
	}
}

public List<Songs> getSongs() throws Exception {
	List<Songs> datas = new ArrayList<Songs>();
	Connection conn = null;
	Statement mySt = null;
    ResultSet myRs = null;
    
	try {
       conn = dataSource.getConnection();
       String sql = "SELECT * FROM song";
       mySt = conn.createStatement();
       myRs = mySt.executeQuery(sql);
       
       while(myRs.next()) {
    	   int id = myRs.getInt("song_No");
    	   String song_Name = myRs.getString("song_Name");
    	   String song_Singer = myRs.getString("song_Singer");
    	   String song_Year = myRs.getString("song_Year");
    	   String song_Genre = myRs.getString("song_Genre");
    	   String song_Mood = myRs.getString("song_Mood");
    	   int song_Count = myRs.getInt("song_Count");
    	   String song_Url = myRs.getString("song_Url");
    	   Songs tempSong = new Songs(id, song_Name, song_Singer, song_Year, song_Genre, song_Mood, song_Count, song_Url);
    	   datas.add(tempSong);
       	   }
       return datas;
	}
	finally {
		close(conn, mySt, myRs);
		}
	}

public void addMusic(Songs a) throws Exception{
	Connection conn = null;
	PreparedStatement mySt = null;

		try {
	       conn = dataSource.getConnection();
	       String sql = "INSERT INTO song (song_Name, song_Singer, song_Year, song_Genre, song_Mood, song_Url , song_Id) VALUES(?, ?, ?, ?, ?, ?, ?)";
	       mySt = conn.prepareStatement(sql);
	       
	       mySt.setString(1, a.getSong_Name());
	       mySt.setString(2, a.getSong_Singer());
	       mySt.setString(3, a.getSong_Year());
	       mySt.setString(4, a.getSong_Genre());
	       mySt.setString(5, a.getSong_Mood());
	       mySt.setString(6, a.getSong_Url());
	       mySt.setString(7, a.getSong_Id());
       
	       mySt.execute();

		}finally {
			close(conn, mySt, null);
		}
	
}

public List<Songs> getSelectSongs(Songs s) throws Exception {
	List<Songs> datas = new ArrayList<Songs>();
	Connection conn = null;
    ResultSet myRs = null;
    PreparedStatement mySt = null;
    
	try {
       conn = dataSource.getConnection();
       mySt = conn.prepareStatement("SELECT * from song where song_Year = ? and song_Genre= ? and song_Mood = ?");

       mySt.setString(1, s.getSong_Year());
       mySt.setString(2, s.getSong_Genre());
       mySt.setString(3, s.getSong_Mood());
       
       myRs = mySt.executeQuery();
       
       while(myRs.next()) {
    	   int id = myRs.getInt("song_No");
    	   String song_Name = myRs.getString("song_Name");
    	   String song_Singer = myRs.getString("song_Singer");
    	   String song_Year = myRs.getString("song_Year");
    	   String song_Genre = myRs.getString("song_Genre");
    	   String song_Mood = myRs.getString("song_Mood");
    	   int song_Count = myRs.getInt("song_Count");
    	   String song_Url = myRs.getString("song_Url");
    	   Songs tempSong = new Songs(id, song_Name, song_Singer, song_Year, song_Genre, song_Mood, song_Count, song_Url);
    	   datas.add(tempSong);
       	   }
       return datas;
	}
	finally {
		close(conn, mySt, myRs);
		}
}

public List<Songs> getDetailSongs(int no) throws SQLException {
	List<Songs> datas = new ArrayList<Songs>();
	Connection conn = null;
	Statement mySt = null;
	ResultSet myRs = null;
	try {
		conn = dataSource.getConnection();
		String sql = "SELECT * FROM song WHERE song_No = "+no;
		mySt = conn.createStatement();
		myRs = mySt.executeQuery(sql);
		
		
		while(myRs.next()) {
			int id = myRs.getInt("song_No");
	    	   String song_Name = myRs.getString("song_Name");
	    	   String song_singer = myRs.getString("song_Singer");
	    	   String song_Year = myRs.getString("song_Year");
	    	   String song_Genre = myRs.getString("song_Genre");
	    	   String song_Mood = myRs.getString("song_Mood");
	    	   String song_Url =myRs.getString("song_Url");
	    	   String song_Id = myRs.getString("song_Id");
	    	   Songs tempSong = new Songs(id,song_Name,song_singer,song_Year,song_Genre,song_Mood,song_Url,song_Id);
	    	   datas.add(tempSong);
	       	   }
		return datas;
	}catch(Exception e) {
		e.printStackTrace();
		return null;
	}
	finally {
		close(conn,mySt,myRs);
	}
	// TODO Auto-generated method stub
}

public List<Review> getReview(int no) throws SQLException {
	List<Review> datas = new ArrayList<Review>();
	Connection conn = null;
	Statement mySt = null;
	ResultSet myRs = null;
	try {
		conn = dataSource.getConnection();
		String sql = "SELECT * FROM review WHERE review_No ="+no;
		mySt = conn.createStatement();
		myRs = mySt.executeQuery(sql);
	    
		
		while(myRs.next()) {
			int review_No = myRs.getInt("review_No");
	    	   String review_Id = myRs.getString("review_Id");
	    	   String review_Text = myRs.getString("review_Text");
	    	   String review_Time = myRs.getString("review_Time");
	    	   Review tempReview = new Review(review_No,review_Id,review_Text,review_Time);
	    	   datas.add(tempReview);
	       	   }
		return datas;
	}finally {
		close(conn, mySt, myRs);
	}
	// TODO Auto-generated method stub
	

	}
public void addUser(User theUser) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	String sql ="insert into user(user_Id, user_Pw, user_Name, user_Pn) values(?,?,?,?)";
	
	try {
		conn = dataSource.getConnection();
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, theUser.getUser_Id());
		ps.setString(2, theUser.getUser_Pw());
		ps.setString(3, theUser.getUser_Name());
		ps.setString(4, theUser.getUser_Pn());
		ps.execute();
		
		
	}finally {
		close(conn, ps, null);
	}
	
}

public User signIn(String userId, String userPw) throws SQLException {
	
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		User theUser = new User(userId, userPw);	
		conn = dataSource.getConnection();
		String sql = "select * from user where user_Id = ? and user_Pw = ?";
		ps = conn.prepareStatement(sql);
		
		ps.setString(1,userId);
		ps.setString(2,userPw);
		
		rs = ps.executeQuery();
		
		if(rs.next()) {
			
			theUser.setUser_No(rs.getInt("user_No"));
			theUser.setUser_Id(rs.getString("user_Id"));
			theUser.setUser_Pw(rs.getString("user_Pw"));
			theUser.setUser_Name(rs.getString("user_Name"));
			theUser.setUser_Pn(rs.getString("user_Pn"));
			
		
		}
		return theUser;
		
	}	
	finally {
		close(conn,ps,rs);
	}

}

public void updateUser(User user) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;

	try {
		conn = dataSource.getConnection();
		String sql = "update user set  user_Pw = ?, user_Name = ? , user_Pn = ? where user_No = ? ";

		
		ps = conn.prepareStatement(sql);
		
		
		ps.setString(1, user.getUser_Pw());
		ps.setString(2, user.getUser_Name());
		ps.setString(3, user.getUser_Pn());
		ps.setInt(4, user.getUser_No());
		
		ps.execute();
		
	}finally {
		close(conn, ps, null);
		}
	
}


public void delUser(User user) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	try {
		conn = dataSource.getConnection();
		String sql = "delete from user where user_No = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, user.getUser_No());
		ps.execute();
		
	}finally {
		close(conn,ps,null);
	}
	
}

public String URLDecode(String enUrl) {
    String deUrl = "";
    try {
        deUrl = URLDecoder.decode(enUrl, "UTF-8");
    
    }
    catch (Exception e) {
        e.printStackTrace();
    }
//    System.out.println("url 디코딩 결과 : " + deUrl);
    return deUrl;
}
public User getUserInfo1(String uname, String upn) throws SQLException {
	User user = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		conn = dataSource.getConnection();
		String sql = "select * from user where user_Name = ? and user_Pn = ?";
		ps = conn.prepareStatement(sql);
		
		ps.setString(1,uname);
		ps.setString(2,upn);
		rs = ps.executeQuery();

		
		if(rs.next()) {
			String id = rs.getString("user_Id");
			String name = rs.getString("user_name");
			String pn = rs.getString("user_pn");
			user = new User(id, name, pn);
			
		}

		return user;
	}	
	finally {
		close(conn,ps,rs);
		
	}
}
public User getUserInfo2(String uname, String upn, String uid) throws SQLException {
	User user = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try {
		conn = dataSource.getConnection();
		String sql = "select * from user where user_Name = ? and user_Pn = ? and user_Id = ?";
		ps = conn.prepareStatement(sql);
		
		ps.setString(1,uname);
		ps.setString(2,upn);
		ps.setString(3,uid);
		rs = ps.executeQuery();

		if(rs.next()) {
			String id = rs.getString("user_Id");
			String name = rs.getString("user_name");
			String pn = rs.getString("user_pn");
			String pw = rs.getString("user_pw");
			
			user = new User(id, pw, name, pn);
		}
		return user;
	}	
	finally {
		close(conn,ps,rs);
		
	}
}
public boolean checkId(String id) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	boolean result;
	
	try {
		conn = dataSource.getConnection();
		String sql = "select user_Id from user where user_Id = ?";
		ps = conn.prepareStatement(sql);	
		ps.setString(1,id);
		rs = ps.executeQuery();
		if(rs.next()) {
			result = true;
		}else {
			result = false;
		}
	}	
	finally {
		close(conn,ps,rs);
		
	}
	return result;
}
public void addReview(Review b) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	String sql ="INSERT INTO review(review_No,review_Id,review_Text) values(?,?,?)";
	
		try {
		conn = dataSource.getConnection();
		ps = conn.prepareStatement(sql);
		
		ps.setInt(1, b.getReview_No());
		ps.setString(2, b.getReview_Id());
		ps.setString(3, b.getReview_Text());
		ps.execute();
		
		
		}finally {
		close(conn, ps, null);
		}
	
	// TODO Auto-generated method stub
	
	}
public void updateReview(Review b) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	String sql ="UPDATE review SET review_Text = ? WHERE review_No = ? AND review_Id = ?";
	
		try {
		conn = dataSource.getConnection();
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, b.getReview_Text());
		ps.setInt(2, b.getReview_No());
		ps.setString(3, b.getReview_Id());
		ps.execute();
		
		
		}finally {
		close(conn, ps, null);
		}
	
	// TODO Auto-generated method stub
	
	}

public void updateMusic(Songs a) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	try {
		conn = dataSource.getConnection();
		String sql = "UPDATE SONG SET song_Name = ?, song_Singer = ? , song_Year = ? , song_Genre = ? , song_Mood = ? , song_Url = ? WHERE song_No = ? ";
		ps = conn.prepareStatement(sql);

		ps.setString(1, a.getSong_Name());
		ps.setString(2, a.getSong_Singer());
		ps.setString(3, a.getSong_Year());
		ps.setString(4, a.getSong_Genre());
		ps.setString(5, a.getSong_Mood());
		ps.setString(6, a.getSong_Url());
		ps.setInt(7, a.getSong_No());
		
		ps.execute();
		}finally {
		close(conn, ps, null);
		}
	
	// TODO Auto-generated method stub
	
}
public void deleteMusic(Songs c) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	try {
		conn = dataSource.getConnection();
		String sql = "DELETE FROM song WHERE song_No = ?";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, c.getSong_No());
		ps.execute();
	}finally {
		close(conn,ps,null);
	}
	// TODO Auto-generated method stub
	
}
public void deleteReview(Review f) throws SQLException {
	Connection conn = null;
	PreparedStatement ps = null;
	try {
		System.out.print("h1");
		conn = dataSource.getConnection();
		System.out.print("h2");
		String sql = "DELETE FROM review WHERE review_No = ? AND review_Text = ? ";
		System.out.print("h3");
		ps = conn.prepareStatement(sql);
		System.out.print("h4");
		ps.setInt(1, f.getReview_No());
		System.out.print("h5");
		ps.setString(2, f.getReview_Text());
		System.out.print("h6");
		ps.execute();
	}finally {
		close(conn,ps,null);
	}
	// TODO Auto-generated method stub
	
}

public List<Songs> getMySongs(String id1) throws Exception {
	List<Songs> datas = new ArrayList<Songs>();
	Connection conn = null;
    ResultSet myRs = null;
    PreparedStatement mySt = null;
    
	try {
       conn = dataSource.getConnection();
       mySt = conn.prepareStatement("SELECT * from song where song_Id ='"+id1+"'");
       
       myRs = mySt.executeQuery();
       
       while(myRs.next()) {
    	   int id = myRs.getInt("song_No");
    	   String song_Name = myRs.getString("song_Name");
    	   String song_Singer = myRs.getString("song_Singer");
    	   String song_Year = myRs.getString("song_Year");
    	   String song_Genre = myRs.getString("song_Genre");
    	   String song_Mood = myRs.getString("song_Mood");
    	   int song_Count = myRs.getInt("song_Count");
    	   String song_Url = myRs.getString("song_Url");
    	   String song_Id = myRs.getString("song_Id");
    	   Songs tempSong = new Songs(id, song_Name, song_Singer, song_Year, song_Genre, song_Mood, song_Count, song_Url, song_Id);
    	   datas.add(tempSong);
       	   }
       return datas;
	}catch(Exception e) {
		e.printStackTrace();
		return null;
	}
	finally {
		close(conn, mySt, myRs);
		}
}
public List<Songs> getMyHeart(String id1) throws Exception {
	List<Songs> datas1 = new ArrayList<Songs>();
	Connection conn = null;
    ResultSet myRs = null;
    PreparedStatement mySt = null;
    
	try {
       conn = dataSource.getConnection();
       mySt = conn.prepareStatement("SELECT * FROM song LEFT JOIN count ON count.song_No=song.song_No WHERE count.user_Id ='"+id1+"'");

       myRs = mySt.executeQuery();
       
       while(myRs.next()) {
    	   int id = myRs.getInt("song_No");
    	   String song_Name = myRs.getString("song_Name");
    	   String song_Singer = myRs.getString("song_Singer");
    	   String song_Year = myRs.getString("song_Year");
    	   String song_Genre = myRs.getString("song_Genre");
    	   String song_Mood = myRs.getString("song_Mood");
    	   int song_Count = myRs.getInt("song_Count");
    	   String song_Url = myRs.getString("song_Url");
    	   String song_Id = myRs.getString("song_Id");
    	   Songs tempSong = new Songs(id, song_Name, song_Singer, song_Year, song_Genre, song_Mood, song_Count, song_Url, song_Id);
    	   datas1.add(tempSong);
       	   }
       return datas1;
	}catch(Exception e) {
		e.printStackTrace();
		return null;
	}
	finally {
		close(conn, mySt, myRs);
		}
}

public void count(int song_No) {
    Connection conn = null;
    PreparedStatement ps = null;

    try {
    	conn = dataSource.getConnection();
    	String sql = "update song set song_Count = song_Count + 1 where song_No = ?";

        ps = conn.prepareStatement(sql);

        ps.setInt(1, song_No);
        
        ps.execute();

    } catch (SQLException e) {
        System.out.println("count fail");

    } finally {
    	close(conn,ps,null);
    }
}

public void discount(int song_No) {
    Connection conn = null;
    PreparedStatement ps = null;

    try {
    	conn = dataSource.getConnection();
    	String sql = "update song set song_Count = song_Count - 1 where song_No = ?";

        ps = conn.prepareStatement(sql);

        ps.setInt(1, song_No);
        
        ps.execute();

    } catch (SQLException e) {
        System.out.println("count fail");

    } finally {
    	close(conn,ps,null);
    }
}

public void like(int song_No, String user_Id) {
    Connection conn = null;
    PreparedStatement ps = null;

    try {
    	conn = dataSource.getConnection();
    	String sql = "INSERT INTO count (song_No, user_Id) VALUES(?, ?)";

        ps = conn.prepareStatement(sql);

        ps.setInt(1, song_No);
        ps.setString(2, user_Id);
        ps.execute();

    } catch (SQLException e) {
        System.out.println("insert fail");

    } finally {
    	close(conn,ps,null);
    }
}
public void dislike(int song_No, String user_Id) {
    Connection conn = null;
    PreparedStatement ps = null;

    try {
    	conn = dataSource.getConnection();
    	String sql = "delete from count where song_No = ? and user_Id= ?";
        ps = conn.prepareStatement(sql);
        
        ps.setInt(1, song_No);
        ps.setString(2, user_Id);

        ps.executeUpdate();


    } catch (SQLException e) {
        System.out.println("delete fail");


    } finally {
    	close(conn,ps,null);
    }
    }

public int count_Check(int song_No, String user_Id) {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
    	conn = dataSource.getConnection();
    	String sql = "select * from count where song_No = ? and user_Id= ?";
        ps = conn.prepareStatement(sql);

        ps.setInt(1, song_No);
        ps.setString(2, user_Id);
        rs = ps.executeQuery();
        if(rs.next()) {
        	return 1;
        }
		return 0;
		
    } catch (SQLException e) {
        e.printStackTrace();
        return 0;
    } finally {
    	close(conn,ps,rs);
    }
	}
public List<Songs> getSearchSongs(Songs d) throws Exception{
	   List<Songs> datas = new ArrayList<Songs>();
	   Connection conn = null;
	    ResultSet myRs = null;
	    PreparedStatement mySt = null;
	    
	   try {
	       conn = dataSource.getConnection();
	       mySt = conn.prepareStatement("select * from song where song_Name like ?;");
	       mySt.setString(1, "%"+d.getSong_Name()+"%");
	       myRs = mySt.executeQuery();
	       
	       while(myRs.next()) {
	          int id = myRs.getInt("song_No");
	          String song_Name = myRs.getString("song_Name");
	          String song_Singer = myRs.getString("song_Singer");
	          String song_Year = myRs.getString("song_Year");
	          String song_Genre = myRs.getString("song_Genre");
	          String song_Mood = myRs.getString("song_Mood");
	          int song_Count = myRs.getInt("song_Count");
	          String song_Url = myRs.getString("song_Url");
	          Songs tempSong = new Songs(id, song_Name, song_Singer, song_Year, song_Genre, song_Mood,song_Count,song_Url);
	          datas.add(tempSong);
	             }
	       return datas;
	   }
	   finally {
	      close(conn, mySt, myRs);
	      }
	}
}