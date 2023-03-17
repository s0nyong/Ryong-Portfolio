<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%
session.invalidate();//세션의 모든 속성 제거
response.sendRedirect("/yongProject/MusicControllerServlet?command=LIST");
%>
