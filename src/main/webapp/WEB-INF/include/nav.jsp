<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sns.vo.*" %>
<%
UserVO loginUserNav = null;
if(session.getAttribute("loginUser") != null) {
	loginUserNav = (UserVO) session.getAttribute("loginUser");
}
%>
<!-- nav 인덱스페이지로 이동, 글쓰기버튼, 다크모드&라이트모드 전환, 관리자의 경우 신고내역확인 -->
<main>
	<nav>
		<ul>
			<li>
                <!-- 인덱스페이지 이동 -->
                <div class="menu-item">
	                <a href="<%= request.getContextPath() %>">
	                    <img src="https://img.icons8.com/?size=100&id=86527&format=png&color=767676" alt="인덱스로 이동">
	                </a>
	                <div class="hover-menu">
	                    <p>홈이동</p>
	                </div>
	            </div>
            </li>
			<li>
                <!-- 글쓰기버튼 -->
                <div class="menu-item">
                    <a href="<%= request.getContextPath() %>/board/write.do">
                        <img src="https://img.icons8.com/?size=100&id=59864&format=png&color=767676" alt="글쓰기">
                    </a>
                    <div class="hover-menu">
	                    <p>글쓰기</p>
	                </div>
                </div>
            </li>
			<li>
                <!-- 다크모드&라이트모드 전환 -->
                <div class="menu-item" id="modeToggle">
                    <a>
                        <img src="https://img.icons8.com/?size=100&id=101342&format=png&color=767676" alt="다크모드 전환">
                    </a>
                    <div class="hover-menu">
	                    <p id="modeText">다크모드</p>
	                </div>
                </div>
            </li>
            <%
			if(loginUserNav != null){
			%>
            <!-- 로그인한 경우 로그아웃 -->
			<li>
                <div class="menu-item">
                    <a href="<%= request.getContextPath() %>/user/logout.do">
                        <img class="logout" src="https://img.icons8.com/?size=100&id=BvRKVanAagI0&format=png&color=767676" alt="로그아웃">
                    </a>
                    <div class="hover-menu">
	                    <p>로그아웃</p>
	                </div>
                </div>
            </li> 
            <%
            	if(loginUserNav.getUauthor().equals("A")){
           		%>
            <!-- 관리자의 경우 신고내역확인 -->
			<li>
                <div class="menu-item">
                    <a href="complain.jsp">
                        <img src="https://img.icons8.com/?size=100&id=8773&format=png&color=767676" alt="관리자 신고 관리">
                    </a>
                    <div class="hover-menu">
	                    <p>신고관리</p>
	                </div>
                </div>
            </li>
           		<%
            	}
			}
			%>
		</ul>
	</nav>