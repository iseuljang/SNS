package sns.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import sns.util.DBConn;
import sns.util.Sendmail;
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
		}else if(comments[comments.length-1].equals("sendmail.do")) {
			sendmail(request,response);
		}else if(comments[comments.length-1].equals("getcode.do")) {
			getcode(request,response);
		}else if(comments[comments.length-1].equals("profileModify.do")) {
			if(request.getMethod().equals("GET")) {
				profileModify(request,response);
			}else if (request.getMethod().equals("POST")) {
				profileModifyOk(request,response);
			}
		}else if(comments[comments.length-1].equals("findId.do")) {
			if(request.getMethod().equals("GET")) {
				findId(request,response);
			}else if (request.getMethod().equals("POST")) {
				findIdOk(request,response);
			}
		}else if(comments[comments.length-1].equals("findIdResult.do")) {
			if (request.getMethod().equals("POST")) {
				findIdResult(request,response);
			}
		}else if(comments[comments.length-1].equals("findPw.do")) {
			findPw(request,response);
		}else if(comments[comments.length-1].equals("pwChange.do")) {
			pwChange(request,response);
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
		
		Connection conn = null;			//DB ����
		PreparedStatement psmt = null;	//SQL ��� �� ����. ������ �� ����!
		ResultSet rs = null;			//��ȸ ����� ����

		//try ����
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
				
				//�α��� ���� session�� ����
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
		
		Connection conn = null;			//DB ����
		PreparedStatement psmt = null;	//SQL ��� �� ����. ������ �� ����!
		ResultSet rs = null;			//��ȸ ����� ����

		//try ����
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
				//ȸ����ȸ ������ ���
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
		System.out.println("������ ���ε� ���� ��� : " + uploadPath);

		int size = 10 * 1024 * 1024;
		MultipartRequest multi;
		try {
		    // ���� ���ε� ó��
		    multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
		    // ���� ���ε� ���� �� ó��
		    response.sendRedirect(request.getContextPath());
		    return;
		}

		// ���ε�� ���ϸ� ���
		Enumeration files = multi.getFileNames();
		String filename = null;  // ���� ���� �̸�
		String phyname = null;   // ������ ����� ���� �̸�

		if (files.hasMoreElements()) {
		    String fileid = (String) files.nextElement();
		    filename = multi.getFilesystemName(fileid);  // ���� ���� �̸� ��������

		    if (filename != null) {
		        System.out.println("���ε�� ���� �̸�: " + filename);
		        
		        // ���� ���� �̸� ���� (UUID ���)
		        phyname = UUID.randomUUID().toString();  
		        
		        // ���� ��� ����
		        String srcName = uploadPath + "/" + filename;  
		        String targetName = uploadPath + "/" + phyname;
		        
		        // ���� �̸� ���� (UUID�� ����)
		        File srcFile = new File(srcName);
		        File targetFile = new File(targetName);

		        boolean renamed = srcFile.renameTo(targetFile);
		        if (!renamed) {
		            System.out.println("���� �̸� ���� ����");
		        } else {
		            System.out.println("���� �̸� ���� ����: " + phyname);
		        }
		    }
		}

		String uid = multi.getParameter("uid");
		String upw = multi.getParameter("upw");
		String unick = multi.getParameter("unick");
		String uemail = multi.getParameter("uemail");

		// ���� �̸��� ������ �� �� ó��
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
			psmt.setString(5, phyname);  // ���� ���� �̸� (������ ����� ���� �̸�)
		    psmt.setString(6, filename);  // ���� ���� �̸� (����ڰ� ���ε��� ���� �̸�)
			
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
	        PrintWriter out = response.getWriter();  // Ŭ���̾�Ʈ�� ������ ���� �غ�
	        if(rs.next()){
	            out.print("01");  // �ߺ��� ���̵�
	        } else {
	            out.print("00");  // ��� ������ ���̵�
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
	        PrintWriter out = response.getWriter();  // Ŭ���̾�Ʈ�� ������ ���� �غ�
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
	
	public void sendmail(HttpServletRequest request
			, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("uemail");

		if(email == null || email.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();  
			out.print("�ùٸ� �����ּҰ� �ƴմϴ�.");
			return;
		}
		//-------------------- �̸��� �ּҷ� ������ȣ ���� ----------------------- 
		//�̸��� ��ü ����
		Sendmail sender = new Sendmail();
		//�����ڵ带 ��´�.
		String code = sender.AuthCode(6);
		sender.setFrom("gyr0204@naver.com");
		sender.setAccount("gyr0204", "zxcv1234!!");

		//�޴��̸� ������ �Է��� �̸��� �ּҷ� ����
		sender.setTo(email);

		sender.setMail("�̸��� �����ڵ��Դϴ�.", "�����ڵ� : " + code);

		if(sender.sendMail() == true)	{
			// �ش� �ּҷ� ���� ������ �������� ���
			HttpSession session = request.getSession();
			session.setAttribute("code", code);
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();  
			out.println("������ �߼��Ͽ����ϴ�.");
		}else {
			// �������� ���
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();  
			out.println("���� �߼ۿ� �����Ͽ����ϴ�.");
		}
	}
	
	public void getcode(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    HttpSession session = request.getSession();
	    String code = (String) session.getAttribute("code");

	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.print(code); // ���ǿ� ����� �����ڵ带 Ŭ���̾�Ʈ�� ��ȯ
	}
	
	public void profileModify(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		String uno = loginUser.getUno();
		System.out.println("profileModify uno : " + uno);
		
		Connection conn = null;			//DB ����
		PreparedStatement psmt = null;	//SQL ��� �� ����. ������ �� ����!
		ResultSet rs = null;			//��ȸ ����� ����

		//try ����
		try{
			conn = DBConn.conn();
			
			String sql = "select * from user where uno=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uno);
			
			rs = psmt.executeQuery();
			if(rs.next()){
				System.out.println("profileModify rs : rs.next() �����");
				UserVO userModify = new UserVO();
				userModify.setUno(rs.getString("uno"));
				userModify.setUid(rs.getString("uid"));
				userModify.setUnick(rs.getString("unick"));
				userModify.setUemail(rs.getString("uemail"));
				userModify.setUstate(rs.getString("ustate"));
				userModify.setUauthor(rs.getString("uauthor"));
				userModify.setUrdate(rs.getString("urdate"));
				userModify.setPname(rs.getString("pname"));
				userModify.setFname(rs.getString("fname"));
				
				request.setAttribute("userModify",userModify);
				request.getRequestDispatcher("/WEB-INF/user/profileModify.jsp").forward(request, response);
			}else {
				//ȸ����ȸ ������ ���
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
	
	public void profileModifyOk(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    request.setCharacterEncoding("UTF-8");
	    String uploadPath = "C:\\Users\\DEV\\Desktop\\JangAWS\\01.java\\workspace\\sns\\src\\main\\webapp\\upload";
	    System.out.println("������ ���ε� ���� ��� : " + uploadPath);
	    
	    int size = 10 * 1024 * 1024; // �ִ� 10MB ���� ���
	    MultipartRequest multi;

	    try {
	        multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.sendRedirect(request.getContextPath());
	        return;
	    }

	    // ���ε�� ���ϸ� ��������
	    Enumeration<String> files = multi.getFileNames();
	    String filename = null; // ��������
	    String phyname = null; // �ٲ��̸�

	    if (files.hasMoreElements()) {
	        String fileid = files.nextElement();
	        filename = multi.getFilesystemName(fileid);
	        
	        if (filename != null) {
	            System.out.println("���ε�� ���� �̸�: " + filename);
	            phyname = UUID.randomUUID().toString(); // UUID ����
	            File srcFile = new File(uploadPath + "/" + filename);
	            File targetFile = new File(uploadPath + "/" + phyname);
	            
	            if (!srcFile.renameTo(targetFile)) {
	                System.out.println("���� �̸� ���� ����");
	            } else {
	                System.out.println("���� �̸� ���� ����: " + phyname);
	            }
	        }
	    }
	    
	    HttpSession session = request.getSession();
	    UserVO user = (UserVO) session.getAttribute("loginUser");
	    String uno = user.getUno();
	    String upw = multi.getParameter("upw");
	    
	    if (upw == null || upw.trim().isEmpty()) {
	        System.out.println("��й�ȣ�� ���۵��� �ʾҽ��ϴ�.");
	        PrintWriter out = response.getWriter();
	        out.println("<script>alert('��й�ȣ�� �Է��ϼ���.'); history.back();</script>");
	        return;
	    }
	    
	    String unick = multi.getParameter("unick");
	    String deleteFile = multi.getParameter("deleteFile");
	    deleteFile = (deleteFile == null || deleteFile.isEmpty()) ? "N" : deleteFile;

	    String sql = "SELECT * FROM user WHERE uno = ? AND upw = md5(?) AND ustate = 'E'";
	    
	    try (Connection conn = DBConn.conn(); 
	         PreparedStatement psmt = conn.prepareStatement(sql)) {
	        
	        psmt.setString(1, uno);
	        psmt.setString(2, upw);
	        
	        System.out.println("��й�ȣ Ȯ�� SQL: " + sql);
	        
	        try (ResultSet rs = psmt.executeQuery()) {
	            if (rs.next()) {
	                // ������ ���� ó��
	                if ("Y".equals(deleteFile)) {
	                    String sqlDelete = "UPDATE user SET pname = '', fname = '' WHERE uno = ?";
	                    try (PreparedStatement psmtFile = conn.prepareStatement(sqlDelete)) {
	                        psmtFile.setInt(1, Integer.parseInt(uno));
	                        int deleteCount = psmtFile.executeUpdate();
	                        System.out.println("������ ���� SQL: " + sqlDelete);
	                        System.out.println("������ ���� ���: " + deleteCount);
	                        
	                        // ���ǿ��� ������ ���� ����
	                        user.setPname("");
	                        user.setFname("");
	                    }
	                } else if (filename != null) {
	                    // ���� ���ε� ó��
	                    String sqlFile = "UPDATE user SET pname = ?, fname = ? WHERE uno = ?";
	                    try (PreparedStatement psmtFile = conn.prepareStatement(sqlFile)) {
	                        psmtFile.setString(1, phyname);
	                        psmtFile.setString(2, filename);
	                        psmtFile.setInt(3, Integer.parseInt(uno));

	                        int fileUpdateCount = psmtFile.executeUpdate();
	                        System.out.println("������ ������Ʈ SQL: " + sqlFile);
	                        System.out.println("������ ������Ʈ ���: " + fileUpdateCount);
	                        
	                        if (fileUpdateCount > 0) {
	                            user.setPname(phyname);
	                            user.setFname(filename);
	                        }
	                    }
	                }

	                // �г��� ������Ʈ ó��
	                if (unick != null && !unick.trim().isEmpty()) {
	                    String sqlUpdate = "UPDATE user SET unick = ? WHERE uno = ?";
	                    try (PreparedStatement psmtUpdate = conn.prepareStatement(sqlUpdate)) {
	                        psmtUpdate.setString(1, unick);
	                        psmtUpdate.setInt(2, Integer.parseInt(uno));

	                        int nickUpdateCount = psmtUpdate.executeUpdate();
	                        System.out.println("�г��� ������Ʈ SQL: " + sqlUpdate);
	                        System.out.println("�г��� ������Ʈ ���: " + nickUpdateCount);
	                        
	                        if (nickUpdateCount > 0) {
	                            user.setUnick(unick);
	                        }
	                    }
	                }
	                
	                // ���ǿ� ����� ����� ���� ����
	                session.setAttribute("loginUser", user);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public void findId(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/findId.jsp").forward(request, response);
	}
	
	public void findIdOk(HttpServletRequest request
			, HttpServletResponse response) throws IOException {
		/*
		 * Connection conn = null; //DB ���� PreparedStatement psmt = null; //SQL ��� �� ����.
		 * ������ �� ����! ResultSet rs = null; //��ȸ ����� ����
		 * 
		 * String email = request.getParameter("uemail"); //try ���� try{ conn =
		 * DBConn.conn();
		 * 
		 * String sql = "select uid from user where uemail=?"; psmt =
		 * conn.prepareStatement(sql); psmt.setString(1, email);
		 * 
		 * rs = psmt.executeQuery(); if(rs.next()){
		 * System.out.println("findIdOk rs : rs.next() �����"); String uid =
		 * rs.getString("uid"); request.setAttribute("uid",uid);
		 * 
		 * response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
		 * response.getWriter(); out.print("success"); out.flush(); out.close(); }else {
		 * response.setContentType("text/html;charset=UTF-8"); PrintWriter out =
		 * response.getWriter(); out.print("error"); out.flush(); out.close(); }
		 * }catch(Exception e){ e.printStackTrace(); PrintWriter out =
		 * response.getWriter(); out.print("error"); out.flush(); out.close(); }finally{
		 * try { DBConn.close(rs, psmt, conn); }catch(Exception e) {
		 * e.printStackTrace(); } }
		 */
	}
	
	public void findIdResult(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;			//DB ����
		PreparedStatement psmt = null;	//SQL ��� �� ����. ������ �� ����!
		ResultSet rs = null;			//��ȸ ����� ����
		List<String> idList = new ArrayList<>();
		String email = request.getParameter("uemail");
		//try ����
		try{
			conn = DBConn.conn();
			
			String sql = "select uid from user where uemail=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			
			rs = psmt.executeQuery();
			while(rs.next()){
				System.out.println("findIdOk rs : rs.next() �����");
				idList.add(rs.getString("uid"));
			}
			request.setAttribute("idList",idList);
			
			request.getRequestDispatcher("/WEB-INF/user/findIdResult.jsp").forward(request, response);
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
	
	public void findPw(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/findPw.jsp").forward(request, response);
	}
	public void pwChange(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/pwChange.jsp").forward(request, response);
	}
}