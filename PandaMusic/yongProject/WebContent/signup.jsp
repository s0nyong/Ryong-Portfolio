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

<script>
$(document).ready(function(){
	$(".signup").click(function(){
		if(confirm("정말 해당 정보로 가입을 하시겠습니까?")){
			alert("회원가입이 완료되었습니다.")
		}else{
			return false;
		}
	});
});
</script>

<script>
     $(document).ready(function(){
        $(".check").click(function(){
            alert("이봐요");
        })
    }); 
</script>

<style>
.jumbotron{
			margin-top : 10%;
}

</style>


<body>
	<form action="MusicControllerServlet" method="GET" class="was-validated">
		<input type="hidden" name="command" value="USER_ADD"/>	
		

			<div class="container">
				<div class="jumbotron">
			    <div class="form-group">
			      <label for="uid">아이디:</label>			      
						<c:url var="checkLink" value="MusicControllerServlet">
							<c:param name="command" value="ID_CHECK"/>
							<c:param name="checkId" value="uid"/>
						</c:url>			      
			      <a type="button" class="check btn btn-success" href="${checkLink}">중복확인</a>
			      <%= request.getAttribute("check") %>







			      <input type="text" class="form-control" id="uid" placeholder="" name="uid" pattern="^[a-zA-Z0-9]{1,20}" required>
			      <div class="valid-feedback">멋진 아이디군요!</div>
			      <div class="invalid-feedback">특수문자를 제외한 8~16자 영문 소문자와 숫자를 입력하세요..</div>
			    </div>
			    <div class="form-group">
			      <label for="upw">비밀번호:</label>
			      <input type="text" class="form-control" id="upw" placeholder="" name="upw" pattern="^[a-zA-Z0-9]{1,20}" required>
			      <div class="valid-feedback"></div> 
			      <div class="invalid-feedback">8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</div>
			    </div>
    
			    <div class="form-group">
			      <label for="uname">이름:</label>
			      <input type="text" class="form-control" id="uname" placeholder="" name="uname" pattern="^[가-힣]{2,5}" required>
			      <div class="valid-feedback">예쁜 이름이군요!</div>
			      <div class="invalid-feedback">필수 입력정보입니다.</div>
			    </div>
			    <div class="form-group">
			      <label for="upn">전화번호:</label>
			      <input type="text" class="form-control" id="upn" placeholder="-을 생략하고 입력하세요." name="upn" pattern="^[0-9]{8,11}" required>
			      <div class="valid-feedback"></div>
			      <div class="invalid-feedback">- 없이 숫자만 입력하세요</div>
			    </div>
				<button type="submit" class=" signup btn btn-danger btn-primary btn-lg btn-block">가입하기</button><p>
			</div>
			</div>
	</form>
</body>
</html>