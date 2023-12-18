<%@page import="boardManagement.BoardDTO"%>
<%@page import="boardManagement.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 로그인 확인 -->
<%@ include file="./IsLoggedIn.jsp" %>
<%
//수정 내용 얻기
int num = 0;
String snum = request.getParameter("num");
if(snum!=null) num = Integer.parseInt(snum);
BoardDTO dto = new BoardDTO();
BoardDAO dao = new BoardDAO(application);
dto = dao.selectView(num);

String sessionId = session.getAttribute("UserId").toString();
int delResult = 0;

//DB에 반영
if(!sessionId.equals(dto.getId())){
	JSFunction.alertBack("본인만 삭제할 수 있습니다.",out);
	return;
}
else{
	dto.setNum(num);
	delResult = dao.deletePost(dto);
	dao.close();
	
	//Success
	 if(delResult==1) { 
		JSFunction.alertLocation("삭제되었습니다.","List.jsp",out);
	}
	//Failed
	else {
		JSFunction.alertBack("삭제에 실패하였습니다.",out); //경고후 이전페이지
	} 
}

%>
