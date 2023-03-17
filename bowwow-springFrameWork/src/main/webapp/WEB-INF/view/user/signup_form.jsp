<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	<div class="container">
		<form:form action="signup" method="POST">
			<div class="form-group">
				<label for="uname">Nickname:</label>
		 		<input type="text" name="userName" placeholder="닉네임" class="form-control" required/>
		 		<div class="valid-feedback">Valid.</div>
      			<div class="invalid-feedback">Please fill out this field.</div>
      		
				<label for="uname">Email:</label>
				<input type="text" name="email" placeholder="ex) example@email.com" class="form-control" required/>
				<div class="valid-feedback">Valid.</div>
      			<div class="invalid-feedback">Please fill out this field.</div>
			
				<label for="uname">Password:</label>
				<input type="text" name="password" placeholder="password" class="form-control" required/>
				<div class="valid-feedback">Valid.</div>
      			<div class="invalid-feedback">Please fill out this field.</div>
      		
				<label for="uname">Phone:</label>
				<input type="text" name="phone" placeholder="ex) 01000000000" class="form-control" required/>
				<div class="valid-feedback">Valid.</div>
      			<div class="invalid-feedback">Please fill out this field.</div>
			
				<label for="uname">Address: </label>
				<input type="text" name="address" placeholder="주소" class="form-control" required/>
				<div class="valid-feedback">Valid.</div>
      			<div class="invalid-feedback">Please fill out this field.</div>
      			<br/>
      			<button id="btn" type="submit" class="btn btn-warning" onclick="alert('가입되었습니다!')">가입하기</button>
			</div>
		</form:form>
	</div>
</body>
</html>