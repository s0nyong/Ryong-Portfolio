<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import = "com.web.jdbc.*" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <div style = "text-align : center;">
  		<img src="images/yong.jpeg" width="200" height="200"><br>
  		<font size="5" color="purple" face="gulim">MUSIC RECOMMENDATION</font>
  		<br>
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
<title>My Music</title>
</head>
<body>
<div class="container">
<form>
<div style = "text-align : center;">
<h1>추천 목록</h1>
<br>
<div class="flex-container">
	<table>
		<c:forEach var = "list" items = "${my_music_list}">
		<c:set var="key" value="${fn:replace(list.song_Url,'https://youtu.be/','')}" />
			<div class="flex-item">
				<img src="https://img.youtube.com/vi/${key}/0.jpg"  width="275">
				<p>Song_Name: ${list.song_Name}</p>
				<p>Singer: ${list.song_Singer}</p>
				<p>#${list.song_Year} #${list.song_Genre} #${list.song_Mood}</p>
			</div>
		</c:forEach>
	</table>
</div>

		<br>
		<hr contenteditable="false" data-ke-type="horizontalRule" data-ke-style="style5">
  		<hr>
<h1>좋아요 목록</h1>
<br>
<div class="flex-container">
	<table>
		<c:forEach var = "list" items = "${my_heart_list}">
		<c:set var="key" value="${fn:replace(list.song_Url,'https://youtu.be/','')}" />
			<div class="flex-item">
				<img src="https://img.youtube.com/vi/${key}/0.jpg"  width="275">
				<p>Song_Name: ${list.song_Name}</p>
				<p>Singer: ${list.song_Singer}</p>
				<p>#${list.song_Year} #${list.song_Genre} #${list.song_Mood}</p>
			</div>	
		</c:forEach>
	</table>
</div>
		<br>
		
		<input type="button" class="btn btn-outline-secondary" value="메인화면이동" 
			   style = "position: absolute; left:47%;"
               onclick="window.location.href='MusicControllerServlet?command=LIST'; return false;"
               class="add-music-button"/>

 </div>
</form>
</div>
</body>
</html>