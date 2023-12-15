<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Action Tag - UseBean</title>
</head>
<body>
	<h3>Get Form attributes by once using Action Tag</h3>
	<jsp:useBean id="person" class="common.Person"/>
	<!-- = Person person = new Person(); -->
	<!-- useBean의 id와 setProperty의 name은 같은 존재 -->
	<jsp:setProperty property="*" name="person"/>
	<ul>
		<li>name: <jsp:getProperty property="name" name="person"/></li>
		<li>age : <jsp:getProperty property="age" name="person"/></li>
	</ul>
</body>
</html>