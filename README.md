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







      
