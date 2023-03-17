<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import = "com.web.jdbc.*" %>
<%@ page import= "java.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "java.text.SimpleDateFormat" %>


<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.jumbotron{
			margin-top : 10%;
}

</style>


<body>
	<form action="MusicControllerServlet" method="GET">
		<input type="hidden" name="command" value="USER_LOGIN"/>

	<div class="container">
		<div class="jumbotron">
				<div class="form-group">
			      <label for="ID">아이디</label>
			      <input type="text" class="form-control" id="ID" placeholder="아이디 입력" name="id" required>
			      <div class="valid-feedback">Valid.</div>
			      <div class="invalid-feedback">필수 입력</div>
			    </div>
			    
			    <div class="form-group">
			      <label for="PW">비밀번호</label>
			      <input type="text" class="form-control" id="PW" placeholder="비밀번호 입력" name="pw" required>
			      <div class="valid-feedback">Valid.</div>
			      <div class="invalid-feedback">필수 입력</div>
			    </div>
				<input type="submit" value="로그인" class="btn btn-danger btn-primary btn-lg btn-block"/>
				<hr>
				<div>
					<h5><input type="checkbox" value="asd" style="width:20px; height:20px;">아이디 저장</h5>
					  <div class="custom-control custom-switch">
					    <h5><input type="checkbox" class="custom-control-input custom-control custom-switch" id="switch1">
					    <label class="custom-control-label" for="switch1" >로그인 상태 유지</label></h5>
					  </div>
				</div>
				<hr>
						<c:url var = "findid" value="/find.jsp">
							<c:param name="num" value="1"></c:param>
						</c:url>
						<c:url var = "findpw" value="/find.jsp">
							<c:param name="num" value="2"></c:param>
						</c:url>														
					<a href="${findid}" style="color:grey; background:pink;">아이디 찾기</a> 
					<a href="${findpw}" type="button" style="color:grey; background:pink;">비밀번호 찾기</a> 					
					<a href="signup.jsp" style="color:grey; background:pink;">회원가입</a>
				<hr>
			</div>
		</div>





</form>
</body>
</html>