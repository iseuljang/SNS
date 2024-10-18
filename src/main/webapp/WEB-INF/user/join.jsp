<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <script>
	// 이미지 input 객체
	const imageInput = document.getElementById('imageInput');
	// 이미지 표시할 객체
	const imagePreview = document.getElementById('imagePreview');
	// 이미지 파일명 표시할 객체
	const fileNameDisplay = document.getElementById('fileName');
	// 파일 선택 라벨로 사용할 객체
	const fileLabelInput = document.getElementById('fileLabel');

	window.onload = function () {
		// input type text에 클릭하면, input type file 클릭 이벤트 발생
		fileLabelInput.onclick = function () {
			imageInput.click();
		}
		imageInput.onchange = function (event) {
			// 선택된 파일(중 첫번째)
			const file = event.target.files[0];
			// 파일이 있으면
			if (file) {
				// 파일명 표시
				fileNameDisplay.innerText = '선택된 파일 이름: ' + file.name
	
				// JS의 파일 라이브러리 객체 생성
				const reader = new FileReader();
				// 파일 라이브러리 객체가 파일을 읽으면(이벤트)
				reader.onload = function (event) {
					// 이미지 미리보기 표시
					imagePreview.src = event.target.result;
					imagePreview.style.display = 'block';
	
					// 라벨로 사용된 인풋에 파일 이름 표시
					fileLabelInput.value = file.name;
				};
	
				// 파일을 Data URL로 읽기 :: 실제로 브라우저 화면에 파일을 표시하기 위해
				reader.readAsDataURL(file);
			} else {
				fileNameDisplay.innerText = '선택된 파일 이름: 없음';
				imagePreview.style.display = 'none';
				// 라벨로 사용된 인풋의 파일 이름 삭제
				fileLabelInput.value = "";
			}	
		}
	}
</script> -->
<!-- <div class="user_title">
	<a href="#">회원가입</a>
</div>
	<div class="join-container">
	<form action="joinOk.html" method="post">
		<div class="joinList">
			<img class="joinIcon1" src="./id.png" alt="id" >
			<input class="joinInput"
						 type="text"
						 name="uid"
						 id="uid"
						 style="background-image: url('./id.png'); background-repeat: no-repeat; background-size: 45px; background-position-y: center ;" 
						 placeholder="아이디"
						 required>
		</div>
		<div class="joinList">
			<input class="joinInput"
						 type="password"
						 name="upw"
						 id="upw"
						 style="background-image: url('./pw.png'); background-repeat: no-repeat; background-size: 30px; background-position-y: center ; background-position-x: 10px;" 
						 placeholder="비밀번호"
						 required>
		</div>
		<div class="joinList">
			<input class="joinInput"
						 type="password"
						 name="pwc" 
						 id="pwc" 
						 style="background-image: url('./pw.png'); background-repeat: no-repeat; background-size: 30px; background-position-y: center ; background-position-x: 10px;" 
						 placeholder="비밀번호 입력" 
						 required>
		</div>
		<div class="joinList">
			<input class="joinInput" 
						 type="text" 
						 name="unick" 
						 id="unick" 
						 style="background-image: url('./id.png'); background-repeat: no-repeat; background-size: 45px; background-position-y: center ;" 
						 placeholder="닉네임" 
						 required>
		</div>
		<div class="joinList">
				<label id="fileLabelInput"
							 style="height: 60px;
											display: flex;
											flex-direction: row;
											align-items: center;">
				<input class="joinInput" 
							 id="fileNameDisplay"
							 name="profile" 
							 style="background-image: url('./photo.png'); background-repeat: no-repeat; background-size: 30px; background-position-y: center ; background-position-x: 10px;" 
							 placeholder="프로필 이미지 선택" 
							 type="text"
							 readonly >
					<img src="./profile.png" id="imagePreview" class="joinInputProfile">
					<input type="file"
								 id="imageInput"
								 accept="image/*" 
								 style="display:none">
			</label>
		</div>
		<div class="joinList">
				<form action="joinOk-email.html">
				<input class="joinInput"
							 type="email" 
							 name="uemail" 
							 id="uemail" 
							 style="background-image: url('./email.png'); background-repeat: no-repeat; background-size: 30px; background-position-y: center ; background-position-x: 10px;" 
							 placeholder="이메일" 
							 required>
				&nbsp;
				<input class="joinBtnC" type="button" value="인증하기">
			</form>
		</div>
		<div class="joinList">
			<input class="joinBtnJ" type="submit" value="회원가입하기">
			<input class="userBtn" type="submit" value="회원가입하기">
		</div>
	</form>
