<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<script>
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
	<div class="profileModify">
		<div class="profileLeft">
			<!-- 첨부파일 삭제 여부 체크박스 추가 -->
            <div class="deleteFile">
         		<input type="checkbox" name="deleteFile" value="Y" id="deleteFile">
         		<label for="deleteFile"><i class="fas fa-solid fa-circle-xmark"></i></label>
            </div>
            <label for="file">
                <div class="profileImg">
                <img id="profilePreview" src="" alt="프로필 이미지 미리보기" 
                     style="width: 350px; height: 350px; border-radius: 200px; display: none;"> 
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
				<ul class="profileListUl">
					<li>
						<img class="imgIconId" src="./id.png" alt="id" >
						<input class="profileList" type="text" placeholder="아이디">
					</li>
					<li>
						<img class="imgIconMail" src="./email.png" alt="이메일">
						<input class="profileList" type="text" placeholder="이메일">
					</li>
					<li>
						<img class="imgIconId" src="./id.png" alt="닉네임">
						<input class="profileList" type="text" placeholder="닉네임">
					</li>
					<li>
						<img class="imgIconPw" src="./pw.png" alt="비밀번호">
						<input class="profileList" type="text" placeholder="비밀번호">
					</li><br>
					<li>
						<div class="profileListBtn">
							<input class="profileBtnM" type="button" value="수정">
							&nbsp;&nbsp;
							<input class="profileBtnC" type="button" value="취소">
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	</form>
</section>
<%@ include file="../include/aside.jsp" %>