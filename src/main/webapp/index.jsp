<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<%@ include file="/WEB-INF/include/nav.jsp" %>
<script>
	window.onload = function(){
		/* view 페이지 띄우는 모달 */
			// 모달 띄우기 버튼
		$(".listDiv").click(function() {
		    $("#modal").fadeIn(); // 모달 창 보이게 하기
		});
		
		$(window).click(function(event) {
		    if ($(event.target).is("#modal")) {
		        $("#modal").fadeOut(); // 모달 창 숨기기
		    }
		});
	}
   	
</script>
<!--웹페이지 본문-->
<section class="scrollable">
	<div id="indexDiv">
           <!-- 1번째 줄 -->
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <!-- 2번째 줄 -->
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <!-- 3번째 줄 -->
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <!-- 4번째 줄 -->
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
           <div class="listDiv">
               <!-- 이미지 -->
           </div>
       </div> 
</section>
<!-- view 페이지 모달창 -->
<div id="modal" style="display:none;">
    <div class="modal-content">
        <div id="modalBody">
            <!-- 게시글 내용이 여기에 표시됨 -->
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/include/aside.jsp" %>