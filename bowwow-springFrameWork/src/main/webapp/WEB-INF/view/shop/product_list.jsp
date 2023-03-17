<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>  
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link type="text/css" rel="stylesheet" href="css/Main.css">
<title>BOW-WOW</title>
<style>
#sform {
          display: inline-block;
          text-align: center;
        }
</style>

</head>
<body>
 	<header>
      <div class="container">
         <!-- 메인 로고 -->
         <div style="margin:50px auto 30px auto; text-align:center;" >
            <a href="${pageContext.request.contextPath}/"><img src="images/MainLogo.png"></a>
         </div>
         
         <table style="float:right">
         
            <!-- 로그인버튼 & 회원가입 authenticated 되지않았을때 -->
            <security:authorize access="isAnonymous()">
	            <td>
	               <a href="${pageContext.request.contextPath}/login"><button class="btn btn-warning">로그인</button></a>
	               <a href="${pageContext.request.contextPath}/signup_form"><button class="btn btn-warning">회원가입</button></a>
	            </td>   
            </security:authorize>
          
            <!-- 로그아웃 & mypage 버튼 authenticated 됐을때-->
            <security:authorize access="isAuthenticated()">
               <!-- 로그아웃버튼 authenticated 됐을때-->
               <td>
	               <form:form class="form-inline center" action="${pageContext.request.contextPath}/logout" method="POST">
	                  <input class="btn btn-warning" type="submit" value="로그아웃">
	               </form:form>
               </td>
               <td>
            	<div class="dropdown">
					<button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">MY</button>
					<div class="dropdown-menu">
						<!-- 마이페이지 버튼 -->
						<form:form action="${pageContext.request.contextPath}/Mypage" method="POST">
							<input class="dropdown-item" type="submit" value="내정보">
						</form:form>

						<!-- 장바구니 버튼 -->
						<form:form action="${pageContext.request.contextPath}/cart" method="GET">
							<input class="dropdown-item" type="submit" value="장바구니">
						</form:form>

						<!-- 구매내역 버튼 -->
						<form:form action="${pageContext.request.contextPath}/orderlist" method="GET">
							<input class="dropdown-item" type="submit" value="구매내역">
						</form:form>
					</div>
				</div>
             </td>
             <td>
                <!-- 관리자 페이지-->
				<security:authorize access="hasRole('ADMIN')">
				<form:form class="form-inline center" action="${pageContext.request.contextPath}/master_form"
						method="GET">
						<input class="btn btn-warning" type="submit" value="관리자">
				</form:form>
				</security:authorize>
				</td>
				</security:authorize>
          </table>
         <br><br>
         <!-- 제품명 검색 -->   
         <div style="float:right">      
            <form:form action="search" method="GET">
               <input type="text" name="searchName" placeholder="🔎 Search..." required/>
               <input type="hidden" name="search" value="product_name"/>
               <input type="submit" value="검색" class="btn btn-warning"/>
            </form:form>
         </div>
          <!-- nav -->
         <div> 
         <br>
            <ul class="nav nav-tabs">
               <!-- (카테고리별 검색) -->
               <li class="nav-item dropdown">
                   <a class="nav-link dropdown-toggle active" data-toggle="dropdown" href="#">카테고리</a>
                   <div class="dropdown-menu">
                      <form:form action="search" method="GET">
                         <input type="hidden" name="search" value="category"/>
                         <button type="submit" name="searchName" class="dropdown-item" value="사료">사료</button>
                           <button type="submit" name="searchName" class="dropdown-item" value="간식">간식</button>   
                           <button type="submit" name="searchName" class="dropdown-item" value="배변/위생">배변/위생</button>   
                           <button type="submit" name="searchName" class="dropdown-item" value="의류/악세서리">의류/악세서리</button>   
                           <button type="submit" name="searchName" class="dropdown-item" value="장난감">장난감</button>   
                           <button type="submit" name="searchName" class="dropdown-item" value="목욕/미용">목욕/미용</button>   
                           <button type="submit" name="searchName" class="dropdown-item" value="하우스">하우스</button>   
                           <button type="submit" name="searchName" class="dropdown-item" value="식기/급수기">식기/급수기</button>
                        </form:form>
                   </div>
                 </li>
               <li class="nav-item">
               <form:form action="sale" method="GET">
                 <a class="nav-link active" href="sale">할인중</a>
               </form:form>
               </li>
               <!-- <li class="nav-item">
                 <a class="nav-link" href="#">신상품</a>
               </li> -->
               <li class="nav-item">
               <form:form action="vip" method="GET">
                 <a class="nav-link active" href="vip">VIP</a>
               </form:form>
               </li> 
            </ul>
         </div>
      </div>
   </header>   
   <br><br>
 
   <!-- 제품 리스트 테이블 -->
   <div class="flex-container">
      <c:set var="i" value="0"/>
      <c:set var="j" value="4"/>
      <table>
         <c:forEach var="productlist" items="${products}">
            <c:if test="${i%j == 0 }">
                <tr>
             </c:if>
               <td>
                  <div class="flex-item"><a href="${pageContext.request.contextPath}/detail?productNum=${productlist.product_num}">
                     <img src="<c:url value="/images/${productlist.product_img_path}"/>" >
                     <br>
                            <b style="font-size:100%;"><span>${productlist.product_name}</span></b>
                            <p style="font-size:14px;">
                               ${productlist.product_desc}<br>
                               <fmt:formatNumber type="number" maxFractionDigits="3" value="${productlist.product_price}" />원<br>
                               평점: ${productlist.product_score}점
                            </p>
               </a>
                         </div>

               </td>
            <c:if test="${i%j == j-1 }">
                </tr>
             </c:if>
             <c:set var="i" value="${i+1 }" />
         </c:forEach>
      </table>
   </div>
   

   
   
</body>
</html>