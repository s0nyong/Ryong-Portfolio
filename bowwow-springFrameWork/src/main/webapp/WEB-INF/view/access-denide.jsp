<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %> 
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<body>
      <div class="container">
      <c:set var="username" value="${username}"/>
         <!-- 메인 로고 -->
         <div style="margin:50px auto 30px auto; text-align:center;" >
            <a href="${pageContext.request.contextPath}/"><img src="images/MainLogo.png"></a>
            <br><br>
				${username}님 권한이없습니다
		
         </div>




</div>
</body>
</html>