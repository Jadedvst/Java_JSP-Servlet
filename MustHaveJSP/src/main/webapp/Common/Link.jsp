<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<table border="1" width="90%">
		<tr>
			<td align="center">
<%-- 		<% if (session.getAttribute("member")==null){ %> --%>
			<% if (session.getAttribute("UserId")==null){ %>
				<a href="../06Session/LoginForm.jsp">[Login]</a>
			<% } else { %>
				<a href="../06Session/Logout.jsp">[Logout]</a>
			<% }%>
			<!-- 공백 확보용 특수 문자 -->
				&nbsp;&nbsp;&nbsp;
				<a href="../08Board/List.jsp">게시판(페이징X)</a>	
				&nbsp;&nbsp;&nbsp;
				<a href="../09PagingBoard/List.jsp">게시판(페이징O)</a>	
			</td>
		</tr>
	</table>