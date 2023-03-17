<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import = "com.web.jdbc.*" %>
<%@ page import= "java.util.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>



<!DOCTYPE html>
<html>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


<body>

	<%= request.getAttribute("check") %>
	<%= request.getAttribute("test") %>
	값 : ${checl}
	값 : ${test}


			

		<hr>
	</form>
</body>
</html>