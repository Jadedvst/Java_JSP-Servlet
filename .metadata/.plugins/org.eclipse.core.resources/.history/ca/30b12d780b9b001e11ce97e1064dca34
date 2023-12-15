<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("pAttr","Kim");
request.setAttribute("rAttr", "Kebak");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Tag - forward</title>
</head>
<body>
	<h2>Forwarding using Action Tag</h2>
	<jsp:forward page="/07ActionTag/ForwardSub.jsp"/>
	
<%
	RequestDispatcher rd = request.getRequestDispatcher("///");
	rd.forward(request,response);
%>
</body>
</html>