</div> -->

<div>
    <div class="user_title">
		<a>회원가입</a>
    </div>
    <div class="user_inner">
        <form action="join.do" method="post" id="joinFn" enctype="multipart/form-data">
			<table>
          	    <tr>
                   <td>
                       <div class="user-container">
                           <i class="fas fa-user" id="user_itag1"></i>
                           <input type="text" name="uid" id="uid" placeholder="아이디">
                       </div>
                   </td>
               </tr>
               <tr>
                   <td>
                       <div class="user-container">
                           <i class="fas fa-lock" id="user_itag2"></i>
                           <input type="password" name="upw" id="upw" placeholder="비밀번호">
                       </div>
                   </td>
               </tr>
               <tr>
                   <td>
                       <div class="user-container">
                           <i class="fas fa-check" id="user_itag3"></i>
                      		<input type="password" name="upwcheck" id="upwcheck" placeholder="비밀번호확인">
                       </div>
                   </td>
               </tr>
               <tr>
                   <td>
                       <div class="user-container">
                           <i class="fas fa-user" id="user_itag4"></i>
                     	   <input type="text" name="unick" id="unick" placeholder="닉네임">
                       </div>
                   </td>
               </tr>
               <tr>
                   <td>
                       <div class="user-container">
                   			<i class="fas fa-camera" id="user_itag5"></i> 
                            <div class="profil">
                                <label for="file">
                               	 <input class="upload-name" value="프로필이미지" placeholder="프로필이미지" readonly>
                                 <input type="file" id="file" name="fname" onchange="readURL(this);">
                                </label>
                            </div>
                            <img id="preview" class="circular-img"
                            style="max-width:150px; max-height:150px; border-radius:50%;" />
                            <!-- 첨부파일 삭제 여부 체크박스 추가 -->
                               <div class="deleteFile" style="margin-left:10px;">
                          	 <input type="checkbox" name="deleteFile" value="Y" id="deleteFile">
                          	 <label for="deleteFile"><i class="fas fa-solid fa-circle-xmark"></i></label>
                            </div>
                       </div>
                        <!-- <div class="user-container" id="fileLabelInput">
							<input class="joinInput" 
										 id="fileNameDisplay"
										 name="profile" 
										 style="background-image: url('./photo.png'); background-repeat: no-repeat; background-size: 30px; background-position-y: center ; background-position-x: 10px;" 
										 placeholder="프로필 이미지 선택" 
										 type="text"
										 readonly >
								<img src="./profile.png" id="imagePreview" class="joinInputProfile">
								<input type="file"
											 id="imageInput"
											 accept="image/*" 
											 style="display:none">
						</div> -->
                   </td>
               </tr>
               <tr>
                   <td>
                       <div class="user-container">
                           <i class="fas fa-envelope" id="user_itag6"></i>
                     	   <input type="email" name="uemail" id="uemail" placeholder="이메일">
                     	   <button type="button" class="usersBtn">인증하기</button>
                       </div>
                   </td>
               </tr>
                <tr>
                	<td style="text-align: center;">
                    	<span class="msg" style="color:green;"></span>
                	</td>
                </tr>
                <tr>
                    <td style="text-align: center;">
                        <button type="button" class="userBtn" id="joinBtn" onclick="DoJoin();">가입하기</button>
                        <button type="reset"  class="userBtn">취소</button>
                    </td>
                </tr>
            </table>	
		</form>
    </div>
</div>