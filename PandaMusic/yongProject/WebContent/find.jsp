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
.jumbotron{
			margin-top : 10%;
}

</style>
<body>

	<form action="MusicControllerServlet" method="GET">
		<input type="hidden" name="command" value="USER_FIND"/>
		
		<% 
		switch(request.getParameter("num").equals("1") ? 1 : 2){
		case 1:%>
			<div class="container">
				<div class="jumbotron">
						<div class="form-group">
					      <label for="NAME">이름</label>
					      <input type="text" name="name" class="form-control" id="NAME" placeholder="이름 입력" required>
					      <div class="valid-feedback">Valid.</div>
					      <div class="invalid-feedback">필수 입력</div>
					    </div>
						<div class="form-group">
					      <label for="PN">전화번호</label>
					      <input type="text" name="phone" class="form-control" id="PN" placeholder="전화번호 입력" required>
					      <div class="valid-feedback">Valid.</div>
					      <div class="invalid-feedback">필수 입력</div>
					    </div>		
					<input type="hidden" name="id">
					<button type="submit" class="btn btn-danger btn-primary btn-lg btn-block">아이디 찾기</button><p>
					</div>
				</div>
			<%break;
		case 2:%>
			<div class="container">
				<div class="jumbotron">
						<div class="form-group">
					      <label for="NAME">이름</label>
					      <input type="text" name="name" class="form-control" id="NAME" placeholder="이름 입력" required>
					      <div class="valid-feedback">Valid.</div>
					      <div class="invalid-feedback">필수 입력</div>
					    </div>
						<div class="form-group">
					      <label for="ID">아이디</label>
					      <input type="text" name="id" class="form-control" id="ID" placeholder="아이디 입력" required>
					      <div class="valid-feedback">Valid.</div>
					      <div class="invalid-feedback">필수 입력</div>
					    </div>							    
						<div class="form-group">
					      <label for="PN">전화번호</label>
					      <input type="text" name="phone" class="form-control" id="PN" placeholder="전화번호 입력" required>
					      <div class="valid-feedback">Valid.</div>
					      <div class="invalid-feedback">필수 입력</div>
					    </div>		
					<button type="submit" class="btn btn-danger btn-primary btn-lg btn-block">비밀번호 찾기</button><p>
					</div>
				</div>
			</div>	
			<%break;} %>	






	</form>
</body>
</html>