<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import = "com.web.jdbc.*" %>
<%@ page import= "java.util.*" %>


<!DOCTYPE html>
<html>
<head>
<title>MUSIC RECOMMENDATION</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<div style = "text-align : center;">
  		<img src="images/yong.jpeg" width="200" height="200"><br>
  		<font size="5" color="purple" face="gulim">MUSIC RECOMMENDATION</font>
  		<br>
  		<%
		String todos =(String)session.getAttribute("user_Id");
		if(todos==null){
			out.println("<button type=\"button\" class=\"btn btn-danger\" onclick=\"window.location.href=\'signin.jsp\'; return false;\">"+"로그인"+"</button>"+
					"<button type=\"button\" class=\"btn btn-danger\" onclick=\"window.location.href=\'signup.jsp\'; return false;\">"+"회원가입"+"</button>");
		}else{
			out.println(
				"<a type=\"button\" class=\"btn btn-danger\" onclick=\"window.location.href=\'MusicControllerServlet?command=MY_MUSIC\'; return false;\">"+"추천목록"+"</>"+
				"<a type=\"button\" href=\"/yongProject/mypage.jsp\" class=\"btn btn-danger\">마이페이지</a>"+
				"<a type=\"button\" id=\"logout\" class = \"btn btn-danger\" href=\"logout.jsp\">로그아웃</a>");}
		%>

  			<hr contenteditable="false" data-ke-type="horizontalRule" data-ke-style="style5">
  		<hr>
  	</div>
     <style>
     	.flex-container{
            display: flex;
            flex-wrap: wrap;
            margin-left: 115px;
        }
        .flex-item{
            text-align: center;
            justify-content: space-around;  			
        }
     </style>
</head>

<body>
	<div class="container">
		<form action="MusicControllerServlet" class="was-validated" method="GET">
			<div id = "content"><br>
				<%
		if(todos==null){
			out.println("<input type=\"button\" class=\"btn btn-outline-secondary\" value=\"recommend\""+ 
					   "style = \"position: absolute; left:47%;\""+
		               "class=\"add-music-button\""+
		               "onclick=\"window.location.href=\'signin.jsp\'; return false;\">");
		}else{
			out.println("<input type=\"button\" class=\"btn btn-outline-secondary\" value=\"recommend\""+ 
					   "style = \"position: absolute; left:47%;\""+
		               "class=\"add-music-button\""+
		               "onclick=\"window.location.href=\'add-music-form.jsp\'; return false;\">");
		}
		%>
			</div>
			<br><br><br>
		<div style = "text-align : center;">
		<div>
         <input type="text" name="song_Name" placeholder="음악명을 입력해주세요">
         <button type="submit" value="SEARCH">검색</button> 
         <input type="hidden"name="command" value="SEARCH" />
         
         </div>
			<div>
				<button type="submit" name="command" value="LIST">all</button>
			</div>
			</form>
			<form action="MusicControllerServlet" class="was-validated" method="GET">
		<span>
			<select id="song_Year" name ="song_Year">
				<option value="2020">2020</option>
				<option value="2010">2010</option>
				<option value="2000">2000</option>
				<option value="1990">1990</option>
				<option value="1980">1980</option>
			</select>
		</span>
		<span>
			<select id = "song_Genre" name ="song_Genre">
				<option value="발라드">발라드</option>
				<option value="랩/힙합">랩/힙합</option>
				<option value="댄스">댄스</option>
				<option value="트로트">트로트</option>
				<option value="해외">해외</option>
			</select>
		</span>
		<span>
			<select id = "song_Mood" name ="song_Mood">
				<option value="신나는">신나는</option>
				<option value="편안한">편안한</option>
				<option value="조용한">조용한</option>
				<option value="슬픈">슬픈</option>
				<option value="행복한">행복한</option>
			</select>
		</span>
		</div>
		
	<button type="submit" value="SELECT">SELECT</button>
	<input type="hidden"name="command" value="SELECT" />
	</form>
	

	
	
	
		
	</div><br><br><br>
    <div class="flex-container">
		<table>
			<c:forEach var = "list" items = "${music_list}">
			<c:set var="key" value="${fn:replace(list.song_Url,'https://youtu.be/','')}" />
				<div class="flex-item" onclick="window.location.href='MusicControllerServlet?theNo=${list.song_No}&&command=INQUIRY'; return false;">
        			<img src="https://img.youtube.com/vi/${key}/0.jpg"  width="275">
        				<p>name: ${list.song_Name}</p>
						<p>singer: ${list.song_Singer}</p>
						<p>#${list.song_Year} #${list.song_Genre} #${list.song_Mood}</p>
				</div>
				<div>
					<input type="button" value="♥" onclick="window.location.href='MusicControllerServlet?song_No=${list.song_No}&&command=LIKE'; return false;">${list.song_Count}
				</div>
			</c:forEach>
		</table>
	</div>
</body>
</html>