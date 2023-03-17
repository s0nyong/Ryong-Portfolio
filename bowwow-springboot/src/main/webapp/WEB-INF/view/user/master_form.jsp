<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<title>BOW-WOW</title>

<body>
	<div style="margin:50px auto 30px auto; text-align:center;" >
            <a href="${pageContext.request.contextPath}/"><img src="images/MainLogo.png"></a>
    </div>
	<div class="container">
		<h2>유저 권한</h2>
	 <table class="table">
			<thead>
                  <tr>
                    <th>유저이메일</th>
                    <th>유저등급</th>
                    <th>변경등급</th>
                    <th>권한변경</th>
                  </tr>
            </thead>

<form:form action="master_form" method="GET">
<c:forEach var="user_list" items="${user}">
	<form:form action="user_btn" method="GET">
						<input type="hidden" name="useremail" value="${user_list.email}"/>
					<tbody>	
						<tr>
						<td>${user_list.email}</td>
						<td>${user_list.grade}</td>

						<td>
                           <select name="grade">
                              <option value="" selected disabled hidden="">${user_list.grade}</option>
                              <option value="ROLE_ADMIN">ADMIN</option>
                              <option value="ROLE_VIP">VIP</option>
                              <option value="ROLE_일반">일반</option>
                           </select>
                        </td>

						<td><button type="submit" name="UPDATE" class="btn btn-warning">권한변경</button></td>
						</tr>
					</tbody>	
	</form:form>
</c:forEach>

</form:form>
</table>
</div>
</body>
</html>