<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String lState = "";
String lBno = (String)request.getAttribute("bno");

if(request.getAttribute("lState") != null && !request.getAttribute("lState").equals("")){
	lState = (String)request.getAttribute("lState");
}

if(request.getAttribute("bno") != null && !request.getAttribute("bno").equals("")){
	lBno = (String)request.getAttribute("bno");
}

System.out.println("lState: " + lState + ", bno: " + lBno);  // 콘솔 출력으로 확인

// 추천 상태에 따라 적절한 이미지를 표시
if(lState.equals("E")) {
    // 추천한 상태일 때
%>
    <img style="width:30px; cursor:pointer;" 
         src="https://img.icons8.com/?size=100&id=12306&format=png&color=5D4037" 
         onclick="recoAdd('<%= lBno %>')" />
<%
}else {
    // 추천하지 않은 상태일 때
%>
    <img style="width:30px; cursor:pointer;" 
         src="https://img.icons8.com/?size=100&id=87&format=png&color=000000" 
         onclick="recoAdd('<%= lBno %>')" />
<%
}
%>