<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>BOW-WOW</title>
	
<style>
	.form-group{
		width:50%;
		margin:50px auto auto auto;
	}
	
	#btn{
		margin:auto;
		display:block;
		
	}
</style>
</head>
<body>

	<div style="margin:50px auto 30px auto; text-align:center;" >
            <a href="${pageContext.request.contextPath}/"><img src="images/MainLogo.png"></a>
    </div>
	<div class="container" >
		<form:form action="Mypage" modelAttribute="edituser" method="POST"> 
			<input type="hidden" name="ddd" value="1"/>
			<form:hidden path="email"/>
			
			<form:hidden path="grade"/>
		
			<div class="form-group" >
				<label for="uname">비밀번호변경:</label>
				<input type="password" name="password" placeholder="변경할 비밀번호 입력" class="form-control" required/>
			
		 
			
				<label for="uname">닉네임변경:</label>
				<form:input path="userName" class="form-control" />
			
			
			
				<label for="uname">번호변경:</label>
				<form:input path="phone" class="form-control" />
			
			
			
				<label for="uname">주소변경:</label>
				<form:input path="address" class="form-control" />
				<br/>
				<button id="btn" type="submit" class="btn btn-warning" onclick="if (!(confirm('변경하시겠습니까'))) return false">변경하기</button>
			</div>
			
			
		</form:form>
	</div>
</body>
</html>