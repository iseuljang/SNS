<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<!--웹페이지 본문-->
<section>
       <div class="bcSpan">
           <a href="<%= request.getContextPath() %>/admin/blackList.do">
           		<span class="bUnderline" > 블랙리스트 </span>
           </a>
           <a href="<%= request.getContextPath() %>/admin/complainList.do">
          	 	<span class="cUnderline" style="color: lightgray;"> 신고 게시글 </span>
           </a>
       </div>
       <div class="complainTable">
           <table class="inner_table">
               <thead>
               	<tr>
                    <th>신고 번호</th>
                    <th>닉네임</th>
                    <th>이메일</th>
                    <th>가입일</th>
                    <th>신고 횟수</th>
                    <th>정지</th>
                   </tr>
               </thead>
               <tbody>
                   <tr>
                       <td>5</td>
                       <td>hong</td>
                       <td>hong@hong.com</td>
                       <td>10.14</td>
                       <td>5번</td>
                       <td>
                       		<button type="button" class="ssBtn">정지</button>
                       </td>
                   </tr>
                   <tr>
                       <td>4</td>
                       <td>go</td>
                       <td>go@go.com</td>
                       <td>10.13</td>
                       <td>15번</td>
                       <td>
                       		<button type="button" class="ssBtn">정지</button>
                       </td>
                   </tr>
                   <tr>
                       <td>3</td>
                       <td>lee</td>
                       <td>lee@lee.com</td>
                       <td>10.12</td>
                       <td>12번</td>
                       <td>
                       		<button type="button" class="ssBtn">정지</button>
                       </td>
                   </tr>
                   <tr>
                       <td>2</td>
                       <td>back</td>
                       <td>back@back.com</td>
                       <td>10.11</td>
                       <td>10번</td>
                       <td>
                       		<button type="button" class="ssBtn">정지</button>
                       </td>
                   </tr>
                   <tr>
                       <td>1</td>
                       <td>kim</td>
                       <td>kim@kim.com</td>
                       <td>10.10</td>
                       <td>20번</td>
                       <td>
                       		<button type="button" class="ssBtn" 
                       		style="background-color:#767676; color:white;">정지해제</button>
                       </td>
                   </tr>
               </tbody>
           </table>
       </div>
</section>
<%@ include file="../include/aside.jsp" %>