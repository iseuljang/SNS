#SNS
-
&nbsp;이 프로젝트는 비동기 통신 기술인 AJAX를 활용한 실시간 모니터링 기능을 갖춘 소셜 웹 서비스를 개발하는 데 중점을 둔 프로젝트입니다.<br>
&nbsp;회원가입을 통해 사용자가 참여할 수 있는 플랫폼을 제공하며, 관리자에게 블랙리스트 관리 기능을 제공하여 사용자 관리의 효율성을 높이고자 합니다.<br>
&nbsp;프론트엔드와 백엔드 통합 기술을 기반으로 하여, 실시간으로 사용자 정보와 상태를 업데이트하고 관리할 수 있는 시스템을 구축하는 것을 목표로 합니다.<br> 
&nbsp;주요 기술로는 시스템 요구사항 분석, JSP 기반 데이터 처리, AJAX를 이용한 비동기 통신 등이 포함됩니다.

<br>

## 목차
  - [개발기간](#개발기간)
  - [팀 구성](#팀-구성)
  - [개발환경](#개발환경)
  - [담당한 기능](#담당한-기능)
  - [트러블 슈팅](#트러블-슈팅)
  - [UI/UX 개선 사례](#UI/UX-개선-사례)
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
    + 개발 : 알림, 메시지 보내기,받기, 마이페이지에서 좋아요 누른 게시글 조회

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
    - 
  - **이메일 인증**
    - 
  - **아이디 찾기/비밀번호 찾기**
    - 
  - **게시글 좋아요, 신고**
    - 
  - **내정보조회 및 수정**
    - 
  - **인덱스 무한스크롤**
    - 

<br>

⚙️UI/UX 개선 사례
-
  1️⃣ 이메일인증 실시간 피드백 제공
  - 이메일 인증 요청 시, 버튼을 "메일발송중" 상태로 변경하고, 발송 완료 시 기존 상태로 복귀하여 사용자에게 명확한 피드백을 제공하도록 개선하였습니다.
    이를 통해 사용자는 발송이 정상적으로 진행되고 있음을 쉽게 인지할 수 있게 되었습니다.

  2️⃣ 회원가입 완료 후 로그인 모달 바로 제공
  - 회원가입 후 페이지 이동 없이 로그인 모달을 제공하여 사용자 여정을 간소화하고, 불필요한 클릭을 줄임으로써 UX를 개선하였습니다

  3️⃣ 헤더 버튼에 툴팁 제공 (마우스 오버 시 버튼 설명 표시)
  - 헤더의 버튼에 툴팁을 추가하여 마우스를 올릴 때마다 버튼의 기능 설명을 제공함으로써 사용자가 쉽게 탐색할 수 있도록 개선하였습니다

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
    - TLS 활성화: mail.smtp.starttls.enable을 "true"로 추가하여 TLS를 활성화.
      SSL 프로토콜 버전 명시: mail.smtp.ssl.protocols에 "TLSv1.2"를 추가하여 Java가 이 특정 버전을 사용하도록 명시함으로써 서버와의 프로토콜 호환성을 보장
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

<br>

📝개선할 부분
-
  - 댓글작성자의 프로필 이미지가 없을 경우 닉네임의 첫글자가 나와야하지만 현재 그 부분이 적용되지 않았습니다
  - 게시글은 작성자, 내용, 제목에서 검색이 되어야하지만 제목으로만 검색되고 있습니다
  - 댓글 수정하고 취소를 누를경우 원래 댓글목록 상태로 돌아가야하지만 수정창이 유지되면서 내용만 reset 되고 있습니다
 
<br>
     
<div align="right">
  
[목차로](#목차)

</div>
