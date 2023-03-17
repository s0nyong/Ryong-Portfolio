<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import = "com.web.jdbc.*" %>
<%@ page import= "java.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>



<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


<style>

a{
	padding : 10px;
}

.jumbotron{
			margin-top : 10%;
}



</style>


<body>					
		<% 
		switch(request.getAttribute("userpw") == null ? 1 : 2){
		case 1:%>
			<div class="container">
				<div class="jumbotron">
					<div class="form-group">
					<%if(request.getAttribute("user")=="none"){%>
						<h2>😢일치하는 회원 정보가 없습니다.</h2>
					<%}else{
					%><h2>😃회원님의 아이디는 ${user} 입니다.</h2><%}%>
						<div class="container">
							<a href="/yongProject/signin.jsp" type="submit" class="btn btn-danger">로그인 하기</a>
							<c:url var = "findpw" value="/find.jsp">
								<c:param name="num" value="2"></c:param>
							</c:url>											
							<a href="${findpw}" type="submit" id="bt" class="btn btn-danger">비밀번호 찾기</a> 
							<c:url var = "home" value="MusicControllerServlet"></c:url>
							<a href="${home}" type="submit" class="btn btn-danger">홈으로</a>
						</div>
					</div>
				</div>
			</div>	
			<%break;
		case 2:%>
			<div class="container">
				<div class="jumbotron">
					<div class="form-group">
					<%if(request.getAttribute("userpw")=="none"){%>
						<h2>😢일치하는 회원 정보가 없습니다.</h2>
					<%}else{
					%><h2>😃회원님의 비밀번호는 ${user} 입니다.</h2><%}%>					
						<div class="container">
							<a href="/yongProject/signin.jsp" type="submit" class="btn btn-danger">로그인 하기</a>
							<c:url var = "home" value="MusicControllerServlet"></c:url>
							<a href="${home}" type="submit" class="btn btn-danger">홈으로</a>
						</div>						
					</div>
				</div>
			</div>
			<%break;}%>

			
				

	
</body>
</html>