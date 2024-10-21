<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="sns.vo.*" %>
<%
String author= "";
String loginNo= "";
String userPname= "";
UserVO loginUser = null;
if(session.getAttribute("loginUser") != null) {
    loginUser = (UserVO)session.getAttribute("loginUser");
    if(loginUser.getPname() != null) {
        System.out.println("loginUser : " + loginUser.getPname());
        userPname = loginUser.getPname();
        loginNo = loginUser.getUno();
    }else {
        System.out.println("loginUser : 이름 없음");
    }
}else {
    System.out.println("loginUser : 로그인되지 않음");
}
%>
<!DOCTYPE html>
<html lang="kr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>SNS</title>
  <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/sns.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <script src="<%= request.getContextPath() %>/js/jquery-3.7.1.js"></script> 
</head> 
<script>
let IsDuplicate = false;
let NickDuplicate = false;
let emailcode = false;
$(document).ready(function() {
    // 다크모드 초기화 함수
    function DarkMode() {
        const currentMode = localStorage.getItem('mode') || 'light';
        const modeText = document.getElementById('modeText');
        if (currentMode === 'dark') {
            document.body.classList.add('dark-mode');
            modeText.textContent = '라이트모드';
        } else {
            document.body.classList.remove('dark-mode');
            modeText.textContent = '다크모드';
        }

        // 다크모드 토글 버튼 클릭 이벤트
        document.getElementById('modeToggle').addEventListener('click', function() {
            document.body.classList.toggle('dark-mode');
            const newMode = document.body.classList.contains('dark-mode') ? 'dark' : 'light';
            localStorage.setItem('mode', newMode);
            modeText.textContent = newMode === 'dark' ? '라이트모드' : '다크모드';
        });
    }

    // 페이지 로드 시 다크모드 초기화
    DarkMode();
    
    $(".icon").mouseover(function() {
        $(this).addClass('round');  // round 클래스 추가
    }).mouseout(function() {
        $(this).removeClass('round');  // round 클래스 제거
    });

    // 회원가입, 로그인 모달
    $(".userHeader a").click(function() {
        $("#user_modal").fadeIn();
        var clickedId = $(this).attr('id');
        var url = clickedId === "join" ? "<%= request.getContextPath() %>/user/join.do" : "<%= request.getContextPath() %>/user/login.do";
        
        $.ajax({
            url: url,
            type: "get",
            success: function(data) {
                $("#user_modalBody").html(data);
				
                // 동적으로 로드된 스크립트 실행
                $('script').each(function() {
                    if (this.src) {
                        $.getScript(this.src);
                    } else {
                        eval($(this).text());
                    }
                });
                
                resetEvents();

                // 다크모드 초기화 다시 실행
                DarkMode();
            }
        });
        
        
        $("header").css("z-index", "1"); 
        $("nav").css("z-index", "1"); 
    });

    $(window).click(function(event) {
        if ($(event.target).is("#user_modal")) {
            $("#user_modal").fadeOut();
            
       		// 모달창이 닫힐 때 header와 nav 색상 원래대로 되돌리기
            $("header").css("z-index", "1000");
            $("nav").css("z-index", "1001");
        }
    });
    
    
    $("#search").keyup(function(event){
		if(event.keyCode == 13)	{	
			//Enter문자가 눌려짐. keyCode 아스키코드. 13이 enter 
			document.searchFn.submit();
		}
	});
	
	
	$("#search").on('keyup',function(){
		if($(this).val().length > 0){
			$("#clearBtn").css("display","inline");
		}else{
			$("#clearBtn").css("display","none");
		}
	});
	
	$("#clearBtn").on('click',function(){
	    $("#search").val('');  // 올바른 코드
	    $(this).css("display","none");
	});


	/* 로그인,회원가입 */
    $("#uid").focus();
    $("#login_uid").focus();
    
    function resetEvents() {
        $("#uid, #upw, #upwcheck, #unick, #uemail, #login_uid, #login_upw").on("input", function() {
            DoReset();
        });

        // 회원가입 버튼에 다시 이벤트 연결
        $("#joinBtn").on("click", function() {
            DoJoin();
        });

        // 로그인 버튼에 다시 이벤트 연결
        $("#loginBtn").on("click", function() {
            DoLogin();
        });
    }
    
    
    
    /* 회원가입 */
    /* 회원가입 폼 제출 처리 */
    function openLoginModal() {
        $("#user_modal").fadeIn();
        
        $.ajax({
            url: "<%= request.getContextPath() %>/user/login.do",
            type: "get",
            success: function(data) {
                $("#user_modalBody").html(data);

                // 동적으로 로드된 스크립트 실행
                $('script').each(function() {
                    if (this.src) {
                        $.getScript(this.src);
                    } else {
                        eval($(this).text());
                    }
                });

                resetEvents();
                DarkMode();
            }
        });
    }
    
    
    
    $("#uid").on("input", function() {
	    var userid = $(this).val();
	    
	    // 아이디 길이가 4자 이상일 때에만 검증 요청
	    if(userid.length >= 4) {
	        $.ajax({
	            url: "<%= request.getContextPath() %>/user/idCheck.do",
	            type: "post",
	            data: { uid: userid },
	            dataType: "html",
	            success: function(result) {
	                result = result.trim();
	                switch(result) {
	                    case "00":
	                        $(".msg").html("사용 가능한 아이디입니다.");
	                        IsDuplicate = false;
	                        break;
	                    case "01":
	                        $(".msg").html("중복된 아이디입니다.");
	                        IsDuplicate = true;
	                        break;
	                }
	            }
	        });
	    }else {
	        $(".msg").html("아이디는 4자 이상이어야 합니다.");
	   	    // 동적으로 로드된 스크립트 실행
	    }
	});

	
	$("#unick").on("input", function() {
	    NickDuplicate = false;
	    var usernick = $(this).val();
	    
	    // 닉네임이 2글자 이상일 때 검증 요청
	    if (usernick.length >= 2) {
	        $.ajax({
	            url: "<%= request.getContextPath() %>/user/nickCheck.do",
	            type: "post",
	            data: { unick: usernick },
	            dataType: "html",
	            success: function(result) {
	                result = result.trim();
	                switch(result) {
	                    case "00":
	                        $(".msg").html("닉네임 체크 오류입니다.");
	                        break;
	                    case "01":
	                        $(".msg").html("중복된 닉네임입니다.");
	                        NickDuplicate = true;
	                        break;
	                    case "02":
	                        $(".msg").html("사용 가능한 닉네임입니다.");
	                        NickDuplicate = false;
	                        break;
	                }
	            }
	        });
	    }else {
	        // 닉네임이 2글자 미만일 경우 메시지 표시
	        $(".msg").html("닉네임은 2글자 이상이어야 합니다.");
	    }
	});
	
	function DoJoin() {
	    let id = document.getElementById("uid");

	    // 아이디 검사
	    if (id.value == "") {
	        $(".msg").html("아이디를 입력해주세요");
	        id.focus();
	        return false;
	    } else if (id.value.length < 4) {
	        $(".msg").html("아이디는 4글자 이상 입력해주세요");
	        id.focus();
	        return false;
	    } else if (!/^[a-z0-9]+$/.test(id.value)) {
	        $(".msg").html("아이디는 영어 소문자와 숫자만 가능합니다");
	        id.focus();
	        return false;
	    } else if (IsDuplicate == true) {
	        $(".msg").html("중복된 아이디입니다");
	        id.focus();
	        return false;
	    }

	    // 비밀번호 검사
	    let pass = document.getElementById("upw");
	    if (pass.value == "") {
	        $(".msg").html("비밀번호를 입력해주세요");
	        pass.focus();
	        return false;
	    } else if (pass.value.length < 4) {
	        $(".msg").html("비밀번호는 4글자 이상 입력해주세요");
	        pass.focus();
	        return false;
	    }

	    // 비밀번호 확인
	    let pwCheck = document.getElementById("upwcheck");
	    if (pwCheck.value == "") {
	        $(".msg").html("비밀번호 확인을 입력해주세요");
	        pwCheck.focus();
	        return false;
	    } else if (pwCheck.value != pass.value) {
	        $(".msg").html("비밀번호가 일치하지 않습니다");
	        pwCheck.focus();
	        return false;
	    }

	    // 닉네임 2글자 이상
	    let userNick = document.getElementById("unick");
	    if (userNick.value == "") {
	        $(".msg").html("닉네임을 입력해주세요");
	        userNick.focus();
	        return false;
	    } else if (userNick.value.length < 2) {
	        $(".msg").html("닉네임은 2글자 이상 입력해주세요");
	        userNick.focus();
	        return false;
	    } else if (NickDuplicate == true) {
	        $(".msg").html("이미 사용중인 닉네임입니다");
	        userNick.focus();
	        return false;
	    }

	    // 이메일 입력 확인
	    let mail = document.getElementById("uemail");
	    var emailPattern = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	    if(mail.value == "") {
	        $(".msg").html("이메일을 입력해주세요");
	        mail.focus();
	        return false;
	    }else if (!emailPattern.test(mail.value)) {
	        $(".msg").html("유효한 이메일 주소를 입력하세요");
	        mail.focus();
	        return false;
	    }if(emailcode == false)	{
	    	$(".msg").html("이메일 인증을 해주세요.");
			$("#code").focus();
			return false;
		}

	    // 회원가입 요청 전에 확인 메시지
	    if (confirm("회원가입을 완료하시겠습니까?") == true) {
	        // 폼 데이터 비동기적으로 전송
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
		                	 alert("서버와의 연결에 실패했습니다. 나중에 다시 시도해 주세요.");
		                     break;
		             }
	            }
	        });
	    }
	}

    
	/* 로그인 */
	$("#login_uid,#login_upw").keyup(function(event){
		if(event.keyCode == 13)
		{	//Enter문자가 눌려짐. keyCode 아스키코드. 13이 enter 
			DoLogin();
		}
	});
	
	function DoReset(){
		$(".msg").html("");  // 메시지 내용 지우기
	}	
	
	function DoLogin(){
		if($("#login_uid").val() == ""){
			$(".msg").html("아이디를 입력해주세요.");
			$("#login_uid").focus();
			return false;
		}
		
		if($("#login_upw").val() == ""){
			$(".msg").html("비밀번호를 입력해주세요.");
			$("#login_upw").focus();
			return false;
		}
		
		if(confirm("로그인하시겠습니까?") == true){
   	        // 폼 데이터 비동기적으로 전송
   	        var form = document.getElementById("loginFn");
   	        console.log(document.loginFn.uid.value);
   	        $.ajax({
   	        	url:"<%= request.getContextPath() %>/user/login.do",
   	        	type:"post",
   	        	data:{
   	        		uid: $("#login_uid").val(),
   	        		upw: $("#login_upw").val()
   	        	},
   	        	success:function(result){
   	        		result = result.trim();
	   	            switch(result) {
		                 case "success":
		                	 /* alert("로그인에 성공"); */
		   	                 window.location.href = "<%= request.getContextPath() %>";
		                     break;
		                 case "error":
		                	 openLoginModal();
	   	                     alert("로그인에 실패하셨습니다.");
		                     break;
		                 default :
		                	 alert("서버와의 연결에 실패했습니다. 나중에 다시 시도해 주세요.");
		                     break;
		             }
   	        	}
   	        });
   	       <%--  $.ajax({
   	            url: "<%= request.getContextPath() %>/user/login.do",  // 요청 URL
   	            type: "post",  // 요청 방식
   	            data: new FormData(form),  // 폼 데이터 전송
   	            processData: false,  // 자동 데이터 처리 방지
   	            contentType: false,  // 요청 시 Content-Type 설정 방지
   	            success: function(result) {
   	            	result = result.trim();
	   	            switch(result) {
		                 case "success":
		                	 alert("로그인에 성공");
		   	                 window.location.href = "<%= request.getContextPath() %>";
		                     break;
		                 case "error":
		                	 openLoginModal();
	   	                     alert("로그인에 실패하셨습니다.");
		                     break;
		                 default :
		                	 alert("서버와의 연결에 실패했습니다. 나중에 다시 시도해 주세요.");
		                     break;
		             }
   	            }
   	        }); --%>
		}
	}

	// 파일 업로드 초기화
    $(".deleteFile").css("display", "none");
    $("#preview").css("display", "none");

    // 파일이 선택될 때의 동작
    $("#file").on('change', function(){
        var fileName = $("#file").val().split('\\').pop(); // 경로 제거하고 파일명만 추출
        if(fileName) {
            $(".upload-name").val(fileName);
            $(".deleteFile").show();
            $("#preview").show();
            $("input[name='deleteFile']").prop('checked', false);

            // 파일 미리보기 처리 (이미지일 경우)
            var file = this.files[0];
            if (file && file.type.startsWith("image/")) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#preview').attr('src', e.target.result);
                };
                reader.readAsDataURL(file);
            }
        }
    });

    // 파일 업로드 창을 열도록 하는 동작
    $(".upload-name").on('click', function() {
        $("#file").click();  // 파일 선택 창 열기
    });

    // 파일 삭제 동작
    $("input[name='deleteFile']").on('change', function() {
        if($(this).is(':checked')) {
            $(".upload-name").val("첨부파일");
            $("#file").val("");  // 파일 선택 취소
            $(".deleteFile").css("display", "none");
            $("#preview").css("display", "none");
            document.getElementById('preview').src = "";  // 미리보기 초기화
        }
    });

    
	var headerHeight = $('header').outerHeight(); // 헤더의 높이
    
    $(window).scroll(function() {
        // 현재 스크롤 위치가 header의 높이를 초과하면
        if ($(window).scrollTop() > headerHeight) {
            $('.listDiv').each(function() {
                var divOffset = $(this).offset().top; // 각 listDiv의 상단 위치
                if (divOffset < headerHeight) {
                    $(this).css('display', 'none'); // 헤더를 지나쳤을 때 숨기기
                } else {
                    $(this).css('display', 'flex'); // 다시 보이게 하기
                }
            });
        } else {
            $('.listDiv').css('display', 'flex'); // 스크롤이 헤더보다 위에 있을 때는 보이게 하기
        }
    });
});

