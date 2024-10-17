<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
    <div class="user_title">
		<a href="#">로그인</a>
    </div>
    <div class="user_inner">
        <form action="login.do" method="post">
            <table>
                <tr>
                    <td>
                        <div class="user-container">
                            <i class="fas fa-user" id="user_itag7"></i>
                            <input type="text" name="uid" id="uid" placeholder="아이디" onkeydown="DoReset();">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="user-container">
                            <i class="fas fa-lock" id="user_itag8"></i>
                            <input type="password" name="upw" id="upw" placeholder="비밀번호" onkeydown="DoReset();">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span id="msg" style="color:green;">&nbsp;</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <button type="button" onclick="DoLogin();" class="userBtn">로그인하기</button>
                        <br>
                        <a href="<%= request.getContextPath() %>/findid.do">
                            <button type="button" class="userBtn">아이디/비밀번호 찾기</button>
                        </a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>