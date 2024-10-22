<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<!--웹페이지 본문-->
<section>
       <div class="bcSpan">
           <a href="<%= request.getContextPath() %>/admin/blackList.do">
           		<span class="bUnderline" style="color: lightgray;"> 블랙리스트 </span>
           </a>
           <a href="<%= request.getContextPath() %>/admin/complainList.do">
          	 	<span class="cUnderline" > 신고 게시글 </span>
           </a>
       </div>
       <div class="complainTable">
         
           <table class="inner_table">
               <thead>
                   <th>신고 번호</th>
                   <th>닉네임</th>
                   <th>제목</th>
                   <th>작성일</th>
                   <th>신고 횟수</th>
               </thead>
               <tbody>
                   <tr>
                       <td>10</td>
                       <td>hong</td>
                       <td>고수익 단기 알바!~</td>
                       <td>10.14</td>
                       <td>100번</td>
                   </tr>
                   <tr>
                       <td>10</td>
                       <td>hong</td>
                       <td>고수익 단기 알바!~</td>
                       <td>10.14</td>
                       <td>100번</td>
                   </tr>
                   <tr>
                       <td>10</td>
                       <td>hong</td>
                       <td>고수익 단기 알바!~</td>
                       <td>10.14</td>
                       <td>100번</td>
                   </tr>
                   <tr>
                       <td>10</td>
                       <td>hong</td>
                       <td>고수익 단기 알바!~</td>
                       <td>10.14</td>
                       <td>100번</td>
                   </tr>
                   <tr>
                       <td>10</td>
                       <td>hong</td>
                       <td>고수익 단기 알바!~</td>
                       <td>10.14</td>
                       <td>100번</td>
                   </tr>
               </tbody>
           </table>
       </div>
</section>
<%@ include file="../include/aside.jsp" %>