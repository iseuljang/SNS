<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<!--웹페이지 본문-->
<section>
	<div class="writeDiv">
		<div class="leftDiv">
			<label>
				<span class="imgSpan"><img src="./490_2342_3934.jpg" alt="고양이" 
					style="width: 100%;height: 100%; border-radius: 40px;"></span>
				<input type="file" style="display: none;">
			</label>
				</div>
		<div class="rightDiv">
			<input type="text" class="titleInput" placeholder="글제목">
			<br><br>
			<input type="text" class="contentInput" placeholder="글내용">
			<br><br>
			<div class="btnDiv">
				<form name="modify" method="post"  action="">
					<input class="btnR" type="submit" value="수정">
					&nbsp;&nbsp;
					<input class="btnC" type="reset" value="취소">
				</form>
			</div>
		</div>
	</div>
</section>
<%@ include file="../include/aside.jsp" %>