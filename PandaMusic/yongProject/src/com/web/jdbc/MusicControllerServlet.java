package com.web.jdbc;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



@WebServlet("/MusicControllerServlet")
public class MusicControllerServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   
private MusicDbUtil musicDbUtil;

   @Resource(name = "jdbc/yong")
   private DataSource dataSource;
   
   @Override
   public void init() throws ServletException {
   	// TODO Auto-generated method stub
   	super.init();
   	musicDbUtil = new MusicDbUtil(dataSource);
  }
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   try {
			String theCommand = request.getParameter("command");
			if(theCommand == null) {
				theCommand = "LIST";
			}
			switch (theCommand) {
			case "LIST":
				//System.out.println("listMusic Start");
				listMusic(request, response);
				break;
			case "ADD":
				//System.out.println("addMusic Start");
				addMusic(request, response);
				break;
			case "MUSIC_UPDATE":
				updateMusic(request,response);
				break;
			case "MUSIC_UPDATE2":
				updateMusic2(request,response);
				break;
			case "MUSIC_DELETE":
				deleteMusic(request,response);
				break;
			case "SELECT":
				//System.out.println("selectMusic Start");
				selectMusic(request, response);
				break;
			case "SEARCH":
				//System.out.println("selectMusic Start");
				serchMusic(request, response);
				break;
			case "INQUIRY":
				inquiryMusic(request,response);
				break;
			case "LIKE":
				likeMusic(request,response);
				break;
			case "USER_LIST":	// 회원조회
				listUser(request, response);
				break;
			case "USER_ADD":		// 회원가입
				addUser(request, response);
				break;
			case "USER_LOGIN":	// 로그인
				login(request, response);
				break;
			case "USER_UPDATE":	// 회원정보 수정
				updateUser(request, response);
				break;
			case "USER_DELETE":	// 회원탈퇴
				deleteUser(request, response);
				break;
			case "USER_CHECK": // 중복확인
				idCheck(request, response);
				break;
			case "USER_FIND": // 아이디,비번 찾기
				findUser(request, response);
				break;
			case "REVIEW_DELETE":	// 리뷰삭제
				deleteReview(request, response);
				break;
			case "REVIEW_UPDATE":
				updateReview(request,response);
				break;
			case "ID_CHECK":	// 중복확인
				idCheck(request, response);
				break;
			case "REVIEW_ADD":	// 리뷰추가
				addReview(request,response);
				break;
			case "MY_MUSIC":	// 중복확인
				myMusic(request, response);
				break;
			default:
				//System.out.println("default");
				listMusic(request, response);
			}
		}catch (Exception e) {
			throw new ServletException(e);
		}
	}

   private void serchMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      String song_Name = request.getParameter("song_Name");
	      if ((song_Name != null)  && (!song_Name.trim().equals("")));
	      
	      Songs d = new Songs(song_Name);
	        
	        try {
	             List<Songs> datas = musicDbUtil.getSearchSongs(d);
	             System.out.println(datas);
	             
	             request.setAttribute("music_list", datas);
	          }catch(Exception e) {
	             e.printStackTrace();
	          }
	          RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
	          dispatcher.forward(request, response);
	     }

private void likeMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int song_No = Integer.parseInt(request.getParameter("song_No"));
	HttpSession session = request.getSession();
	String user_Id  = (String)session.getAttribute("user_Id");
	
	if(!(user_Id == null)) {
	
	try {
	int check = musicDbUtil.count_Check(song_No, user_Id);  
		if (check == 1) { 
			musicDbUtil.dislike(song_No, user_Id);
			musicDbUtil.discount(song_No);

		} else { 

			musicDbUtil.like(song_No, user_Id);
			musicDbUtil.count(song_No);
		}
		response.sendRedirect(request.getContextPath() +"/MusicControllerServlet?command=LIST");
	}catch(Exception e){
		e.printStackTrace();
	}
	}else {
		response.sendRedirect(request.getContextPath() +"/signin.jsp");
	}
	
}

private void updateReview(HttpServletRequest request, HttpServletResponse response) throws IOException {
	int review_No = Integer.parseInt(request.getParameter("song"));
    String review_Id = request.getParameter("user_Id");
	String review_Text = request.getParameter("review");
	System.out.println(review_Text);
	
	Review b = new Review(review_No,review_Id,review_Text);
	try {
	musicDbUtil.updateReview(b);
	response.sendRedirect(request.getContextPath() + "/MusicControllerServlet?command=INQUIRY&&theNo="+request.getParameter("song"));
	}catch(Exception e) {
		   e.printStackTrace();
	   }
	   
	// TODO Auto-generated method stub
	
}
	


