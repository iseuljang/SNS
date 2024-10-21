<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
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
<section>
	<div class="mypage_inner">
		<div id="mypage_top">
			<!-- 프로필이미지 -->
			<%
	        if(userPname != null && !userPname.equals("")){
        	%>
        	<img id="previewProfil" class="circular-img" 
        	onclick="location.href='<%= request.getContextPath() %>/user/mypage.do'"
	            style="border:none; width:300px; height:300px;" 
	            src="<%= request.getContextPath()+"/upload/" + userPname %>" 
           		alt="첨부된 이미지" style="max-width: 100%; height: auto;" />
         	<%
	        }else{
	        	String firstNick = loginUser.getUnick().substring(0, 1);
        	%>
	        <div class="icon profileicon"
	        onclick="location.href='<%= request.getContextPath() %>/user/mypage.do'"
	        style="background-color:#EEEEEE; border-radius: 50%; cursor: pointer;
	        display: flex; justify-content: center; align-items: center;
	         font-size: 100px; font-weight: bold; width: 300px; height: 300px;">
		        <%= firstNick %>
        	</div>
        	<%
	        }
	        %>
	    	<div><%= loginUser.getUnick() %></div>
	    	<div>팔로워 수 10</div>
	    	<div>
	    		<!-- 내가 아닌 다른 회원페이지인 경우 -->
	    		<!-- <button class="ssBtn">메시지</button>
	    		<button class="ssBtn">팔로우</button> -->
	    		<!-- 로그인한 회원의 마이페이지인 경우 -->
	    		<button class="ssBtn" onclick="location.href='profileModify.do'">프로필 수정</button>
			</div>
			<div class="mypage_page">
				<a href="mypage.jsp" style="text-decoration: underline; text-underline-offset: 6px;">북마크</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="mypage_write.jsp" style="color:gray;">내가쓴글</a>
            </div>
		</div>
		<!-- 게시글출력되는곳 -->
		<div id="indexDiv" class="scrollable">
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
            <div class="listDiv">
                <!-- 이미지 -->
            </div>
            <div class="listDiv">
                <!-- 이미지 -->
            </div>
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
<%@ include file="../include/aside.jsp" %>