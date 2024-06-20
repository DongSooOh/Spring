<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!--<%-->
<!--	request.setCharacterEncoding("utf-8");-->
<!--%>-->
<html>
<head>
<meta charset="utf-8">
<title>회원정보 출력</title>
<style>
	a {
		text-decoration: none;
	}
	td {
		text-align: center;
	}
</style>	
</head>
<body>
	<table border="1" align="center" width="80%">
		<tr align="center" bgcolor="lightgreen">
			<td><b>아이디</b></td>
			<td><b>비밀번호</b></td>
			<td><b>이 름</b></td>
			<td><b>이메일</b></td>
			<td><b>가입일</b></td>
			<td><b>삭 제</b></td>
			<td><b>수 정</b></td>
		</tr>
		<c:forEach var="member" items="${membersList}">
		<tr align="center">
			<td>${member.id}</td>
			<td>${member.pwd}</td>
			<td>${member.name}</td>
			<td>${member.email}</td>
			<td>${member.joinDate}</td>
			<td><a href="${contextPath}/member/removeMember.do?id=${member.id}">삭제하기</a></td>
			<td><a href="${contextPath}/member/modMember.do?id=${member.id}">수정하기</a></td>
		</tr>
		</c:forEach>	
	</table>
	<c:choose>
	    <c:when test="${loginStatus}">
			<h1 align="center">${member.id}님 환영합니다</h1>
	        <a href="${contextPath}/member/logout.do"><h1 style="text-align:center">로그아웃</h1></a>
	    </c:when>
	    <c:otherwise>
	        <a href="${contextPath}/member/loginForm.do"><h1 style="text-align:center">로그인</h1></a>
	    </c:otherwise>
	</c:choose>
	<a href="${contextPath}/member/memberForm.do"><h1 style="text-align:center">회원가입</h1></a>
</body>
</html>