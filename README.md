:speech_balloon: SNS 
-
&nbsp;이 프로젝트는 비동기 통신 기술인 AJAX를 활용한 실시간 모니터링 기능을 갖춘 소셜 웹 서비스를 개발하는 데 중점을 둔 프로젝트입니다.<br>
<!--
&nbsp;회원가입을 통해 사용자가 참여할 수 있는 플랫폼을 제공하며, 관리자에게 블랙리스트 관리 기능을 제공하여 사용자 관리의 효율성을 높이고자 합니다.<br>
&nbsp;프론트엔드와 백엔드 통합 기술을 기반으로 하여, 실시간으로 사용자 정보와 상태를 업데이트하고 관리할 수 있는 시스템을 구축하는 것을 목표로 합니다.<br> 
&nbsp;주요 기술로는 시스템 요구사항 분석, JSP 기반 데이터 처리, AJAX를 이용한 비동기 통신 등이 포함됩니다.

-->

<br>

🔗 완성된 웹 애플리케이션 보기
-
⚠️ 기존에는 AWS EC2와 RDS를 이용해 배포했으나, 무료 사용 기간 종료로 요금 부담이 예상되어 서비스를 중단했습니다. <br>
현재는 Render와 Clever Cloud를 이용해 배포했으며, 아래 캡처 화면을 통해 프로젝트의 주요 기능과 UI를 확인하실 수 있습니다.<br><br>

<a href="https://sns-yef2.onrender.com/" target="_blank">https://sns-yef2.onrender.com/</a> <br>

<!--
<a href="http://3.39.239.157:8080/SNS" target="_blank">SNS</a>
-->

관리자 아이디 : admin<br>
관리자 비밀번호 : 1234<br>

<br>

