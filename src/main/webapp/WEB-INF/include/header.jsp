<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="sns.vo.*" %>
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

                // 다크모드 초기화 다시 실행
                DarkMode();
            }
        });
    });

    $(window).click(function(event) {
        if ($(event.target).is("#user_modal")) {
            $("#user_modal").fadeOut();
        }
    });

    $("#uid").focus();

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
        <!-- 로그인했을 경우 -->
        <!-- <div class="userHeader"> -->
        <!-- 알림 표시 -->
        <!-- <img src="https://img.icons8.com/?size=100&id=3334&format=png&color=767676"> -->
        <!-- 메시지 표시 -->
        <!-- <img src="https://img.icons8.com/?size=100&id=37966&format=png&color=767676"> -->
        <!-- 프로필이미지 -->
        <!-- </div> -->
        <!-- 로그인하지 않았을 경우 -->
        <div class="userHeader">
            <a id="join">회원가입</a> | 
			<a id="login">로그인</a>
        </div>
	</header>
	<!-- 회원가입,로그인 모달창 -->
	<div id="user_modal" style="display:none;">
	    <div class="user_modal-content">
	        <div id="user_modalBody">
	            <!-- 회원가입,로그인페이지 여기에 표시됨 -->
	        </div>
	    </div>
	</div>