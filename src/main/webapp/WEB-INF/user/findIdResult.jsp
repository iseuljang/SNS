<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String uid = (String)request.getAttribute("uid");
%>
<!--웹페이지 본문-->
<div>
	<div class="user_title" id="findIdPw">
		<a id="findId" onclick="findPage('findId');">
			아이디찾기
		</a>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a id="findPw" onclick="findPage('findPw');" style="color:gray;">
			비밀번호찾기
		</a>
    </div>
	<div class="user_inner">
		<h2>가입한 아이디</h2>
		<table>
			<tr>
			    <td>
			        <div class="user-container">
			            <i class="fas fa-user" id="user_itag7"></i>
			            <span id="login_uid"><%= uid %></span>
			        </div>
			    </td>
			</tr>
			<tr>
				<td>
					<input class="userBtn" type="button" value="비밀번호찾기" onclick="findPage('findPw');">
				</td>
			</tr>
		</table>
	</div>
</div>