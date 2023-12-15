<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Tag - forward</title>
</head>
<body>
	<h2>Forwarding Result</h2>
	<ul>
		<li>
			page: <%=pageContext.getAttribute("pAttr")%>
		</li>
		<li>
			request: <%=request.getAttribute("rAttr")%>
		</li>
	</ul>
</body>
</html>