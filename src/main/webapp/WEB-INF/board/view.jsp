<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ include file="../include/nav.jsp" %>
<!--웹페이지 본문-->
<section>
	<div id="indexDiv">
       <!-- 1번째 줄 -->
       <div class="viewTable">
           <div class="viewImage">
               <img src="rabbit.png" alt="Dinosaur" />
           </div>
           <div class="viewTable_text">
               <div class="viewSpan">
                   <span><img src="basic_heart_flat_vector.jpg" alt="down" /></span>
                   <span><img src="download_120262.png" alt="down" /></span>
                   <span><img src="settings_gear_option_rotate_options_icon-icons.com_76298.png" alt="down" /></span>
               </div>
               <p align="left">제목 </p>
               <div class="user_info">
                   <img src="rabbit.png" alt="rabbit" />
                   <div class="inner_info"> 
                       <div>가나다라마바사아자차카타파하</div><br>
                       <div> 2024-10-16 </div>
                   </div>
                   <button>팔로우</button>
               </div>
               <div class="viewTextarea">
                   <textarea placeholder="글 내용" rows="10" cols="50"></textarea>
               </div>
               <div class="viewReply">
                   <div class="replyForm">
                       <img class="img" src="./rabbit.png" alt="사진x">
                       <input type="text"name="uid" size="30" placeholder="댓글"> 
                       <button>작성</button>
                   </div>
               </div>
           </div>
       </div>
   </div>
</section>
<%@ include file="../include/aside.jsp" %>