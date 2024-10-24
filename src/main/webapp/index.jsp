<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp" %>
<%@ include file="/WEB-INF/include/nav.jsp" %>
<%@ page import="sns.util.*" %>
<%
Connection conn = null;			//DB 연결
PreparedStatement psmt = null;	//SQL 등록 및 실행. 보안이 더 좋음!
ResultSet rs = null;			//조회 결과를 담음

List<BoardVO> bList = new ArrayList<>();

//try 영역
try{
	conn = DBConn.conn();
	
	String sql = "select b.bno,pname " 
			+ "from board b "
			+ "inner join attach a " 
			+ "on b.bno = a.bno ";
	sql += " where b.state='E' ";
	sql	+= "order by bno desc ";
	psmt = conn.prepareStatement(sql);
	
	rs = psmt.executeQuery();
%>
<!--웹페이지 본문-->
<section class="scrollable">
	<div id="indexDiv">
<%	
	while(rs.next()){
		%>
		<div class="listDiv" id="<%= rs.getString("bno") %>">
            <img style="width: 250px; height: 250px; border-radius: 20px;" 
            src="<%= request.getContextPath() %>/upload/<%= rs.getString("pname") %>">
        </div>
		<%
	}
%>
	</div> 
</section>
<%
}catch(Exception e){
	e.printStackTrace();
}finally{
	try {
		DBConn.close(rs, psmt, conn);
	}catch(Exception e) {
		e.printStackTrace();
	}
}
%>
<%@ include file="/WEB-INF/include/aside.jsp" %>