function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('preview').src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        document.getElementById('preview').src = "";
    }
}


function findPage(type) {
    var url = (type === "findId") 
    ? "<%= request.getContextPath() %>/user/findId.do" 
	: (type === "findPw") 
		? "<%= request.getContextPath() %>/user/findPw.do" 
		: "<%= request.getContextPath() %>/user/pwChange.do";
    
    $.ajax({
        url: url,
        type: "get",
        success: function(data) {
            $("#user_modalBody").html(data); // 모달 내부에 페이지 로드
            
            // 동적으로 로드된 스크립트 실행
            $('script').each(function() {
                if (this.src) {
                    $.getScript(this.src);
                } else {
                    eval($(this).text());
                }
            });
            
            // 필요한 이벤트 재설정
            resetEvents();
            
            // 다크모드 적용 (있는 경우)
            DarkMode();
        }
    });
}

//------------------ 인증번호 발송 버튼 -------------------
function SendMail() {
	var email = $("#uemail").val();
	var pattern = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	
	if(!pattern.test($("#uemail").val())) {
		$(".msg").html("올바른 메일주소를 입력해주세요");
		$("#uemail").focus();
		return;
	}else if($("#uemail").val().length < 5)	{
		$(".msg").html("메일주소를 5자 이상 입력해주세요.");
		$("#uemail").focus();
		return;
	}
	
	$.ajax({
		url: "<%= request.getContextPath() %>/user/sendmail.do",
		type : "post",
		data : {uemail : email},
		dataType: "html",
		success : function(result) {
			result = result.trim();
			alert(result);
		}			
	});		
}
// ----------- 이메일 코드인증 확인 버튼 --------------
function DoEmail() {
	if($("#code").val() == "") {
		$(".msg").html("인증코드를 입력하세요.");
		$("#code").focus();
		return;
		emailcode = false;
	}		
	
	// 받아온 코드값과 입력한 코드값을 비교
	$.ajax({
		url: "<%= request.getContextPath() %>/user/getcode.do",
		type : "post",
		dataType: "html",
		success : function(result) {
			result = result.trim();
			if($("#code").val() == result) {
				console.log(result);				
				$(".msg").html("인증이 완료되었습니다.");
				emailcode = true;
			}else {
				console.log(result);	
				$(".msg").html("인증코드가 일치하지 않습니다.");
				$("#code").focus();
				emailcode = false;
			}
		}			
	});		
}

