<%@ page import="utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String loginId = CookieManager.readCookie(request, "loginId");

String cookieCheck = "";
if(!loginId.equals("")){
	cookieCheck="checked";
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie - Save ID</title>
</head>
<body>
<form action = "IdSaveProcess.jsp" method ="post">
	아이디 : <input type="text" name="user_id" value="<%= loginId %>"/>
			<input type="checkbox" name="save_check" value="Y" <%=cookieCheck %>/>
			아이디 저장하기
	<br/>
	패스워드 : <input type="text" name="user_pwd"/>
	<br/>
	<input type="submit" value="Login" />
</form>
</body>
</html>