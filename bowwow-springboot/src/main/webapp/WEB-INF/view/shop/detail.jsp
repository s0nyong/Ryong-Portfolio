<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<style>
.star-rating {
	border: solid 1px #ccc;
	display: flex;
	flex-direction: row-reverse;
	font-size: 1.5em;
	justify-content: space-around;
	padding: 0 .2em;
	text-align: center;
	width: 5em;
}

.star-rating input {
	display: none;
}

.star-rating label {
	color: #ccc;
	cursor: pointer;
}

.star-rating :checked ~ label {
	color: #f90;
}

.star-rating label:hover, .star-rating label:hover ~ label {
	color: #fc0;
}

</style>
<meta charset="UTF-8">
<title>BOW-WOW</title>
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
	
	
	<div class="container">	
		<table>
			<c:forEach var="productlist" items="${products}">
				<c:set var="product_num" value="${productlist.product_num}"/>
				<div class="row">
					<div class="col-md-5">
						<img src="<c:url value="/images/${productlist.product_img_path}"/>" width='90%;'/>
					</div>
					<div class="col-md-7">
						<h3>${productlist.product_name}</h3>
						<p>상품설명: ${productlist.product_desc}</p>
						<p>
							<security:authorize access="hasRole('VIP')">
								<c:if
									test="${productlist.product_discount eq 0.4 or productlist.product_discount eq 0.3}">
	        			가격: ${productlist.product_price*productlist.product_discount}원<span class="badge badge-danger">할인</span>
	        		</c:if>
								<c:if test="${productlist.product_discount eq 1 }">
	        			가격: ${productlist.product_price}원
	        		</c:if>
							</security:authorize>
							<security:authorize access="hasRole('ADMIN')">
								<c:if
									test="${productlist.product_discount eq 1 or productlist.product_discount eq 0.4}">
						  가격: <fmt:formatNumber type="number" maxFractionDigits="3" value="${productlist.product_price}" />원
	        			
	        		</c:if>
								<c:if test="${productlist.product_discount eq 0.3}">
	        			가격: ${productlist.product_price*productlist.product_discount}원<span class="badge badge-danger">할인</span>
	        		</c:if>
							</security:authorize>
						</p>
						<p>남은수량: ${productlist.product_stock}개</p>
						<p>별점: ${productlist.product_score}점</p>
						<hr class="my-4">
				<form:form method="GET">
					<div class="form-group">
						<label>수량</label> <input type="number" name="product_count"
							class="form-control" value="1" min="1" max="100"/>
					</div>
					<input type="hidden" name="product_name"
						value="${productlist.product_name}" />
					<input type="hidden" name="product_price"
						value="${productlist.product_price}" />
					<div  style="float:right;">
					<button type="submit" formaction="add" class="btn btn-warning" onclick="if (!(confirm('구매하시겠습니까'))) return false">구매하기</button>
					
					<button type="submit" formaction="add_cart" class="btn btn-warning" onclick="if (!(confirm('장바구니에 담으시겠습니까'))) return false">장바구니에 담기</button>
					</div>
				</form:form>
					</div>
				</div>
			</c:forEach>	
		</table>
	</div>
	<br>
	<div class="container" style="margin-bottom:100px;">
		  <h2>COMMENT</h2>
		<!-- review 리스트 -->
		<!-- review 리스트,수정,삭제 -->
		<table class="table">
		<c:set var="username" value="${username}"/>
			<thead>
                  <tr>
                    <th style="text-align:center;">순번</th>
                    <th style="text-align:center;">아이디</th>
                    <th style="text-align:center;">댓글내용</th>
                    <th style="text-align:center;">평점</th>
                    <th style="text-align:center;">등록일시</th>
                    <th style="text-align:center;">수정/삭제</th>
                  </tr>
            </thead>
			<tbody>
			<c:forEach var="review_list" items="${reviews}" varStatus="status">
			<c:set var="username" value="${username}"/>
			<c:set var="ratingone" value="⭐"/>
			<c:set var="ratingtwo" value="⭐⭐"/>
			<c:set var="ratingthree" value="⭐⭐⭐"/>
			<c:set var="ratingfour" value="⭐⭐⭐⭐"/>
			<c:set var="ratingfive" value="⭐⭐⭐⭐⭐"/>
				<tr>
		  <c:choose>
				<c:when test="${review_list.username eq username}">
					<!-- 리뷰버튼 -->
						<form:form action="review_btn" method="GET">
							<input type="hidden" name="productNo" value="${product_num}" />
							<input type="hidden" name="review_num"value="${review_list.review_num}">
							<input type="hidden" name="product_num"value="${review_list.product_num}" />
							<input type="hidden" name="username"value="${review_list.username}" />
							<input type="hidden" name="product_score"value="${review_list.product_score}" />
			

							<td style="text-align:center;">${status.count}</td>
							<td style="text-align:center;">${review_list.username}</td>
							<td width='40%;'style="word-break:break-all;">${review_list.coment}</td>
							<c:if test="${review_list.product_score == 5.0}">
							<td style="text-align:center;">${ratingfive} ${review_list.product_score}</td>
							</c:if>
							<c:if test="${review_list.product_score == 4.0}">
							<td class="star" style="text-align:center;">${ratingfour} ${review_list.product_score}</td>
							</c:if>
							<c:if test="${review_list.product_score == 3.0}">
							<td style="text-align:center;">${ratingthree} ${review_list.product_score}</td>
							</c:if>
							<c:if test="${review_list.product_score == 2.0}">
							<td style="text-align:center;">${ratingtwo} ${review_list.product_score}</td>
							</c:if>
							<c:if test="${review_list.product_score == 1.0}">
							<td style="text-align:center;">${ratingone} ${review_list.product_score}</td>
							</c:if>
							<td style="text-align:center;">${review_list.coment_date}</td>
							<td style="text-align:center;"><div class="dropdown">
								<button type="button" class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown">수정/삭제</button>
								<div class="dropdown-menu">
									<input type="text" name="coment" value="${review_list.coment}"/>
									<button type="submit" name="update" value="update"class="badge badge-warning">리뷰수정</button>
									<button type="submit" name="delete" value="delete" class="badge badge-warning">리뷰삭제</button>
								</div>
									</div></td>
						</form:form>
				</c:when>
       		</c:choose>	
					
				<c:if test="${review_list.username ne username}">
					<td style="text-align:center;">${status.count}</td>
					<td style="text-align:center;">${review_list.username}</td>
					<td width='40%;'style="word-break:break-all">${review_list.coment}</td>
					<c:if test="${review_list.product_score == 5.0}">
					<td style="text-align:center;">${ratingfive} ${review_list.product_score}</td>
					</c:if>
					<c:if test="${review_list.product_score == 4.0}">
					<td style="text-align:center;">${ratingfour} ${review_list.product_score}</td>
					</c:if>
					<c:if test="${review_list.product_score == 3.0}">
					<td style="text-align:center;">${ratingthree} ${review_list.product_score}</td>
					</c:if>
					<c:if test="${review_list.product_score == 2.0}">
					<td style="text-align:center;">${ratingtwo} ${review_list.product_score}</td>
					</c:if>
					<c:if test="${review_list.product_score == 1.0}">
					<td style="text-align:center;">${ratingone} ${review_list.product_score}</td>
					</c:if>
					<td style="text-align:center;">${review_list.coment_date}</td>
					<td style="text-align:center;"> </td>
				</c:if>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	
		<!-- 리뷰 입력 -->
		<form:form action="review_add" Method="POST">
			<input type="hidden" name="product_num" value="${product_num}"/>
			<h4>별점을 선택해주세요</h4>
			<div class="star-rating">
				<input type="radio" id="5-stars" name="product_score" value="5.0" />
				<label for="5-stars" class="star">&#9733;</label>
				<input type="radio" id="4-stars" name="product_score" value="4.0" />
				<label for="4-stars" class="star">&#9733;</label>
				<input type="radio" id="3-stars" name="product_score" value="3.0" />
				<label for="3-stars" class="star">&#9733;</label>
				<input type="radio" id="2-stars" name="product_score" value="2.0" />
				<label for="2-stars" class="star">&#9733;</label>
				<input type="radio" id="1-star" name="product_score" value="1.0" required/>
				<label for="1-star" class="star">&#9733;</label>
			</div>
			<input type="text" class="form-control" name="coment" maxlength='100' placeholder="평점은 최대 100자까지 등록 가능합니다." required/> 
			<button type="submit"class="badge badge-pill badge-secondary" onclick="if (!(confirm('등록하시겠습니까'))) return false">리뷰등록</button>		
		</form:form>
	</div>

</body>
</html>