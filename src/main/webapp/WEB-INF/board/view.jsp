<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--웹페이지 본문-->
<div class="view_div">
    <div class="view_inner">
    	<label>
			<span class="imgSpan" style="width: 550px; height: 550px;">사진</span>
		</label>
       	<div class="view_content" style="width: 50%;">
       		<div class="icon-container">
				<div class="reco" style="width:30px; cursor:pointer;">
				<!-- 추천표시되는곳 -->
					<img style='width:30px; cursor:pointer;' src='https://img.icons8.com/?size=100&id=12306&format=png&color=5D4037'>
				</div>
				<!-- 이미지 다운로드 -->
				<a>
					<img style="width:30px;" src="https://img.icons8.com/?size=100&id=gElSR9wTv6aF&format=png&color=000000">
				</a>
				<div class="complain" style="width:30px; cursor:pointer; margin-bottom:5px;">
				<!-- 메뉴바 -->
					<img style="width:30px; transform: rotate(90deg);" src="https://img.icons8.com/?size=100&id=98963&format=png&color=000000">
				</div>
			</div>
       	<p style="font-size:26px; margin:10px 0;">딸기타르트</p>
		<div style="font-size:16px; margin-top:5px;">
			<div class="view_profil">
		        <!-- 프로필 이미지가 있을 경우 -->
		        <img id="previewProfil" class="circular-img" 
		             style="border:none; width:50px; height:50px;" 
		             src="<%= request.getContextPath() %>/upload/96a49eb6-2c1b-4dab-b538-2defa1fa1043" alt="프로필 이미지" />
			    <span>흰둥이</span>
			    <button class="ssBtn">팔로우</button>
			</div>
		&nbsp;
		2024.10.22 16:04&nbsp;
		추천수&nbsp; 20&nbsp;
		조회수&nbsp; 20
		</div><br>
		<div>맛있는 딸기 타르트</div>
		<!-- 댓글위치 -->
		<div class="comment_inner">
			<table>
				<tr>
					<td colspan="3">
						<div class="search-wrapper">
							<div class="input-container" id="seach-container"
							style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
						    background-color: white;
						    border-radius: 40px;
						    width:100%;
						    display: flex;
						    align-items: center; 
						    gap: 10px;
						    ">
								<i class="fas solid fa-comment-dots" style="margin-left:10px;"></i>
								<input type="text" name="comment" placeholder="댓글"	
								style="padding-right: 40px;	background-color: white;" size="50" readonly>
            			    </div>
         			    </div>
					</td>
				</tr>
			</table>
			<!-- 댓글목록 출력 -->
			<div class="commentDiv">
				<div style="display: flex; align-items: center; gap: 10px;">
        			<div class="view_profil">
						<img id="previewProfil" class="circular-img" 
			             style="border:none; width:50px; height:50px;" 
			             src="<%= request.getContextPath() %>/upload/96a49eb6-2c1b-4dab-b538-2defa1fa1043" alt="프로필 이미지" />
				    </div>
		            <span style="font-size:18px;">흰둥이</span> <!-- 닉네임 -->
		       </div>
			   <div style="margin-top: 5px; margin-left: 70px;" id="content">
                	<span>맛있어보여요!</span>
           	    </div>
           	    <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 5px; margin-left: 70px; font-size: 12px; color: #999;">
		            <!-- 작성일 -->
		            <span>2024.10.14 16:19</span>
	           	    <div style="display: flex; align-items: center; gap: 10px;"> 
			            <span id="menuB" class="menuB" style="display: flex; align-items: center; gap: 10px;">
			            	<i class="fas fa-solid fa-bars"></i>
				  	  	</span>
			  	    </div>
		  	    </div>
           	    
			</div>	
			</form> 
			</div>
        </div>
	</div>
</div>