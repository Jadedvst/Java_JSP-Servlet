<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - response</title>
</head>
<body>
	<h2>1. 로그인 폼</h2>
	<%
	String loginErr = request.getParameter("loginErr");
	if(loginErr!= null) out.print("<h4>Login Fail: "+loginErr+"</h4>");
	%>
	<form action = "./ResponseLogin.jsp" method ="post">
	아이디 : <input type="text" name="user_id"/><br/>
	패스워드 : <input type="text" name="user_pwd"/><br/>
	<input type="submit" value="Login" />
	</form>
	
	<h2>2. HTTP 응답 헤더 설정하기</h2>
	<form action="./ResponseHeader.jsp" method="get">
	Date Form : <input type="text" name="add_date" value="2023-11-11 09:00"/><br/>
	Number Form : <input type="text" name="add_int" value="8282"/><br/>
	Text Form : <input type="text" name="add_str" value="김땡떙"/><br/>
	</form>
</body>
</html>