package sns.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import sns.util.DBConn;
import sns.vo.UserVO;

public class UserController {
	public UserController(HttpServletRequest request
			, HttpServletResponse response
			, String[] comments) throws ServletException, IOException {
		
		if(comments[comments.length-1].equals("login.do")) {
			if(request.getMethod().equals("GET")) {
				login(request,response);
			}else if (request.getMethod().equals("POST")) {
				
				loginOk(request,response);
			}
		}else if(comments[comments.length-1].equals("logout.do")) {
			logout(request,response);
		}else if(comments[comments.length-1].equals("mypage.do")) {
			mypage(request,response);
		}else if(comments[comments.length-1].equals("join.do")) {
			if(request.getMethod().equals("GET")) {
				join(request,response);
			}else if (request.getMethod().equals("POST")) {
				joinOk(request,response);
			}
		}else if(comments[comments.length-1].equals("idCheck.do")) {
			idCheck(request,response);
		}else if(comments[comments.length-1].equals("nickCheck.do")) {
			nickCheck(request,response);
		}
	}
	
	public void login(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
	}
	
	public void loginOk(HttpServletRequest request
			, HttpServletResponse response) {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
		
		Connection conn = null;			//DB 연결
		PreparedStatement psmt = null;	//SQL 등록 및 실행. 보안이 더 좋음!
		ResultSet rs = null;			//조회 결과를 담음

		//try 영역
		try{
			conn = DBConn.conn();
			
			String sql = "select * from user where uid = ? and upw = md5(?) and ustate='E' ";
			psmt = conn.prepareStatement(sql);
			System.out.println(sql);
			psmt.setString(1, uid);
			psmt.setString(2, upw);
			
			rs = psmt.executeQuery();
			
			if(rs.next()){
				UserVO loginUser = new UserVO();
				loginUser.setUno(rs.getString("uno"));
				loginUser.setUnick(rs.getString("unick"));
				loginUser.setUauthor(rs.getString("uauthor"));
				loginUser.setPname(rs.getString("pname"));
				
				//로그인 정보 session에 저장
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				/* response.sendRedirect(request.getContextPath()); */
				
		        response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();  
	            out.print("success");  
		        out.flush();
		        out.close();
			}else {
				response.setContentType("text/html;charset=UTF-8");
		        PrintWriter out = response.getWriter();  
	            out.print("error");  
		        out.flush();
		        out.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DBConn.close(rs, psmt, conn);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void logout(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath());
	}
	
	public void mypage(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		String uno = loginUser.getUno();
		
		Connection conn = null;			//DB 연결
		PreparedStatement psmt = null;	//SQL 등록 및 실행. 보안이 더 좋음!
		ResultSet rs = null;			//조회 결과를 담음

		//try 영역
		try{
			conn = DBConn.conn();
			
			String sql = "select * from user where uno=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uno);
			
			rs = psmt.executeQuery();
			
			if(rs.next()){
				UserVO user = new UserVO();
				user.setUno(rs.getString("uno"));
				user.setUid(rs.getString("uid"));
				user.setUnick(rs.getString("unick"));
				user.setUemail(rs.getString("uemail"));
				user.setUstate(rs.getString("ustate"));
				user.setUauthor(rs.getString("uauthor"));
				user.setUrdate(rs.getString("urdate"));
				user.setPname(rs.getString("pname"));
				user.setFname(rs.getString("fname"));
				
				request.setAttribute("user",user);
				
				request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request, response);
			}else {
				//회원조회 실패할 경우
				response.sendRedirect(request.getContextPath()+"/login.do");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DBConn.close(rs, psmt, conn);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void join(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/join.jsp").forward(request, response);
	}
	
	public void joinOk(HttpServletRequest request
			, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		/* String uploadPath = request.getServletContext().getRealPath("/upload"); */
		String uploadPath = "C:\\Users\\DEV\\Desktop\\JangAWS\\01.java\\workspace\\sns\\src\\main\\webapp\\upload";
		System.out.println("서버의 업로드 폴더 경로 : " + uploadPath);

		int size = 10 * 1024 * 1024;
		MultipartRequest multi;
		try {
		    // 파일 업로드 처리
		    multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
		    // 파일 업로드 실패 시 처리
		    response.sendRedirect(request.getContextPath());
		    return;
		}

		// 업로드된 파일명 얻기
		Enumeration files = multi.getFileNames();
		String filename = null;  // 원본 파일 이름
		String phyname = null;   // 서버에 저장될 파일 이름

		if (files.hasMoreElements()) {
		    String fileid = (String) files.nextElement();
		    filename = multi.getFilesystemName(fileid);  // 원본 파일 이름 가져오기

		    if (filename != null) {
		        System.out.println("업로드된 파일 이름: " + filename);
		        
		        // 물리 파일 이름 생성 (UUID 사용)
		        phyname = UUID.randomUUID().toString();  
		        
		        // 파일 경로 설정
		        String srcName = uploadPath + "/" + filename;  
		        String targetName = uploadPath + "/" + phyname;
		        
		        // 파일 이름 변경 (UUID로 저장)
		        File srcFile = new File(srcName);
		        File targetFile = new File(targetName);

		        boolean renamed = srcFile.renameTo(targetFile);
		        if (!renamed) {
		            System.out.println("파일 이름 변경 실패");
		        } else {
		            System.out.println("파일 이름 변경 성공: " + phyname);
		        }
		    }
		}

		String uid = multi.getParameter("uid");
		String upw = multi.getParameter("upw");
		String unick = multi.getParameter("unick");
		String uemail = multi.getParameter("uemail");

		// 파일 이름이 없으면 빈 값 처리
		if (phyname == null) phyname = "";
		if (filename == null) filename = "";
		
		Connection conn = null;			
		PreparedStatement psmt = null;

		try{
			conn = DBConn.conn();
			
			String sql = "insert into user (uid,upw,unick,uemail,pname,fname) "
					+ " values (?,md5(?),?,?,?,?) ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uid);
			psmt.setString(2, upw);
			psmt.setString(3, unick);
			psmt.setString(4, uemail);
			psmt.setString(5, phyname);  // 물리 파일 이름 (서버에 저장된 파일 이름)
		    psmt.setString(6, filename);  // 원본 파일 이름 (사용자가 업로드한 파일 이름)
			
			psmt.executeUpdate();
			
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();  
            out.print("success");  
	        out.flush();
	        out.close();
		}catch(Exception e){
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();  
            out.print("error");  
	        out.flush();
	        out.close();
		}finally{
			try {
				DBConn.close(psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void idCheck(HttpServletRequest request
			, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid");

		Connection conn = null;			
		PreparedStatement psmt = null;
		ResultSet rs = null;	


		try{
			conn = DBConn.conn();
			
			String sql = "select uid from user "
					+ " where uid=? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uid);
			
			rs = psmt.executeQuery();
			
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();  // 클라이언트로 응답을 보낼 준비
	        if(rs.next()){
	            out.print("01");  // 중복된 아이디
	        } else {
	            out.print("00");  // 사용 가능한 아이디
	        }
	        out.flush();
	        out.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DBConn.close(rs,psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void nickCheck(HttpServletRequest request
			, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String unick = request.getParameter("unick");

		Connection conn = null;			
		PreparedStatement psmt = null;
		ResultSet rs = null;	


		try{
			conn = DBConn.conn();
			
			String sql = "select unick from user "
					+ " where unick=? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, unick);
			
			rs = psmt.executeQuery();
			
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();  // 클라이언트로 응답을 보낼 준비
	        if(rs.next()){
	            out.print("01");  
	        } else {
	            out.print("02");  
	        }
	        out.flush();
	        out.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DBConn.close(rs,psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