🔗 PPT
-
[AWS실습프로젝트(sns).pptx](https://github.com/user-attachments/files/18312751/AWS.sns.pptx)

<br>

## 목차
  - [개발기간](#개발기간)
  - [팀 구성](#팀-구성)
  - [개발환경](#개발환경)
  - [담당한 기능](#담당한-기능)
  - [UI/UX 개선사례](#UI-UX-개선사례)
  - [트러블 슈팅](#트러블-슈팅)
  - [개선할 부분](#개선할-부분)

<br>

🗓️개발기간 
-
  + 2024.10.14 ~ 2024.11.13

<br>

👥팀 구성
-
  + 팀장 장이슬
    + 기획 : 화면설계서 작성, 프로토타입 제작, HTML, CSS 제작, 시퀀스(로그인), 디자인, PPT
    + 개발 : 회원가입/로그인, 이메일인증, 아이디,비밀번호 찾기, 로그아웃, 좋아요, 신고, 내정보조회 및 수정, index 무한스크롤
  + 팀원 박인재
    + 기획 : 요구분석서 작성, HTML, CSS 제작, 시퀀스(회원가입), 유즈케이스
    + 개발 : 게시판 CRUD, 블랙리스트 유저 조회, 신고게시글 조회, 본인과 다른회원의 작성글 조회, 회원정지
  + 팀원 송지은
    + 기획 : 주제제안서 작성, HTML, CSS 제작, 시퀀스(글쓰기), ERD
    + 개발 : 댓글 CRUD, 검색, 팔로우하기
  + 팀원 이동윤
    + 기획 : 프로젝트설계서 작성, HTML, CSS 제작, 시퀀스(글쓰기)
    + 개발 : 알림, 마이페이지에서 좋아요 누른 게시글 조회
    + 미구현 : 메시지 보내기,받기

<br>

🛠개발환경
-
  + JDK 13.0.2, MySQL 8.0, TOMCAT 9.0
  + JAVA13, HTML5, CSS3, JSP4, JavaScript, jQuery, Ajax
  + Eclipse IDE 4.22.0, Visual Studio Code, ERMaster, StarUML, MySQL (Workbench 8.0),GitHub, Notion

<br>

🖥담당한 기능
-
  - **회원가입 및 로그인**
    - 모달창을 띄워 회원가입을 진행하고 회원가입 성공시 로그인 모달창으로 변경됩니다
    - https://github.com/iseuljang/SNS/blob/main/src/main/webapp/WEB-INF/include/header.jsp
    	- DoLogin();
     	- DoJoin();
     	- 회원가입 후 로그인 모달 : openLoginModal();
    - https://github.com/iseuljang/SNS/blob/main/src/main/java/sns/controller/UserController.java
    	- login, loginOk, logout, join, joinOk, idCheck, nickCheck
    ![image](https://github.com/user-attachments/assets/2b77bb69-bbca-4e5a-8ce2-97c95f931bea)
    ![image](https://github.com/user-attachments/assets/46b6fbe1-e83f-44b8-9b4f-4e5fca22c089)

  - **이메일 인증**
    - 이메일인증하기 버튼 클릭시 메일발송중으로 변경되며 발송완료시 다시 인증하기로 변경됩니다
    - 6자리의 랜덤한 코드가 발송되며 발송한 코드와 회원이 입력한 코드가 일치해야 가입가능합니다
    - 회원가입, 아이디찾기/비밀번호 찾기를 할때 모두 이메일인증을 거쳐야 가능합니다
    - https://github.com/iseuljang/SNS/blob/main/src/main/webapp/WEB-INF/include/header.jsp
    	- SendMail();
     	- DoEmail();
     - https://github.com/iseuljang/SNS/blob/main/src/main/java/sns/controller/UserController.java
     	- sendmail, getcode
    ![image](https://github.com/user-attachments/assets/22299720-ba6f-44a2-bb70-95bc854d6cd2)

  - **아이디 찾기/비밀번호 찾기**
    - 회원가입 시 사용했던 이메일로 인증번호를 받아 가입한 아이디를 조회
    ![findId](https://github.com/user-attachments/assets/c4c3a8e4-2115-4de9-9266-d7c8e74ab0bc)
    - 가입한 아이디가 있을경우 아이디를 보여주고, 없을 경우 가입한 내역이 없음을 안내
    ![findIdResult](https://github.com/user-attachments/assets/5f1b1588-ccda-4866-9f24-6ac9c0387170)
    - 비밀번호 찾기할 아이디와 그 아이디를 가입할 때 사용했던 이메일 인증을 통해 비밀번호 찾기 실행
    ![findPw](https://github.com/user-attachments/assets/06d54b6e-2ac9-4761-8c3e-f09fbd75cb30)
    - 이메일 인증 완료 후 비밀번호 재설정으로 넘어감
    ![pwChange](https://github.com/user-attachments/assets/fe512b98-2ddc-4d78-91e7-8033a3237f80)
    - https://github.com/iseuljang/SNS/blob/main/src/main/webapp/WEB-INF/include/header.jsp
    	- findPage(type);
     	- 비밀번호 변경 : DoChange();
    - https://github.com/iseuljang/SNS/blob/main/src/main/webapp/WEB-INF/user
    	- findId.jsp, findIdResult.jsp, findPw.jsp, pwChange.jsp
    - https://github.com/iseuljang/SNS/blob/main/src/main/java/sns/controller/UserController.java
    	- findId, findIdResult, findPw, findPwOk, pwChangeOk

  - **게시글 좋아요, 신고**
    - 게시글을 조회할 경우 하트 아이콘을 눌러 게시글 좋아요
    ![image](https://github.com/user-attachments/assets/c238ce65-7307-4b16-8196-f5b0d9d57ebc)
    - 햄버거 메뉴를 눌러 게시글 신고
    ![image](https://github.com/user-attachments/assets/b1284f7f-3c24-4c3c-bef3-fe276a7d4ab3)
    - https://github.com/iseuljang/SNS/blob/main/src/main/webapp/WEB-INF/include/header.jsp
    	- loadReco(bno);
     	- recoAdd(bno);
      	- loadComplain(bno);
      	- complainAdd(bno);
    - https://github.com/iseuljang/SNS/blob/main/src/main/java/sns/controller/BoardController.java
    	- loadReco, recoAdd,
    - https://github.com/iseuljang/SNS/blob/main/src/main/java/sns/controller/AdminController.java
    	- loadComplain, complainAdd

  - **내정보조회 및 수정**
    - 회원가입할 때 기입한 아이디와 이메일 주소, 닉네임, 프로필 사진을 확인할 수 있고 닉네임과 프로필 사진을 변경. 비밀번호 확인을 거쳐야만 변경 가능
    ![image](https://github.com/user-attachments/assets/0a3b0010-ce31-4bd4-b355-54b08579776f)
    - 프로필 이미지 없이 가입하거나 내정보수정에서 프로필 이미지를 삭제할 경우 닉네임의 첫글자가 프로필 이미지를 대신
    ![image](https://github.com/user-attachments/assets/b999bef2-670b-4029-9dd5-ba4c644453b1)
    - https://github.com/iseuljang/SNS/blob/main/src/main/webapp/WEB-INF/user/profileModify.jsp
    - https://github.com/iseuljang/SNS/blob/main/src/main/java/sns/controller/UserController.java
    	-  mypage, profileModify, profileModifyOk

  - **인덱스 무한스크롤**
    - 처음 메인페이지에서 24개의 게시글이 조회되고 스크롤이 맨밑으로 내려갈때 추가로 게시글 목록을 조회하여 추가함
    ![image](https://github.com/user-attachments/assets/b0a3ca1c-5254-46da-95a5-80e3982835a7)
    - 추가 조회시 스크롤 변경
    ![image](https://github.com/user-attachments/assets/a61b374e-0e4e-41ba-b9bf-efb5572ab9eb)
    - https://github.com/iseuljang/SNS/blob/main/src/main/java/sns/controller/BoardController.java
    	- loadMore
    - https://github.com/iseuljang/SNS/blob/main/src/main/webapp/index.jsp
    
    
<br>

<h2 id="ui-ux-개선사례">📈UI/UX 개선사례</h2>

1️⃣ 이메일인증 실시간 피드백 제공    
  - 이메일 인증 요청 시, 버튼을 "메일발송중" 상태로 변경하고, 발송 완료 시 기존 상태로 복귀하여 사용자에게 명확한 피드백을 제공하도록 개선하였습니다. 이를 통해 사용자는 발송이 정상적으로 진행되고 있음을 쉽게 인지할 수 있게 되었습니다.
 
<br>2️⃣ 회원가입 완료 후 로그인 모달 바로 제공
  - 회원가입 후 페이지 이동 없이 로그인 모달을 제공하여 사용자 여정을 간소화하고, 불필요한 클릭을 줄임으로써 UX를 개선하였습니다.
  
<br>3️⃣ 헤더 버튼에 툴팁 제공 (마우스 오버 시 버튼 설명 표시)
  - 헤더의 버튼에 툴팁을 추가하여 마우스를 올릴 때마다 버튼의 기능 설명을 제공함으로써 사용자가 쉽게 탐색할 수 있도록 개선하였습니다.
  ![image](https://github.com/user-attachments/assets/98f04200-61a6-414b-b3f9-ded023ab0778)

<br>4️⃣ 게시글 이미지에 마우스 오버 효과 추가
  - 메인 페이지에서 게시글 이미지를 마우스 오버 시 살짝 위로 올라가고 그림자가 확대되는 효과를 추가하여 시각적 피드백을 개선하였습니다. 
  ![image](https://github.com/user-attachments/assets/160d11a9-fe10-4731-a32a-3b9a723c6bf5)


  
<br>

💡트러블 슈팅
-
  1️⃣ 게시글 조회수 증가오류
  - 문제 배경
    - 게시글 조회페이지를 모달창으로 띄울때마다 조회수가 2배씩 증가
  - 해결 방법
    - 게시글을 모달창으로 출력하는 ajax를 기존 header.jsp가 아닌 index.jsp로 이동    
  - 코드 비교
    - 수정전 header.jsp, index.jsp
      ```
      <!-- header.jsp -->
      $(document).ready(function() {
          $(".listDiv").click(function() {
      	    $("#modal").fadeIn(); // 모달 창 보이게 하기
      	    
      	    let bno = $(this).attr('id');
      	    console.log(bno);
      	    
      	    $.ajax({
      	        url: "<%= request.getContextPath() %>/board/view.do",
      	        data : {bno:bno},
      	        type: "get",
      	        success: function(data) {
      	            $("#modalBody").html(data);
      				
      	            // 동적으로 로드된 스크립트 실행
      	            $('script').each(function() {
      	                if (this.src) {
      	                    $.getScript(this.src);
      	                } else {
      	                    eval($(this).text());
      	                }
      	            });
      	
      	            // 다크모드 초기화 다시 실행
      	            DarkMode();
      	            
      	            loadReco(bno);
      	        }
      	    });
      	});
      	
      	$(window).click(function(event) {
      	    if ($(event.target).is("#modal")) {
      	        $("#modal").fadeOut(); // 모달 창 숨기기
      	    }
      	});
      });
      ```
      ```
      <!-- index.jsp -->
      <section class="scrollable">
      <div id="indexDiv">
      <%	
      	while(rs.next()){
      	%>
      		<div class="listDiv" id="<%= rs.getString("bno") %>">
                  <img style="width: 250px; height: 250px; border-radius: 20px;" 
                  src="<%= request.getContextPath() %>/upload/<%= rs.getString("pname") %>">
              </div>
      	<%
      	}
      %>
      	</div> 
      </section>
      <%
      }catch(Exception e){
      	e.printStackTrace();
      }finally{
      	try {
      		DBConn.close(rs, psmt, conn);
      	}catch(Exception e) {
      		e.printStackTrace();
      	}
      }
      %>
      ```
     - 수정후 index.jsp
       ```
       <!-- index.jsp -->
        <script>
        window.onload = function(){
        	$(".listDiv").click(function() {
        	    $("#modal").fadeIn(); // 모달 창 보이게 하기
        	    
        	    let bno = $(this).attr('id');
        	    console.log(bno);
        	    
        	    $.ajax({
        	        url: "<%= request.getContextPath() %>/board/view.do",
        	        data : {bno:bno},
        	        type: "get",
        	        success: function(data) {
        	            $("#modalBody").html(data);
        				
        	            // 동적으로 로드된 스크립트 실행
        	            $('script').each(function() {
        	                if (this.src) {
        	                    $.getScript(this.src);
        	                } else {
        	                    eval($(this).text());
        	                }
        	            });
        	
        	            // 다크모드 초기화 다시 실행
        	            DarkMode();
        	            
        	            loadReco(bno);
        	        }
        	    });
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
        <%	
        	while(rs.next()){
        	%>
        		<div class="listDiv" id="<%= rs.getString("bno") %>">
                    <img style="width: 250px; height: 250px; border-radius: 20px;" 
                    src="<%= request.getContextPath() %>/upload/<%= rs.getString("pname") %>">
                </div>
        	<%
        	}
        %>
        	</div> 
        </section>
        <%
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
        	try {
        		DBConn.close(rs, psmt, conn);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        %>
       ```
 - 해당 경험을 통해 알게 된 점
   - 동적으로 로드된 스크립트를 실행하는 부분이 header에서 반복실행되면서 조회수가 비정상적으로 증가됨을 알게 되었습니다.


 2️⃣ 모달창 닫기 오류
  - 문제 배경
    - 모달창에서 header 부분 클릭시 모달창이 닫히지 않고, header의 버튼이 그대로 적용되는 오류발생
  - 해결 방법
    - 인덱스 페이지 스크롤시 header를 고정하기 위해 걸어놓았던 z-index:1000; 으로 발생한 문제였고 modal 창에 z-index:1001;을 주는것으로 문제 해결    
  - 코드 비교
    - 수정전 css
       ```
       <!-- sns.css -->
       header {
          position: fixed;
          top: 0;
          left: 0;
          width: 100%;
          height: 15%;
          z-index: 1000;     
      }
      
      #modal {
          display: none; /* 기본적으로 숨김 */
          position: fixed;
          z-index: 1;
          left: 0;
          top: 0;
          width: 100%;
          height: 100%;
          background-color: rgba(0, 0, 0, 0.4); 
          overflow: auto;
          padding-top: 60px;
      }
       ```
    - 수정후 css
       ```
       <!-- sns.css -->
       header {
          position: fixed;
          top: 0;
          left: 0;
          width: 100%;
          height: 15%;
          z-index: 1000;     
       }
      
       #modal {
          display: none; /* 기본적으로 숨김 */
          position: fixed;
          z-index: 1001;
          left: 0;
          top: 0;
          width: 100%;
          height: 100%;
          background-color: rgba(0, 0, 0, 0.4); 
          overflow: auto;
          padding-top: 60px;
       }
       ```
  - 해당 경험을 통해 알게 된 점
    - 페이지의 스크롤과 모달창 등 여러 UI 요소가 겹치는 경우, z-index 설정에 따라 예상치 못한 오류가 발생할 수 있음을 알게 되었습니다


3️⃣ 이메일 발송 오류
  - 문제 배경
    - 이메일 발송할 때 워크스페이스 위치에 따라 이메일 발송 성공,실패가 달라짐
  - 해결 방법
    - TLS 활성화: mail.smtp.starttls.enable을 "true"로 추가하여 TLS를 활성화<br>
      SSL 프로토콜 버전 명시: mail.smtp.ssl.protocols에 "TLSv1.2"를 추가하여 Java가 이 특정 버전을 사용하도록 명시함으로써 서버와의 프로토콜 호환성을 보장<br>
      포트 값의 문자열 변환: 포트 번호를 문자열로 변경하여 코드의 일관성을 확보하고 포트 관련 오류 발생 가능성을 줄임
  - 코드 비교
    - 수정전 Sendmail.java
      ```
      Properties clsProp = new Properties();
            clsProp.put("mail.smtp.host", "smtp.naver.com");
            clsProp.put("mail.smtp.port", 465);
            clsProp.put("mail.smtp.auth", "true");
            clsProp.put("mail.smtp.ssl.enable", "true"); 
            clsProp.put("mail.smtp.ssl.trust", "smtp.naver.com");
      ```
    - 수정후 Sendmail.java
      ```
      Properties clsProp = new Properties();
            clsProp.put("mail.smtp.host", "smtp.naver.com");
            clsProp.put("mail.smtp.port", "465");
            clsProp.put("mail.smtp.auth", "true");
            clsProp.put("mail.smtp.ssl.enable", "true"); 
            // TLS 활성화
            clsProp.put("mail.smtp.starttls.enable", "true");
            // SSL 프로토콜 명시
            clsProp.put("mail.smtp.ssl.protocols", "TLSv1.2");
            
            clsProp.put("mail.smtp.ssl.trust", "smtp.naver.com");
      ```
 - 해당 경험을 통해 알게 된 점
    - 환경마다 네트워크 및 보안 요구 사항이 다를 수 있다는 점을 파악할 수 있었습니다. 특히 외부 SMTP 서버를 사용할 때 보안 설정을 명확히 해야 한다는 사실을 깨달았습니다.
      TLS 및 SSL 프로토콜의 명시적 설정이 필요할 수 있고 이로 인해 서버와의 호환성 문제를 줄이고, 이메일 전송 성공률을 높일 수 있다는 것을 알았습니다

4️⃣ 회원가입 후 로그인모달 열릴때 알림 오류
  - 문제 배경
    - 회원가입 모달창에서 회원가입 성공할 경우 로그인모달창이 열리는데 성공임에도 실패메시지가 출력되는 오류 발생
  - 해결 방법
    - class msg를 똑같이 사용하며 이전 회원가입 시 발생했던 에러 메시지가 그대로 남아있는 문제였고, 회원가입 완료 후 로그인 모달 창을 열 때 .msg 요소의 내용을 초기화하여 문제 해결
  - 코드 비교
    - 수정전 함수
      ```
      function openLoginModal() {
          $("#user_modal").fadeIn();
                           
          $.ajax({
              url: "<%= request.getContextPath() %>/user/login.do",
              type: "get",
              success: function(data) {
                  $("#user_modalBody").html(data);
                             
                  resetEvents();
                  DarkMode();
              }
          });
      }
      function DoJoin() {
        ...
        var form = document.getElementById("joinFn");
      
        $.ajax({
            url: "<%= request.getContextPath() %>/user/join.do",  // 요청 URL
            type: "post",  // 요청 방식
            data: new FormData(form),  // 폼 데이터 전송
            processData: false,  // 자동 데이터 처리 방지
            contentType: false,  // 요청 시 Content-Type 설정 방지
            success: function(result) {
    		      	result = result.trim();
    	            	
                switch(result) {
                     case "success":
                    	 openLoginModal();
                         break;
                     case "error":
                    	 $(".msg").html("회원가입에 실패했습니다. 다시 시도해주세요.");
                         break;
                     default :
                    	 alert("실패했습니다. 나중에 다시 시도해 주세요.");
                         break;
                 }
            }
        });
      }
      ```
    - 수정후 함수
      ```
      function openLoginModal() {
          $("#user_modal").fadeIn();
          $(".msg").html(""); 
          
          $.ajax({
              url: "<%= request.getContextPath() %>/user/login.do",
              type: "get",
              success: function(data) {
                  $("#user_modalBody").html(data);
                             
                  resetEvents();
                  DarkMode();
              }
          });
      }
      function DoJoin() {
        ...
        var form = document.getElementById("joinFn");
      
        $.ajax({
            url: "<%= request.getContextPath() %>/user/join.do",  // 요청 URL
            type: "post",  // 요청 방식
            data: new FormData(form),  // 폼 데이터 전송
            processData: false,  // 자동 데이터 처리 방지
            contentType: false,  // 요청 시 Content-Type 설정 방지
            success: function(result) {
    		      	result = result.trim();
    	            	
                switch(result) {
                     case "success":
                    	 openLoginModal();
                         break;
                     case "error":
                    	 $(".msg").html("회원가입에 실패했습니다. 다시 시도해주세요.");
                         break;
                     default :
                    	 alert("실패했습니다. 나중에 다시 시도해 주세요.");
                         break;
                 }
            }
        });
      }
      ```
 - 해당 경험을 통해 알게 된 점
    - 모달 창 전환 시 이전 상태를 초기화하는 것이 중요하다는 점을 확인하였습니다

5️⃣ 마이페이지 오류
  - 문제 배경
    - 여러 사람이 마이페이지의 기능을 구현하면서 기존 user 테이블에서 데이터를 조회하는게 아니라 board에서 조회하는 쿼리로 변경되어 마이페이지의 회원이 작성한 글이 없을 때, <br>
     board 변수가 null 상태가 되면서 해당 데이터를 처리하는 로직에서 예외가 발생하였고, 이로 인해 전체 페이지가 정상적으로 표시되지 않았습니다.
  - 해결 방법
    - type 파라미터 추가를 통한 조건별 데이터 처리
    - SQL 코드 분리
    - 데이터가 null인 경우의 처리 로직 추가
  - 코드 비교
    - 수정전 함수
      -  mypage controller 함수 일부
      ```
      String sql  =" SELECT * "
        +"    , (select count(*) from follow f where f.uno = ? and tuno = ? ) as isfollow "
        +" FROM board b "
        +" INNER JOIN user u "
        +" ON b.uno = u.uno"
        +" INNER JOIN attach a"
        +" ON b.bno = a.bno "
        +" WHERE u.uno =? and state = 'E' ";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, loginUser.getUno());
			psmt.setString(2, uno);
			psmt.setString(3, uno);
			ArrayList<BoardVO> board = new ArrayList<>();
			rs = psmt.executeQuery();
			String isfollow="";
			// 수정할 부분
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setUno(rs.getString("uno"));
				user.setUid(rs.getString("uid"));
				user.setUnick(rs.getString("unick"));
				user.setUemail(rs.getString("uemail"));
				user.setUstate(rs.getString("ustate"));
				user.setUauthor(rs.getString("uauthor"));
				user.setUrdate(rs.getString("urdate"));
				user.setPname(rs.getString("pname"));
				user.setFname(rs.getString("fname"));
				isfollow = rs.getString("isfollow");
				System.out.println("isfollow : " + isfollow);
				request.setAttribute("user", user);
				request.setAttribute("isfollow", isfollow);

				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setUno(rs.getInt("uno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRdate(rs.getString("rdate"));
				vo.setState(rs.getString("state"));
				vo.setUnick(rs.getString("unick"));
				vo.setPname(rs.getString("a.pname"));
				vo.setFname(rs.getString("a.fname"));
				board.add(vo);
			}
      // 세션에 있는 uno와 일치하는 팔로우 테이블의 uno를 카운트를 조회한다
      String sqlFollow = " select count(*) as cnt from follow where tuno = ? ";

      psmtFollow = conn.prepareStatement(sqlFollow);
      psmtFollow.setInt(1, Integer.parseInt(uno));

      rsFollow = psmtFollow.executeQuery();

      int cnt = 0;
      if (rsFollow.next()) {
        cnt = rsFollow.getInt("cnt");
      }
      request.setAttribute("fcnt", cnt);
      request.setAttribute("board", board);
      
      request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request, response);

        ```
      -  myPageWrite
        ```
      public void myPageWrite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

    		request.setCharacterEncoding("UTF-8");
    		String Struno = request.getParameter("uno");
    		if (Struno == null) {
    			return;
    		}
    		int uno = Integer.parseInt(Struno);
    		System.out.println("uno ====================================" + uno);
    
    		Connection conn = null;
    		PreparedStatement psmt = null;
    		ResultSet rs = null;
    		String sql = "";
    
    		PreparedStatement psmtFollow = null;
    		ResultSet rsFollow = null;
    
    
    		try {
    			conn = DBConn.conn();
    			sql = " SELECT * FROM board b " + " INNER JOIN user u " + " ON b.uno = u.uno" + " INNER JOIN attach a "
    					+ " ON b.bno = a.bno" + " where u.uno =? ";
    			psmt = conn.prepareStatement(sql);
    			psmt.setInt(1, uno);
    			rs = psmt.executeQuery();
    			ArrayList<BoardVO> board = new ArrayList<>();
    			while (rs.next()) {
    				UserVO user = new UserVO();
    				user.setUno(rs.getString("uno"));
    				user.setUid(rs.getString("uid"));
    				user.setUnick(rs.getString("unick"));
    				user.setUemail(rs.getString("uemail"));
    				user.setUstate(rs.getString("ustate"));
    				user.setUauthor(rs.getString("uauthor"));
    				user.setUrdate(rs.getString("urdate"));
    				user.setPname(rs.getString("pname"));
    				user.setFname(rs.getString("fname"));
    				request.setAttribute("user", user);
    
    				BoardVO vo = new BoardVO();
    				vo.setBno(rs.getInt("bno"));
    				vo.setUno(rs.getInt("uno"));
    				vo.setTitle(rs.getString("title"));
    				vo.setContent(rs.getString("content"));
    				vo.setRdate(rs.getString("rdate"));
    				vo.setState(rs.getString("state"));
    				vo.setUnick(rs.getString("unick"));
    				vo.setPname(rs.getString("a.pname"));
    				vo.setFname(rs.getString("a.fname"));
    				board.add(vo);
    			}
    
    			// 세션에 있는 uno와 일치하는 팔로우 테이블의 uno를 카운트를 조회한다
    			String sqlFollow = " select count(*) as cnt from follow where tuno = ? ";
    
    			psmtFollow = conn.prepareStatement(sqlFollow);
    			psmtFollow.setInt(1,uno);
    
    			rsFollow = psmtFollow.executeQuery();
    
    			int cnt = 0;
    			if (rsFollow.next()) {
    				cnt = rsFollow.getInt("cnt");
    			}
    			request.setAttribute("fcnt", cnt);
    			request.setAttribute("board", board);
    			request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				DBConn.close(rs, psmt, conn);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}
        ```
      -  myPageBookmark
        ```
      public void myPageBookmark(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {
  
  		request.setCharacterEncoding("UTF-8");
  		String Struno = request.getParameter("uno");
  		if (Struno == null) {
  			return;
  		}
  		int uno = Integer.parseInt(Struno);
  		System.out.println("uno ====================================" + uno);
  
  		Connection conn = null;
  		PreparedStatement psmt = null;
  		ResultSet rs = null;
  		String sql = "";
  
  		PreparedStatement psmtFollow = null;
  		ResultSet rsFollow = null;
  
  
  		try {
  			conn = DBConn.conn();
  			sql = " SELECT *"
  			sql = " SELECT *,u.uno as uuno"
  				+ "  FROM board b"
  				+ " INNER JOIN love l"
  				+ "    ON b.bno = l.bno"
  				+ " INNER JOIN user u"
  				+ "	   ON l.uno = u.uno"
  				+ " INNER JOIN attach a"
  				+ "	   ON b.bno = a.bno"
  				+ " where u.uno = ? ";
  			psmt = conn.prepareStatement(sql);
  			psmt.setInt(1, uno);
  			rs = psmt.executeQuery();
  			ArrayList<BoardVO> board = new ArrayList<>();
  			while (rs.next()) {
  				UserVO user = new UserVO();
  				user.setUno(rs.getString("uno"));
  				user.setUno(rs.getString("uuno"));
  				user.setUid(rs.getString("uid"));
  				user.setUnick(rs.getString("unick"));
  				user.setUemail(rs.getString("uemail"));
  				user.setUstate(rs.getString("ustate"));
  				user.setUauthor(rs.getString("uauthor"));
  				user.setUrdate(rs.getString("urdate"));
  				user.setPname(rs.getString("pname"));
  				user.setFname(rs.getString("fname"));
  				request.setAttribute("user", user);
  
  				BoardVO vo = new BoardVO();
  				vo.setBno(rs.getInt("bno"));
  				vo.setUno(rs.getInt("uno"));
  				vo.setTitle(rs.getString("title"));
  				vo.setContent(rs.getString("content"));
  				vo.setRdate(rs.getString("rdate"));
  				vo.setState(rs.getString("state"));
  				vo.setUnick(rs.getString("unick"));
  				vo.setPname(rs.getString("a.pname"));
  				vo.setFname(rs.getString("a.fname"));
  				board.add(vo);
  			}
  
  			// 세션에 있는 uno와 일치하는 팔로우 테이블의 uno를 카운트를 조회한다
  			String sqlFollow = " select count(*) as cnt from follow where tuno = ? ";
  
  			psmtFollow = conn.prepareStatement(sqlFollow);
  			psmtFollow.setInt(1,uno);
  
  			rsFollow = psmtFollow.executeQuery();
  
  			int cnt = 0;
  			if (rsFollow.next()) {
  				cnt = rsFollow.getInt("cnt");
  			}
  			request.setAttribute("fcnt", cnt);
  			request.setAttribute("board", board);
  			request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request, response);
  
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
    			try {
    				DBConn.close(rs, psmt, conn);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
  	  }
      ```
      - mypage.jsp
       ```html
		<a href="mypage_bookmark.do?uno=<%= loginUser.getUno() %>&type=bookmark"
		style="<%= "bookmark".equals(type) ? "text-decoration: underline; text-underline-offset: 6px;" : "color:gray;" %>">북마크</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="mypage_write.do?uno=<%= loginUser.getUno() %>&type=written" 
		style="<%= "written".equals(type) ? "text-decoration: underline; text-underline-offset: 6px;" : "color:gray;" %>">내가쓴글</a>
      ```
    - 수정후
      - mypage
      ```
      public void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		HttpSession session = request.getSession();
    		UserVO loginUser = null;
    		if(session.getAttribute("loginUser") != null && !session.getAttribute("loginUser").equals("")) {
    			loginUser = (UserVO)session.getAttribute("loginUser");
    		}
    		String uno = request.getParameter("uno");
    		String type = "bookmark";
    		if(request.getParameter("type") != null && !request.getParameter("type").equals("")) {
    			type = request.getParameter("type");
    		}
    		request.setCharacterEncoding("UTf-8");
    
    		Connection conn = null; // DB 연결
    		PreparedStatement psmt = null; // SQL 등록 및 실행. 보안이 더 좋음!
    		ResultSet rs = null; // 조회 결과를 담음
    
    		PreparedStatement psmtFollow = null;
    		ResultSet rsFollow = null;
    		// try 영역
    		try {
    			conn = DBConn.conn();
    			String sql = "";
    			if(loginUser != null) {
    				sql = "select *,(select count(*) from follow f where f.uno = ? and tuno = ? ) as isfollow from user where uno=?"; 
    				psmt = conn.prepareStatement(sql);
    				psmt.setString(1, loginUser.getUno()); 
    				psmt.setString(2, uno);
    				psmt.setString(3, uno);
    			}else {
    				sql = "select * from user where uno=?";
    				psmt = conn.prepareStatement(sql);
    				psmt.setString(1, uno); 
    			}
    			rs = psmt.executeQuery();
    			String isfollow="";
    			// 수정할 부분
    			if(rs.next()) {
    				UserVO user = new UserVO();
    				user.setUno(rs.getString("uno"));
    				user.setUid(rs.getString("uid"));
    				user.setUnick(rs.getString("unick"));
    				user.setUemail(rs.getString("uemail"));
    				user.setUstate(rs.getString("ustate"));
    				user.setUauthor(rs.getString("uauthor"));
    				user.setUrdate(rs.getString("urdate"));
    				user.setPname(rs.getString("pname"));
    				user.setFname(rs.getString("fname"));
    				if(loginUser != null) {
    					isfollow = rs.getString("isfollow");
    					System.out.println("isfollow : " + isfollow);
    					request.setAttribute("isfollow", isfollow);
    				}
    				request.setAttribute("user", user);
    			}
    			if(loginUser != null) {
    				// 세션에 있는 uno와 일치하는 팔로우 테이블의 uno를 카운트를 조회한다
    				String sqlFollow = " select count(*) as cnt from follow where tuno = ? ";
    	
    				psmtFollow = conn.prepareStatement(sqlFollow);
    				psmtFollow.setInt(1, Integer.parseInt(uno));
    	
    				rsFollow = psmtFollow.executeQuery();
    	
    				int cnt = 0;
    				if (rsFollow.next()) {
    					cnt = rsFollow.getInt("cnt");
    				}
    				request.setAttribute("fcnt", cnt);
    			}
    			if(type.equals("bookmark")) {
    				myPageBookmark(request, response);
    			}else {
    				myPageWrite(request, response);
    			}
    			
    			request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				DBConn.close(rs, psmt, conn);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
      ```  
      - myPageBookmark
      ```
      public void myPageBookmark(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    		
    		request.setCharacterEncoding("UTF-8");
    		String Struno = request.getParameter("uno");
    		if (Struno == null) {
    			return;
    		}
    		int uno = Integer.parseInt(Struno);
    		System.out.println("uno ====================================" + uno);
    
    		Connection conn = null;
    		PreparedStatement psmt = null;
    		ResultSet rs = null;
    		String sql = "";
    
    		try {
    			conn = DBConn.conn();
    			sql = " SELECT *,u.uno as uuno"
    				+ "  FROM board b"
    				+ " INNER JOIN love l"
    				+ "    ON b.bno = l.bno"
    				+ " INNER JOIN user u"
    				+ "	   ON l.uno = u.uno"
    				+ " INNER JOIN attach a"
    				+ "	   ON b.bno = a.bno"
    				+ " where u.uno = ?  and b.state='E' ";
    			psmt = conn.prepareStatement(sql);
    			psmt.setInt(1, uno);
    			rs = psmt.executeQuery();
    			ArrayList<BoardVO> board = new ArrayList<>();
    			while (rs.next()) {
    				BoardVO vo = new BoardVO();
    				vo.setBno(rs.getInt("bno"));
    				vo.setUno(rs.getInt("uno"));
    				vo.setTitle(rs.getString("title"));
    				vo.setContent(rs.getString("content"));
    				vo.setRdate(rs.getString("rdate"));
    				vo.setState(rs.getString("state"));
    				vo.setUnick(rs.getString("unick"));
    				vo.setPname(rs.getString("a.pname"));
    				vo.setFname(rs.getString("a.fname"));
    				board.add(vo);
    			}
    			
    			request.setAttribute("board", board);
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				DBConn.close(rs, psmt, conn);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}
      ```
      - myPageWrite
      ```
      public void myPageWrite(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {
  		
  		request.setCharacterEncoding("UTF-8");
  		String Struno = request.getParameter("uno");
  		if (Struno == null) {
  			return;
  		}
  		int uno = Integer.parseInt(Struno);
  		System.out.println("uno ====================================" + uno);
  
  		Connection conn = null;
  		PreparedStatement psmt = null;
  		ResultSet rs = null;
  		String sql = "";
  
  		PreparedStatement psmtFollow = null;
  		ResultSet rsFollow = null;
  		
  		
  		try {
  			conn = DBConn.conn();
  			sql = " SELECT *,a.pname,a.fname "
  					+ "  FROM board b"
  					+ " INNER JOIN user u"
  					+ "	   ON b.uno = u.uno"
  					+ " INNER JOIN attach a"
  					+ "	   ON b.bno = a.bno"
  					+ " where u.uno = ? and b.state='E'";
  			psmt = conn.prepareStatement(sql);
  			psmt.setInt(1, uno);
  			rs = psmt.executeQuery();
  			ArrayList<BoardVO> board = new ArrayList<>();
  			while (rs.next()) {
  				BoardVO vo = new BoardVO();
  				vo.setBno(rs.getInt("bno"));
  				vo.setUno(rs.getInt("uno"));
  				vo.setTitle(rs.getString("title"));
  				vo.setContent(rs.getString("content"));
  				vo.setRdate(rs.getString("rdate"));
  				vo.setState(rs.getString("state"));
  				vo.setUnick(rs.getString("unick"));
  				vo.setPname(rs.getString("a.pname"));
  				vo.setFname(rs.getString("a.fname"));
  				board.add(vo);
  			}
  			request.setAttribute("board", board);
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
    			try {
    				DBConn.close(rs, psmt, conn);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    	}
      ```
      - mypage.jsp
       ```java
		String type = "bookmark";
		if (request.getParameter("type") != null && !request.getParameter("type").equals("")) {
		    type = request.getParameter("type");
		}	
       ```
       ```html
		<a href="mypage.do?uno=<%= loginUser.getUno() %>&type=bookmark"
		   style="<%= "bookmark".equals(type) ? "text-decoration: underline; text-underline-offset: 6px;" : "color:gray;" %>">
		   북마크
		</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<a href="mypage.do?uno=<%= loginUser.getUno() %>&type=written" 
		   style="<%= "written".equals(type) ? "text-decoration: underline; text-underline-offset: 6px;" : "color:gray;" %>">
		   내가쓴글
		</a>
      ```
 - 해당 경험을 통해 알게 된 점
    - 단일 컨트롤러에서 모든 데이터를 처리하려는 접근보다는, 상황에 맞는 파라미터(type 등)를 사용해 조건별로 필요한 데이터를 효율적으로 처리할 수 있다는 점을 알게 되었습니다
    - null 값이 반환될 가능성을 항상 염두에 두고 기본값 설정이나 null 체크와 같은 예외 상황을 다루는 코드를 작성하는 것이 얼마나 중요한지 깨닫게 되었습니다. 
<br>

📝개선할 부분
-
  - 게시글은 작성자, 내용, 제목에서 검색이 되어야하지만 제목으로만 검색되고 있습니다
  - 댓글 수정하고 취소를 누를경우 원래 댓글목록 상태로 돌아가야하지만 수정창이 유지되면서 내용만 reset 되고 있습니다
  - 마이페이지에서 닉네임 중복체크 확인이 누락되었습니다
  - 게시글을 신고할 때 신고 사유 없이 신고되기 때문에 어떤 이유로 신고된 것인지 관리자가 게시글을 조회해 확인해야 하는 번거로움이 있습니다
  - 일정관리의 미흡함으로 메시지 보내고 받는 기능을 구현하지 못했습니다
 
<br>
     
<div align="right">
  
[목차로](#목차)

</div>