private void deleteReview(HttpServletRequest request, HttpServletResponse response) {
	   int review_No =Integer.parseInt(request.getParameter("theNo"));
	   String review_text = request.getParameter("review_text");
	   
	   Review f= new Review(review_No,review_text);
	   try {
		   musicDbUtil.deleteReview(f);
		   response.sendRedirect(request.getContextPath() +"/MusicControllerServlet?command=INQUIRY&&theNo="+review_No);
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   
	// TODO Auto-generated method stub
	
}

private void deleteMusic(HttpServletRequest request, HttpServletResponse response) {
	   int song_No =Integer.parseInt(request.getParameter("theNo"));
	   Songs c = new Songs(song_No);
	   try {
			musicDbUtil.deleteMusic(c);
			response.sendRedirect(request.getContextPath() +"/MusicControllerServlet?command=LIST");
		}catch(Exception e){
			e.printStackTrace();
		}
	// TODO Auto-generated method stub
	
}

private void updateMusic2(HttpServletRequest request, HttpServletResponse response) {
	    String song_Name = request.getParameter("song_Name");
			String singer = request.getParameter("song_Singer");
			String song_Year = request.getParameter("song_Year");
			String song_Genre = request.getParameter("song_Genre");
			String song_Mood = request.getParameter("song_Mood");
			String song_Url = request.getParameter("song_Url");
			String song_Id = request.getParameter("song_Id");
			int song_No =Integer.parseInt(request.getParameter("song_No"));
			Songs a = new Songs(song_No,song_Name, singer, song_Year, song_Genre, song_Mood,song_Url,song_Id);
			try {
				musicDbUtil.updateMusic(a);
				response.sendRedirect(request.getContextPath() +"/MusicControllerServlet?command=INQUIRY&&theNo="+request.getParameter("song_No"));
			}catch(Exception e){
				e.printStackTrace();
			}
	// TODO Auto-generated method stub
	
}

private void updateMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   int no = Integer.parseInt(request.getParameter("theNo"));
	   try {
		   List<Songs> datas = musicDbUtil.getDetailSongs(no);
		   String as = datas.get(0).getSong_Id();
		   request.setAttribute("music_Detail_list", datas);
		   request.setAttribute("song_Id", as);
		   request.setAttribute("song_No", no);
		   
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
	   RequestDispatcher dispatcher = request.getRequestDispatcher("/update-music-form.jsp");
	   dispatcher.forward(request,response);
}

private void addReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	   int review_No = Integer.parseInt(request.getParameter("song"));
	    String review_Id = request.getParameter("user_Id");
		String review_Text = request.getParameter("review");
		System.out.println(review_Text);
		
		Review b = new Review(review_No,review_Id,review_Text);
		musicDbUtil.addReview(b);
		response.sendRedirect(request.getContextPath() + "/MusicControllerServlet?command=INQUIRY&&theNo="+request.getParameter("song"));
	   
	// TODO Auto-generated method stub
	
}

