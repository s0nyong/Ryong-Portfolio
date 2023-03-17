 <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import = "com.web.jdbc.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "java.util.*" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<form>
<div id="song_Detail" style="width:800px;height:500px;" align="center">
<c:forEach var = "list" items = "${music_Detail_list}">
<c:set var="key" value="${fn:replace(list.song_Url,'https://youtu.be/','')}" />
		<iframe width="560" height="315" src="https://www.youtube.com/embed/${key}" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		<p>Song_No:${list.song_No}</p>
		<p>Song_Name: ${list.song_Name}</p>
		<p>Singer: ${list.song_Singer}</p>
		<p>Song_since: ${list.song_Year}</p> 
		<p>Song_Genre: ${list.song_Genre} </p>
		<p>Song_Mood: ${list.song_Mood}</p>
		</c:forEach>
		</div>
		<br>
		<% 
		String todos2 =(String)session.getAttribute("user_Id");
		String todos3 =(String)request.getAttribute("song_Id");
		int song_No =(int)request.getAttribute("song_No");
		if(todos3.equals(todos2) && todos2!=null){
			out.println("<button type=\"button\" class=\"btn btn-danger\" onclick=\"window.location.href=\'MusicControllerServlet?command=MUSIC_UPDATE&&theNo="+song_No+"\'; return false;\">"+"개시물수정"+"</button>");
			out.println("<button type=\"button\" class=\"btn btn-danger\" onclick=\"window.location.href=\'MusicControllerServlet?command=MUSIC_DELETE&&theNo="+song_No+"\'; return false;\">"+"게시물삭제"+"</button>");
		}else{
			
		}
		%>
		<br>
		</form>
		<hr>
		<label>리뷰</label>
		<hr>
		<br>
		<c:forEach var ="review_list" items="${music_Review}">
		<p>작성자:${review_list.review_Id}</p>
		<p>내용:${review_list.review_Text}</p>
		<p>작성시간:${review_list.review_Time}</p>
		<c:set var="his" value="${review_list.review_Id}"/>
		<c:set var="Text" value="${review_list.review_Text}"/>
		<%
			String k3 = (String)pageContext.getAttribute("Text");
			String ks =(String)session.getAttribute("user_Id");
			String ks2 =(String)pageContext.getAttribute("his");
			if(ks2.equals(ks)){
				out.println("<details><summary>수정/삭제</summary>");
				
				out.println("<form action=\"MusicControllerServlet\" Method=\"GET\"><input type=\"hidden\" name=\"command\" value=\"REVIEW_UPDATE\"/>");
				out.println("<input type=\"hidden\" name=\"song\" value="+song_No+">");
				out.println("<input type=\"text\" name=\"review\"/>");
				
				out.println("<input type=\"hidden\" name=\"review_Id\" value=\"${review_list.review_Id}\"/><input type=\"hidden\" name=\"review_text\" value=\"${review_list.review_Text}\"/><input type=\"hidden\" name=\"review_id\" value=\"${review_list.review_Id}\"/>");

				out.println("<button type=\"submit\">수정</button>");
				out.println("<button type=\"button\" class=\"btn btn-danger\" onclick=\"window.location.href=\'MusicControllerServlet?command=REVIEW_DELETE&&theNo="+song_No+"&&review_text="+k3+"\'; return false;\">"+"리뷰삭제"+"</button>");
				out.println("</details>");
			}
		
		%>
		<hr>
		</c:forEach>
		<hr>
	 	<form action="MusicControllerServlet" Method="GET">
	 	<input type="hidden" name="command" value="REVIEW_ADD"/> 
	 	<input type="hidden" name="song" value="${song_No}"/>
	 	<input type="hidden" name="user_Id"value="<%=session.getAttribute("user_Id")%>"/>
	 	<%
			if(session.getAttribute("user_Id")!=null){
				out.println("<input type=\"text\" name=\"review\" style=\"width:560px;height:360px;\"/>"+
				"<button type=\"submit\">확인</button>");
				out.println("<hr>");
			}
		%> 
		</form>
		<br>
		<input type="button" class="btn btn-outline-secondary" value="메인화면이동" 
			   style = "position: absolute; left:47%;"
               onclick="window.location.href='MusicControllerServlet?command=LIST'; return false;"
               class="add-music-button"/>

</body>
</html> 