<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import = "java.util.*, com.web.jdbc.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>


<head>

<script>
$(document).ready(function(){
	$(".update").click(function(){
		if(confirm("정말 수정하시겠습니까?")){
			alert("수정이 완료되었습니다."+" 다시 로그인해주세요.")
		}else{
			return false;
		}
	});
});

$(document).ready(function(){
	$(".delete").click(function(){
		if(confirm("정말 탈퇴하시겠습니까?")){
			alert("탈퇴가 완료되었습니다.")
		}else{
			return false;
		}
	});
});

</script>
<style>
.jumbotron{
			margin-top : 10%;
}
</style>
<title>My Page</title>
</head>
<body>
	<hr>
	<form action="MusicControllerServlet" method="GET" class="was-validated">
	<input type="hidden" name="unum" value="<%=session.getAttribute("user_No")%>"/>

				<div class="container">
				<div class="jumbotron">
						<div class="form-group">
					      <label for="NAME">이름</label>
					      <input type="text" class="form-control" placeholder="이름" name="uname" value="<%=session.getAttribute("user_Name")%>" required>
					      <div class="valid-feedback">Valid.</div>
					      <div class="invalid-feedback">필수 입력</div>
					    </div>
						<div class="form-group">
					      <label for="PN">비밀번호</label>
					      <input type="text" class="form-control" placeholder="비밀번호" name="upw" value="<%=session.getAttribute("user_Pw")%>"required>
					      <div class="valid-feedback">Valid.</div>
					      <div class="invalid-feedback">필수 입력</div>
					    </div>							    
						<div class="form-group">
					      <label for="PN">전화번호</label>
					      <input type="text" class="form-control" placeholder="휴대전화 번호" name="upn" value="<%=session.getAttribute("user_Pn")%>"required>
					      <div class="valid-feedback">Valid.</div>
					      <div class="invalid-feedback">필수 입력</div>
					    </div>		
						<button type="submit" class="btn btn-danger btn-primary" name="command" value="USER_UPDATE">정보 수정</button>
						<button type="submit" class="btn btn-danger btn-primary" name="command" value="USER_DELETE">회원 탈퇴</button>
					</div>
				</div>
	</form>
</body>
</html>