private void listMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	   System.out.println("listMusic Start");
	   	try {
  		   	List<Songs> datas = musicDbUtil.getSongs();
  		   	request.setAttribute("music_list", datas);
  	   	}catch(Exception e) {
  	   		e.printStackTrace();
  	   	}
  	   	RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
  	   	dispatcher.forward(request, response);
  	}	



   private void addMusic(HttpServletRequest request, HttpServletResponse response) {
//	    System.out.println("addMusic Start");
	    String song_Name = request.getParameter("song_Name");
		String singer = request.getParameter("song_Singer");
		String song_Year = request.getParameter("song_Year");
		String song_Genre = request.getParameter("song_Genre");
		String song_Mood = request.getParameter("song_Mood");
		String song_Url = request.getParameter("song_Url");
		String song_Id = request.getParameter("song_Id");
		Songs a = new Songs(song_Name, singer, song_Year, song_Genre, song_Mood,song_Url,song_Id);
				
		try {
//			System.out.println("add시작");
			musicDbUtil.addMusic(a);
			response.sendRedirect(request.getContextPath() +"/MusicControllerServlet?command=LIST");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
   
   
   	private void selectMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		String song_Year = request.getParameter("song_Year");
   		String song_Genre = request.getParameter("song_Genre");
   		String song_Mood = request.getParameter("song_Mood");
   		
   		Songs a = new Songs(song_Year, song_Genre, song_Mood);
   		
   		try {
  		   	List<Songs> datas = musicDbUtil.getSelectSongs(a);
  		   	System.out.println(datas);
  		   	request.setAttribute("music_list", datas);
  	   	}catch(Exception e) {
  	   		e.printStackTrace();
  	   	}
  	   	RequestDispatcher dispatcher = request.getRequestDispatcher("/main.jsp");
  	   	dispatcher.forward(request, response);
   	}
   	private void inquiryMusic(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
 	   int no = Integer.parseInt(request.getParameter("theNo"));
 	   
 	  
 	   try {
 		   List<Songs> datas = musicDbUtil.getDetailSongs(no);
 		   List<Review> reviews = musicDbUtil.getReview(no);
 		  String as = datas.get(0).getSong_Id();
 		   request.setAttribute("music_Detail_list", datas);
 		   request.setAttribute("music_Review", reviews);   
 		   request.setAttribute("song_Id", as);
		   request.setAttribute("song_No", no);
 		   
 	   }catch(Exception e) {
 		   e.printStackTrace();
 	   }
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/DetailPage-music-form.jsp");
   	   	dispatcher.forward(request,response);
 	   
 	// TODO Auto-generated method stub
 	
 }
   	private void myMusic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		HttpSession session = request.getSession();
   		String song_Id  = (String)session.getAttribute("user_Id");
   		
   		System.out.println(song_Id);

	   try {
		   List<Songs> datas = musicDbUtil.getMySongs(song_Id);
		   List<Songs> datas1 = musicDbUtil.getMyHeart(song_Id);
		   
		   request.setAttribute("my_music_list", datas);
		   request.setAttribute("my_heart_list", datas1);

		   
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mymusic.jsp");
	   	dispatcher.forward(request,response);
	
}
   	
   	private void findUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String uname = request.getParameter("name");
		String uid = request.getParameter("id");
		String upn = request.getParameter("phone");
		switch(uid == "" ? 1 : 2) {
		case 1:
			if (uname != null & upn != null) {
				User user = musicDbUtil.getUserInfo1(uname, upn);
				if(user != null) {
					request.setAttribute("user", user.getUser_Id());
				}else {
					request.setAttribute("user", "none");
				}
			}
			break;
		case 2:
			if (uname != null & upn != null & uid != null) {
				User user = musicDbUtil.getUserInfo2(uname, upn, uid);
				if(user != null) {
					request.setAttribute("userpw", user.getUser_Pw());
				}else {
					request.setAttribute("userpw", "none");
				}
		}
			break;
		default:
			request.setAttribute("user", null);
		}			
		RequestDispatcher dispatcher = request.getRequestDispatcher("/find2.jsp");
		dispatcher.forward(request, response);
	}
	
	private void idCheck(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String id = request.getParameter("checkId");
		boolean user = musicDbUtil.checkId(id);		
		String num = String.valueOf(user);		
		request.setAttribute("check", num);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/check2.jsp");
		dispatcher.forward(request, response);
	}
	
	   private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		      HttpSession session = request.getSession();
		      String userNum = request.getParameter("unum");
		      int unum = Integer.parseInt(userNum);
		      String uid = request.getParameter("uid");
		      String upw = request.getParameter("upw");
		      String uname = request.getParameter("uname");
		      String upn = request.getParameter("upn");
		      User user = new User(unum, uid, upw, uname, upn);
		      System.out.println(user);
		      musicDbUtil.updateUser(user);
		      session.setAttribute("user_Pw", user.getUser_Pw());
		      session.setAttribute("user_Name", user.getUser_Name());
		      session.setAttribute("user_Pn", user.getUser_Pn());
		      RequestDispatcher dispatcher = request.getRequestDispatcher("/MusicControllerServlet?command=LIST");
		      dispatcher.forward(request, response);
		
	}




	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
		String userNum = request.getParameter("unum");
		int unum = Integer.parseInt(userNum); 
		User user = new User(unum);
		musicDbUtil.delUser(user);
		session.invalidate();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/MusicControllerServlet?command=LIST");
		dispatcher.forward(request, response);
		
	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
	 	try {
  		   	List<Songs> datas = musicDbUtil.getSongs();
  		   	request.setAttribute("music_list", datas);
  	   	}catch(Exception e) {
  	   		e.printStackTrace();
  	   	}
  	   
		
		String userId = request.getParameter("id");
		String userPw = request.getParameter("pw");
		User user = musicDbUtil.signIn(userId, userPw);
		System.out.print(user);
		if(user.getUser_Id() != null && user.getUser_Pw() != null && user.getUser_Name() != null && user.getUser_Pn() != null ) {
				
		HttpSession session = request.getSession();
		
		/* session.setMaxInactiveInterval(600); */
		session.setAttribute("user_No", user.getUser_No());
		session.setAttribute("user_Id", user.getUser_Id());
		session.setAttribute("user_Pw", user.getUser_Pw());
		session.setAttribute("user_Name", user.getUser_Name());
		session.setAttribute("user_Pn", user.getUser_Pn());
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("MusicControllerServlet?command=LIST");
		dispatcher.forward(request, response);
		System.out.println("성공");
		} else {
			System.out.println("실패");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/signin.jsp");
			dispatcher.forward(request, response);
		}
	}
	

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		// 회원가입
		String id = request.getParameter("uid");
		String pw = request.getParameter("upw");
		System.out.print(pw);
		String name = request.getParameter("uname");
		String pn = request.getParameter("upn");
		User theUser = new User(id, pw, name, pn);
	
		musicDbUtil.addUser(theUser);
		response.sendRedirect(request.getContextPath() + "/MusicControllerServlet?command=LIST");
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) {
		// 회원조회 

	}
	

   }