</script>
<body>
	<!-- header 검색창, 프로필이미지, 알림, 메시지 -->
	<header>
        <div class="search_inner">
            <form action="index.jsp" method="get" name="searchFn" style="padding-bottom:30px;">
                <div class="search-wrapper">
                    <div id="seach-container"
                    style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    background-color: white;
                    border-radius: 20px;
                    width:80%; height: 42px;
                    margin-top: 1.5%; margin-left: 7%;
                    ">
                        <i class="fas fa-search" id="searchIcon"></i>
                        <input type="text" name="searchValue" id="search" placeholder="검색">
                        <i class="fas fa-times" id="clearBtn"></i>
                    </div>
                </div>
            </form>
        </div>
        <%
		if(loginUser != null){
		%>
        <!-- 로그인했을 경우 -->
        <div class="userHeader_login">
	        <!-- 알림 표시 -->
	        <div class="icon">
      	   		<img src="https://img.icons8.com/?size=100&id=3334&format=png&color=767676">
      	   		<div class="login-hover-menu">
		            <p>알림</p>
		        </div>
	        </div>
	        <!-- 메시지 표시 -->
	        <div class="icon">
	       		<img src="https://img.icons8.com/?size=100&id=37966&format=png&color=767676">
	       		<div class="login-hover-menu">
		            <p>메시지</p>
		        </div>
	        </div>
	        <!-- 프로필이미지 -->
	        <%
	        if(userPname != null && !userPname.equals("")){
        	%>
        	<img id="previewProfil" class="circular-img" 
        	onclick="location.href='<%= request.getContextPath() %>/user/mypage.do'"
	            style="border:none;" 
	            src="<%= request.getContextPath()%>/upload/<%= userPname %>"
           		alt="첨부된 이미지" style="max-width: 100%; height: auto;" />
        	<%-- <img id="previewProfil" class="circular-img" 
        	onclick="location.href='<%= request.getContextPath() %>/user/mypage.do'"
	            style="border:none;" 
	            src="<%= userPname != null && !userPname.equals("") 
	            ? request.getContextPath()+"/upload/" + userPname 
           		: "https://img.icons8.com/?size=100&id=115346&format=png&color=000000" %>" 
           		alt="첨부된 이미지" style="max-width: 100%; height: auto;" /> --%>
        	<%
	        }else{
	        	String firstNick = loginUser.getUnick().substring(0, 1);
        	%>
	        <div class="icon profileicon" 
	        onclick="location.href='<%= request.getContextPath() %>/user/mypage.do'"
	        style="background-color:#EEEEEE; border-radius: 50%; cursor: pointer;
	        display: flex; justify-content: center; align-items: center; margin-left:10px;
	         font-size: 24px; font-weight: bold; width: 70px; height: 70px;">
		        <%= firstNick %>
        	</div>
        	<%
	        }
	        %>
        </div>
		<%
		}else{
		%>
        <!-- 로그인하지 않았을 경우 -->
        <div class="userHeader">
            <a id="join">회원가입</a> | 
			<a id="login">로그인</a>
        </div>
		<%	
		}
		%>
	</header>
	<!-- 회원가입,로그인 모달창 -->
	<div id="user_modal" style="display:none;">
	    <div class="user_modal-content">
	        <div id="user_modalBody">
	            <!-- 회원가입,로그인페이지 여기에 표시됨 -->
	        </div>
	    </div>
	</div>