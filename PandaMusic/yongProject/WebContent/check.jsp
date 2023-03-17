<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import = "com.web.jdbc.*" %>
<%@ page import= "java.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>



<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script>

$(document).ready(function(){
	$(".check").click(function(){
		alert("hi");
	});
});

</script>

<body>
	
	<form action="MusicControllerServlet" method="GET">
		<input type="hidden" name="command" value="USER_CHECK"/>
			이름<br>
			<input type="text" name="checkId" ><p>
<button type="submit" class="check btn btn-danger btn-primary btn-lg btn-block">아이디 찾기</button><p>
<button type="button" class="check btn btn-danger btn-primary btn-lg btn-block">test</button><p>


			

		<hr>
	</form>
</body>
</html>