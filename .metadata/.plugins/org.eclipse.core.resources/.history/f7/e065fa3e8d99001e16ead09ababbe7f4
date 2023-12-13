<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JDBC</title>
</head>
<body>
	<h2>회원 추가 테스트 using (executeUpdate())</h2>
	<%
	JDBConnect jdbc = new JDBConnect();
	
	String id ="test2";
	String pwd = "1111";
	String name = "testMem2";// 길이 주의
	
	String sql = "INSERT INTO member(id,pass,name) VALUES (?,?,?)";
	PreparedStatement psmt=jdbc.getCon().prepareStatement(sql);
	psmt.setString(1,id);
	psmt.setString(2,pwd);
	psmt.setString(3,name);
	
	int inResult = psmt.executeUpdate();
	out.println(inResult+"행 입력완");
	try{
		psmt.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	jdbc.close();//jbdc instance의 con, psmt가 일치 하지 않아서 따로 닫아줘야함
	%>
</body>
</html>