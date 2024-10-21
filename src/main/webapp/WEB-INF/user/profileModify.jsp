<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<%
UserVO userModify = null;
String userUid = "";
String userUnick = "";
String userUemail = "";
if(session.getAttribute("userModify") != null) {
	userModify = (UserVO)request.getAttribute("userModify");
	userUid = userModify.getUid();
	userUnick = userModify.getUnick();
	userUemail = userModify.getUemail();
}else {
    System.out.print("userModify : 로그인되지 않음");
}

System.out.println("userUid: "+userUid);
System.out.println("userUnick: "+userUnick);
System.out.println("userUemail: "+userUemail);
%>
<script>
let fileName = "";
window.onload = function(){
	$("#unick").focus();
	
	$(".deleteFile").css("visibility", "visible");
	
	fileName = '<%= userPname %>';
	if(fileName == null || fileName == ""){
		$(".deleteFile").css("visibility","hidden");
		$("#profilePreview").css("visibility","hidden");
	}
	
	$("#file").on('change', function(){
	    var fileName = $("#file").val();
	  
		// 새 파일이 선택된 경우 삭제 체크박스 보이기
  		$(".deleteFile").css("visibility", "visible"); // 체크박스를 보이게 함
 	 	$("#profilePreview").css("visibility", "visible");
  		$("input[name='deleteFile']").prop('checked', false); // 체크 해제
	});
	
	
	$("input[name='deleteFile']").click(function(){
		if($(this).is(':checked')) {
			$("#file").val("");
			$(".deleteFile").css("visibility","hidden");
			$("#profilePreview").css("visibility","hidden");
	        document.getElementById('profilePreview').src = "";
	    }
	});
	
	
	$('#resetBtn').click(function(){
		$('#profilePreview').attr('src', '<%= request.getContextPath() %>/upload/<%= userPname %>');
	});
}

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			// 파일이 선택된 경우 이미지 미리 보기 활성화
			document.getElementById('profilePreview').src = e.target.result;
			document.getElementById('profilePreview').style.display = 'block';
			document.getElementById('defaultProfile').style.display = 'none';  // 기본 프로필 div 숨기기
		};
		reader.readAsDataURL(input.files[0]);
	} else {
		// 파일이 없을 경우 기본 프로필 div를 표시하고 이미지 숨기기
		document.getElementById('profilePreview').src = "";
		document.getElementById('profilePreview').style.display = 'none';
		document.getElementById('defaultProfile').style.display = 'flex';
	}
}
</script>

<!-- 웹페이지 본문 -->
<section>
	<form action="profileModify.do" method="post" enctype="multipart/form-data">
	<div class="article_inner">
		<div class="inner">
			<div class="profileLeft">
				<!-- 첨부파일 삭제 여부 체크박스 추가 -->
	            <div class="deleteFile">
	         		<input type="checkbox" name="deleteFile" value="Y" id="deleteFile">
	         		<label for="deleteFile"><i class="fas fa-solid fa-circle-xmark"></i></label>
	            </div>
	            <label for="file">
	                <div class="profileImg">
	                <img id="profilePreview" src="" alt="프로필 이미지 미리보기" 
	                     style="width: 450px; height: 450px; border-radius: 50%; display: none;"> 
	                <%
	                if (userPname != null && !userPname.equals("")) {
	                %>
	                    <!-- 기존 프로필 이미지가 있을 때는 이미지 표시 -->
	                    <script>
	                        document.getElementById('profilePreview').src = "<%= request.getContextPath()%>/upload/<%= userPname %>";
	                        document.getElementById('profilePreview').style.display = 'block';
	                    </script>
	                <%
	                } else {
	                    String firstNick = loginUser.getUnick().substring(0, 1);
	                %>
	                    <!-- 기본 프로필 닉네임 첫 글자 표시 -->
	                    <div class="icon profileicon" id="defaultProfile"
	                        style="background-color:#EEEEEE; border-radius: 50%; cursor: pointer;
	                        display: flex; justify-content: center; align-items: center;
	                        font-size: 100px; font-weight: bold; width: 100%; height: 100%;">
	                        <%= firstNick %>
	                    </div>
	                <%
	                }
	                %>
	                </div>
	                <!-- 파일 선택 input -->
	                <input type="file" id="file" name="fname" style="display: none;" onchange="readURL(this);">
	            </label>
	        </div>
			<div class="profileRight">
				<div class="profileListDiv">
					<table>
						<tr>
							<td>
								<div class="user-container">
									<i class="fas fa-user" id="user_itag1"></i>
									<input class="profileList" type="text" name="uid" placeholder="<%= userUid %>" readonly>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="user-container">
									<i class="fas fa-envelope" id="user_itag6"></i>
									<input class="profileList" type="text" name="uemail" placeholder="<%= userUemail %>" readonly>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="user-container">
									<i class="fas fa-user" id="user_itag4"></i>
									<input class="profileList" type="text" name="unick" placeholder="<%= userUnick %>">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="user-container">
									<i class="fas fa-lock" id="user_itag2"></i>
									<input class="profileList" type="text" name="upw" placeholder="비밀번호">
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="profileListBtn">
									<input class="usersBtn" type="button" value="수정">
									&nbsp;&nbsp;
									<input class="usersBtn" type="reset" id="resetBtn" value="취소">
								</div>
							</td>
						</tr>				
						</table>
					</div>
				</div>
			</div>
		</div>
	</form>
</section>
<%@ include file="../include/aside.jsp" %>