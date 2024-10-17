package sns.controller;

import java.io.File;
import java.io.IOException;
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
			
			String sql = "select * from user where uid=? and upw=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uid);
			psmt.setString(2, upw);
			
			rs = psmt.executeQuery();
			
			if(rs.next()){
				UserVO loginUser = new UserVO();
				loginUser.setUno(rs.getString("uno"));
				loginUser.setUnick(rs.getString("unick"));
				loginUser.setUauthor(rs.getString("uauthor"));
				loginUser.setUid(uid);
				
				//�α��� ���� session�� ����
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				response.sendRedirect(request.getContextPath());
			}else {
				//�α��� ������ ���
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
				user.setUpw(rs.getString("upw"));
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
		String uploadPath = request.getServletContext().getRealPath("/upload");
		System.out.println("������ ���ε� ���� ��� : " + uploadPath);

		int size = 10 * 1024 * 1024;
		MultipartRequest multi;
		try
		{
			multi = 
				new MultipartRequest(request,uploadPath,size,
					"UTF-8",new DefaultFileRenamePolicy());
		}catch(Exception e)
		{
			response.sendRedirect("join.do");
			return;
		}

		//���ε�� ���ϸ��� ��´�
		Enumeration files = multi.getFileNames();
		String filename = null;			//��������
		String phyname = null;			//�ٲ��̸�

		if (files.hasMoreElements()) {
		    String fileid = (String) files.nextElement();
		    filename = multi.getFilesystemName(fileid);

			if (filename != null) {
		        System.out.println("���ε�� ���� �̸�: " + filename);
		        phyname = UUID.randomUUID().toString(); // UUID ����
		        String srcName = uploadPath + "/" + filename;
		        String targetName = uploadPath + "/" + phyname;
		        
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
			if(phyname == null) phyname = "";
			psmt.setString(5, phyname);
			if(filename == null) filename = "";
			psmt.setString(6, filename);
			
			psmt.executeUpdate();
			response.sendRedirect(request.getContextPath()+"/login.do");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				DBConn.close(psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
