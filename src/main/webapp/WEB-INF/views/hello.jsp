<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contetextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
	<script src="${contextPath}/js/scriptTest.js" type="text/javascript"></script>
	<meta charset="UTF-8">
	<title>Hello.jsp 페이지</title>
</head>
<body>
	<h2>Hello World</h2>
	<hr>
	현재 날짜와 시간은
	<%=java.time.LocalDateTime.now()%>입니다.
	<hr>
	메시지: ${msg}
	안녕하세요.<br>
	<h2>${message}</h2>
	<img width=200 height=200 src="${contextPath}/image/cat.jpg" /><br/>
	<input type="button" name="test" value="테스트" onClick="test();">
</body>
</html>