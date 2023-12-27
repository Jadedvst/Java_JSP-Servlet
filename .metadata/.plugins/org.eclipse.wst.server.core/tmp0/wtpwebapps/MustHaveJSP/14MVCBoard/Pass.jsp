<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>

<script type="text/javascript">
	function validateForm(form){
		if(form.pass.value==""){
			alert("password please");
			form.pass.focus();
			return false;
		}
	}
</script>
</head>
<body>
<h2>파일 첨부형 게시판 - 비번 검증(Pass)</h2>
<!-- 주소창 보기 ㅜㅜㅜㅜㅜㅜ -->
<form action="../mvcboard/pass.do" method="post" name="writeFrm"
			onsubmit="return validateForm(this);"> <!-- false일시 출력 안됨 -->
	<input type="hidden" id="idx" name="idx" value="${ param.idx }"/>
	<input type="hidden" id="mode" name="mode" value="${ param.mode }"/>
	<table border="1" width="90%">
		<tr>
			<td>비번</td>
			<td><input type="password" id="pass" name="pass" style="width:100px;"/></td>
		</tr>
	
		<!-- Footer Menu (Button) -->
		<tr>
			<td colspan="2" align="center">
				<button type="submit">검증하기</button>
				<button type="button">초기화</button>
				<button type="button"
						onclick="location.href='../mvcboard/list.do';">
						목록 보기
				</button>
			</td>
		</tr>
	</table>
</form>
</body>
</html>