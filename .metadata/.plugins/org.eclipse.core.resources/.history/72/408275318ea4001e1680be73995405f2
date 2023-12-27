<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 첨부형 게시판</title>
</head>
<body>
<h2>파일 첨부형 게시판 - 상세 보기(View)</h2>

<table border="1" width="90%">
	<colgroup>
		<col width="15%"/><col width="35%"/>
		<col width="15%"/><col width="*"/>
	</colgroup>
	
	<!-- Board information -->
	<tr>
		<td>번호</td><td>${ dto.idx }</td>
		<td>작성자</td><td>${ dto.name }</td>
	</tr>
	<tr>
		<td>작성일</td><td>${ dto.postdate }</td>
		<td>조회수</td><td>${ dto.visitcount }</td>
	</tr>
	<tr>
		<td>제목</td>
		<td colspan="3">${ dto.title }</td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3" height="100">
			${ dto.content }
			<c:if test="${ not empty dto.ofile and isImage eq true }">
			<!-- 이미지 첨부 -->
				<br><img src="../Uploads/${ dto.sfile }" style="max-height:1000px; max-width:1000px"/>
			</c:if>
		</td>
	</tr>
	
	<!-- File Attachment -->
	<tr>
		<td>첨부 파일</td>
		<td>
			<c:if test="${ not empty dto.ofile }">
				${ dto.ofile }
				<a href = "../mvcboard/download.do?ofile=${ dto.ofile }&sfile=${ dto.sfile }&idx=${ dto.idx }">[download]</a>
			</c:if>
		</td>
		<td>다운로드수</td><td>${ dto.downcount }</td>
	</tr>
	
	<!-- Footer Menu (Button) -->
	<tr>
		<td colspan="4" align="center">
			<button type="button"
					onclick="location.href='../mvcboard/pass.do?mode=edit&idx=${ param.idx }';">
					수정하기
			</button>
			<button type="button"
					onclick="location.href='../mvcboard/pass.do?mode=delete&idx=${ param.idx }';"
					style="background-color:orange;">
					삭제하기
			</button>
			<button type="button"
					onclick="location.href='../mvcboard/list.do';">
					목록 보기
			</button>
		</td>
	</tr>
</table>
</body>
</html>