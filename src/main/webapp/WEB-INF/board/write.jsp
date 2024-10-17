<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<!--웹페이지 본문-->
<section>
	<div class="writeDiv">
		<div class="leftDiv">
			<label>
				<span class="imgSpan">사진넣기</span>
				<input type="file" style="display: none;">
			</label>
				</div>
		<div class="rightDiv">
			<input type="text" class="titleInput" placeholder="글제목">
			<br><br>
			<input type="text" class="contentInput" placeholder="글내용">
			<br><br>
			<div class="btnDiv">
				<form name="write" method="post" action="">
					<input class="btnR" type="submit" value="등록">
					&nbsp;&nbsp;
					<input class="btnC" type="reset" value="취소">
				</form>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/aside.jsp" %>