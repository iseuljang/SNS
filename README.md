#SNS
-




<!-- -->
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

      
