<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<!--웹페이지 본문-->
<section>
    <div class="article_inner">
        <div class="pwChange_title">
			<a href="idCheck.jsp" style="color:gray;">아이디 찾기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="pwCheck.jsp" style="text-decoration: underline; text-underline-offset: 6px;">비밀번호 찾기</a>
       </div>
       <div class="pwChange_inner">
           <form action="pwChange.do" method="post">
               <table>
                   <tr>
                       <td>
                           <div class="input-container">
                               <i class="fas fa-lock" id="pw_tag"></i>
                               <input type="password" name="upw" id="upw" size="50" placeholder="비밀번호">
                           </div>
                       </td>
                   </tr>
                   <tr>
                       <td>
                           <div class="input-container">
                               <i class="fas fa-lock" id="pwc_tag"></i>
                               <input type="password" name="upwCheck" size="50" id="upwCheck" placeholder="비밀번호 확인">
                           </div>
                       </td>
                   </tr>
                   <tr>
                       <td>
                           <button type="button" class="userBtn">비밀번호 재설정</button>
                       </td>
                   </tr>
               </table>
           </form>
       </div>
   </div>
</section>
<%@ include file="../include/aside.jsp" %>