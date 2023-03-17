<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
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
		<c:if test="${param.error != null}">
			<script>
				alert("Invalid email and password!!!");
			</script>
		</c:if>

		<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
			<div class="form-group">
				<label for="uname">User email:</label>
				<input type="email" id="uname" name="username" placeholder="example@email.com" class="form-control" required />
				<div class="valid-feedback">Valid.</div>
      			<div class="invalid-feedback">Please fill out this field.</div>
			

			
				<label for="uname">User password:</label>
				<input type="password" id="pwd" name="password" placeholder="password" class="form-control" required/>
				<div class="valid-feedback">Valid.</div>
      			<div class="invalid-feedback">Please fill out this field.</div>
      			<br/>
      			<button id="btn" type="submit" class="btn btn-warning" value="login" >로그인</button>
			</div>			
		</form:form>
		<br/>
		<a href="${pageContext.request.contextPath}/signup_form"><button id="btn"class="btn btn-warning" >회원가입</button></a>
	</div>
</body>
</html>