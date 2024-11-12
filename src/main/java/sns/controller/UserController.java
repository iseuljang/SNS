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

import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import sns.util.DBConn;
import sns.util.Sendmail;
import sns.vo.BoardVO;
import sns.vo.FollowVO;
import sns.vo.UserVO;

public class UserController {
	public UserController(HttpServletRequest request, HttpServletResponse response, String[] comments)
			throws ServletException, IOException {

		if (comments[comments.length - 1].equals("login.do")) {
			if (request.getMethod().equals("GET")) {
				login(request, response);
			} else if (request.getMethod().equals("POST")) {
				loginOk(request, response);
			}
		} else if (comments[comments.length - 1].equals("logout.do")) {
			logout(request, response);
		} else if (comments[comments.length - 1].equals("mypage.do")) {
			mypage(request, response);
		} else if (comments[comments.length - 1].equals("join.do")) {
			if (request.getMethod().equals("GET")) {
				join(request, response);
			} else if (request.getMethod().equals("POST")) {
				joinOk(request, response);
			}
		} else if (comments[comments.length - 1].equals("idCheck.do")) {
			idCheck(request, response);
		} else if (comments[comments.length - 1].equals("nickCheck.do")) {
			nickCheck(request, response);
		} else if (comments[comments.length - 1].equals("sendmail.do")) {
			sendmail(request, response);
		} else if (comments[comments.length - 1].equals("getcode.do")) {
			getcode(request, response);
		} else if (comments[comments.length - 1].equals("profileModify.do")) {
			if (request.getMethod().equals("GET")) {
				profileModify(request, response);
			} else if (request.getMethod().equals("POST")) {
				profileModifyOk(request, response);
			}
		} else if (comments[comments.length - 1].equals("findId.do")) {
			findId(request, response);
		} else if (comments[comments.length - 1].equals("findIdResult.do")) {
			if (request.getMethod().equals("POST")) {
				findIdResult(request, response);
			}
		} else if (comments[comments.length - 1].equals("findPw.do")) {
			if (request.getMethod().equals("GET")) {
				findPw(request, response);
			} else if (request.getMethod().equals("POST")) {
				findPwOk(request, response);
			}
		} else if (comments[comments.length - 1].equals("pwChange.do")) {
			if (request.getMethod().equals("POST")) {
				pwChangeOk(request, response);
			}
		} else if(comments[comments.length-1].equals("alramCount.do")) {
			if(request.getMethod().equals("GET")) {
				alramCount(request,response);
			}
		} else if(comments[comments.length-1].equals("alramList.do")) {
			if(request.getMethod().equals("GET")) {
				alramList(request,response);
			}
		} else if(comments[comments.length-1].equals("updateState.do")) {
			if(request.getMethod().equals("GET")) {
				updateState(request,response);
			}
		}else if (comments[comments.length - 1].equals("followAddPage.do")) {
			followAddPage(request, response);
		}
	}

	
	public void followAddPage(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		// �� ������ ��������
		String uno = loginUser.getUno();
		int tuno = Integer.parseInt(request.getParameter("tuno"));

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";


		try {
		    conn = DBConn.conn();

		    sql = " SELECT count(*) as cnt FROM sns.follow where uno=? and tuno=? ";

		    psmt = conn.prepareStatement(sql);
		   
			psmt.setString(1, uno);
		    psmt.setInt(2, tuno);

		    rs = psmt.executeQuery();
		    
		    int cnt = 0;
		    if(rs.next()) cnt = rs.getInt("cnt");

		    if (cnt>0) {
		        // ��õ�� �̹� �����ϸ� delete
		    	sql = "delete from follow where uno = ? and tuno = ?";
		        psmt = conn.prepareStatement(sql);
		        psmt.setString(1, uno);
		        psmt.setInt(2, tuno);
		        psmt.executeUpdate();
		        
		    	sql = "delete from alram where no = ? and uno = ?";
		        psmt = conn.prepareStatement(sql);
		        psmt.setString(1, uno);
		        psmt.setInt(2, tuno);
		        psmt.executeUpdate();
		        
		    } else {
		        // ��õ�� ������ insert
		        sql = "insert into follow (uno, tuno) values (?, ?)";
		        System.out.println(sql);
		        psmt = conn.prepareStatement(sql);
		        psmt.setString(1, uno);
		        psmt.setInt(2, tuno);
		        System.out.println(psmt.executeUpdate());
		        
				/*
				 * sql = "insert into alram (uno, tuno) values (?, ?)"; psmt =
				 * conn.prepareStatement(sql); psmt.setString(1, uno); psmt.setInt(2, tuno);
				 * psmt.executeUpdate();
				 */
		        //�ȷο����̺� ���ε� �������� pk�� ������
		        sql = "insert into alram (uno, no, type) values (?, ?, ?)";
		        System.out.println(sql);
		        psmt = conn.prepareStatement(sql);
		        psmt.setInt(1, tuno);
		        psmt.setString(2, uno);
		        psmt.setString(3, "F");
		        System.out.println(psmt.executeUpdate());
		        
		    }
		    
		    
		    response.setCharacterEncoding("utf-8");
		    response.setContentType("text/html;");
		    response.getWriter().append("success").flush();

		} catch (Exception e) {
		    e.printStackTrace();
		} finally {
		    try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
	}

	public void loginOk(HttpServletRequest request, HttpServletResponse response) {
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");

		Connection conn = null; // DB ����
		PreparedStatement psmt = null; // SQL ��� �� ����. ������ �� ����!
		ResultSet rs = null; // ��ȸ ����� ����

		// try ����
		try {
			conn = DBConn.conn();

			String sql = "select * from user where uid = ? and upw = md5(?) and ustate='E' ";
			psmt = conn.prepareStatement(sql);
			System.out.println(sql);
			psmt.setString(1, uid);
			psmt.setString(2, upw);

			rs = psmt.executeQuery();

			if (rs.next()) {
				UserVO loginUser = new UserVO();
				loginUser.setUno(rs.getString("uno"));
				loginUser.setUnick(rs.getString("unick"));
				loginUser.setUauthor(rs.getString("uauthor"));
				loginUser.setPname(rs.getString("pname"));

				// �α��� ���� session�� ����
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				/* response.sendRedirect(request.getContextPath()); */

				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("success");
				out.flush();
				out.close();
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("error");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath());
	}

	public void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO loginUser = null;
		if(session.getAttribute("loginUser") != null && !session.getAttribute("loginUser").equals("")) {
			loginUser = (UserVO)session.getAttribute("loginUser");
		}
		String uno = request.getParameter("uno");
		String type = "bookmark";
		if(request.getParameter("type") != null && !request.getParameter("type").equals("")) {
			type = request.getParameter("type");
		}
		request.setCharacterEncoding("UTf-8");

		Connection conn = null; // DB ����
		PreparedStatement psmt = null; // SQL ��� �� ����. ������ �� ����!
		ResultSet rs = null; // ��ȸ ����� ����

		PreparedStatement psmtFollow = null;
		ResultSet rsFollow = null;
		// try ����
		try {
			conn = DBConn.conn();
			String sql = "";
			if(loginUser != null) {
				sql = "select *,(select count(*) from follow f where f.uno = ? and tuno = ? ) as isfollow from user where uno=?"; 
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, loginUser.getUno()); 
				psmt.setString(2, uno);
				psmt.setString(3, uno);
			}else {
				sql = "select * from user where uno=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, uno); 
			}
			rs = psmt.executeQuery();
			String isfollow="";
			// ������ �κ�
			if(rs.next()) {
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
				if(loginUser != null) {
					isfollow = rs.getString("isfollow");
					System.out.println("isfollow : " + isfollow);
					request.setAttribute("isfollow", isfollow);
				}
				request.setAttribute("user", user);
			}
			if(loginUser != null) {
				// ���ǿ� �ִ� uno�� ��ġ�ϴ� �ȷο� ���̺��� uno�� ī��Ʈ�� ��ȸ�Ѵ�
				String sqlFollow = " select count(*) as cnt from follow where tuno = ? ";
	
				psmtFollow = conn.prepareStatement(sqlFollow);
				psmtFollow.setInt(1, Integer.parseInt(uno));
	
				rsFollow = psmtFollow.executeQuery();
	
				int cnt = 0;
				if (rsFollow.next()) {
					cnt = rsFollow.getInt("cnt");
				}
				request.setAttribute("fcnt", cnt);
			}
			if(type.equals("bookmark")) {
				myPageBookmark(request, response);
			}else {
				myPageWrite(request, response);
			}
			
			request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/join.jsp").forward(request, response);
	}

	public void joinOk(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		/* String uploadPath = request.getServletContext().getRealPath("/upload"); */
		/*
		 * String uploadPath =
		 * "C:\\DEV\\GIT\\first-SNS\\sns\\src\\main\\webapp\\upload";
		 */
		String uploadPath = request.getServletContext().getRealPath("/upload");
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
		String filename = null; // ���� ���� �̸�
		String phyname = null; // ������ ����� ���� �̸�

		if (files.hasMoreElements()) {
			String fileid = (String) files.nextElement();
			filename = multi.getFilesystemName(fileid); // ���� ���� �̸� ��������

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
		if (phyname == null)
			phyname = "";
		if (filename == null)
			filename = "";

		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			conn = DBConn.conn();

			String sql = "insert into user (uid,upw,unick,uemail,pname,fname) " + " values (?,md5(?),?,?,?,?) ";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uid);
			psmt.setString(2, upw);
			psmt.setString(3, unick);
			psmt.setString(4, uemail);
			psmt.setString(5, phyname); // ���� ���� �̸� (������ ����� ���� �̸�)
			psmt.setString(6, filename); // ���� ���� �̸� (����ڰ� ���ε��� ���� �̸�)

			psmt.executeUpdate();

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("success");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("error");
			out.flush();
			out.close();
		} finally {
			try {
				DBConn.close(psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void idCheck(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String uid = request.getParameter("uid");

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.conn();

			String sql = "select uid from user " + " where uid=? ";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uid);

			rs = psmt.executeQuery();

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter(); // Ŭ���̾�Ʈ�� ������ ���� �غ�
			if (rs.next()) {
				out.print("01"); // �ߺ��� ���̵�
			} else {
				out.print("00"); // ��� ������ ���̵�
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void nickCheck(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String unick = request.getParameter("unick");

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = DBConn.conn();

			String sql = "select unick from user " + " where unick=? ";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, unick);

			rs = psmt.executeQuery();

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter(); // Ŭ���̾�Ʈ�� ������ ���� �غ�
			if (rs.next()) {
				out.print("01");
			} else {
				out.print("02");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void sendmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");

		String email = request.getParameter("uemail");

		if (email == null || email.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("�ùٸ� �����ּҰ� �ƴմϴ�.");
			return;
		}
		// -------------------- �̸��� �ּҷ� ������ȣ ���� -----------------------
		// �̸��� ��ü ����
		Sendmail sender = new Sendmail();
		// �����ڵ带 ��´�.
		String code = sender.AuthCode(6);
		sender.setFrom("gyr0204@naver.com");
		sender.setAccount("gyr0204", "zxcv1234!!");

		// �޴��̸� ������ �Է��� �̸��� �ּҷ� ����
		sender.setTo(email);

		sender.setMail("�̸��� �����ڵ��Դϴ�.", "�����ڵ� : " + code);

		if (sender.sendMail() == true) {
			// �ش� �ּҷ� ���� ������ �������� ���
			HttpSession session = request.getSession();
			session.setAttribute("code", code);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("������ �߼��Ͽ����ϴ�.");
		} else {
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

	public void profileModify(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");
		String uno = loginUser.getUno();
		System.out.println("profileModify uno : " + uno);

		Connection conn = null; // DB ����
		PreparedStatement psmt = null; // SQL ��� �� ����. ������ �� ����!
		ResultSet rs = null; // ��ȸ ����� ����

		// try ����
		try {
			conn = DBConn.conn();

			String sql = "select * from user where uno=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, uno);

			rs = psmt.executeQuery();
			if (rs.next()) {
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

				request.setAttribute("userModify", userModify);
				request.getRequestDispatcher("/WEB-INF/user/profileModify.jsp").forward(request, response);
			} else {
				// ȸ����ȸ ������ ���
				response.sendRedirect(request.getContextPath() + "/user/login.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void profileModifyOk(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		/*
		 * String uploadPath =
		 * "C:\\Users\\DEV\\Desktop\\JangAWS\\01.java\\workspace\\sns\\src\\main\\webapp\\upload";
		 */
		/* String uploadPath = "C:\\DEV\\GIT\\ -SNS\\sns\\src\\main\\webapp\\upload"; */
		String uploadPath = request.getServletContext().getRealPath("/upload");
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

		try (Connection conn = DBConn.conn(); PreparedStatement psmt = conn.prepareStatement(sql)) {

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
					/*
					 * request.getRequestDispatcher("/WEB-INF/user/mypage.jsp").forward(request,
					 * response);
					 */
					response.sendRedirect(request.getContextPath() + "/user/mypage.do?uno=" + uno);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/findId.jsp").forward(request, response);
	}

	public void findIdResult(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null; // DB ����
		PreparedStatement psmt = null; // SQL ��� �� ����. ������ �� ����!
		ResultSet rs = null; // ��ȸ ����� ����
		List<String> idList = new ArrayList<>();
		String email = request.getParameter("uemail");
		// try ����
		try {
			conn = DBConn.conn();

			String sql = "select uid from user where uemail=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);

			rs = psmt.executeQuery();
			while (rs.next()) {
				System.out.println("findIdOk rs : rs.next() �����");
				idList.add(rs.getString("uid"));
				request.setAttribute("idList", idList);
			}

			request.getRequestDispatcher("/WEB-INF/user/findIdResult.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void findPw(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/findPw.jsp").forward(request, response);
	}

	public void findPwOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null; // DB ����
		PreparedStatement psmt = null; // SQL ��� �� ����. ������ �� ����!
		ResultSet rs = null; // ��ȸ ����� ����

		String uid = request.getParameter("uid");
		String email = request.getParameter("uemail");

		System.out.println("findPwOk uid:" + uid + ", email : " + email);

		// try ����
		try {
			conn = DBConn.conn();

			String sql = "select * from user where uemail=? and uid=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, email);
			psmt.setString(2, uid);

			rs = psmt.executeQuery();

			request.setAttribute("uid", uid);
			request.setAttribute("uemail", email);
			request.getRequestDispatcher("/WEB-INF/user/pwChange.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void pwChangeOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null; // DB ����
		PreparedStatement psmt = null; // SQL ��� �� ����. ������ �� ����!
		ResultSet rs = null; // ��ȸ ����� ����

		String uid = request.getParameter("uid");
		String email = request.getParameter("uemail");
		String upw = request.getParameter("upw");
		System.out.println("pwChangeOk");
		System.out.println("pwChangeOk uid : " + uid + ", email:" + email + ", upw:" + upw);
		// try ����
		try {
			conn = DBConn.conn();

			String sql = "update user set upw=md5(?) where uemail=? and uid=?";
			System.out.println("pwUpdate sql:" + sql);
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, upw);
			psmt.setString(2, email);
			psmt.setString(3, uid);

			int result = psmt.executeUpdate();

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter(); // Ŭ���̾�Ʈ�� ������ ���� �غ�
			if (result > 0) {
				out.print("success");
			} else {
				out.print("error");
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void alramCount(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		// �Ķ��Ÿ�� �Ѿ�� uno�� �޽��ϴ�
		request.setCharacterEncoding("UTF-8");
		String uno = request.getParameter("uno");
		System.out.println("alramCount() : uno :" + uno);
		
		// DB ���� ����
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		// SB����� �ۼ� ���� 
		try {
			// DB����
			conn = DBConn.conn();
			// SQL �ۼ�
			// �ش� uno���� �� �˸��� ������ ���ϴ�
			
			String sql = " select count(*) as count from alram where uno = ? and state = 'N' ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,Integer.parseInt(uno));
			rs = psmt.executeQuery();
			
			int result = 0;
			
			if(rs.next()){
				//  json ��ü�� ������ �ֱ�
				result = rs.getInt("count");
			}
			
			// ������ ��ȯ�մϴ�
			PrintWriter out = response.getWriter();
			out.print(result);
			
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void alramList(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		
		// �Ķ��Ÿ�� �Ѿ�� uno�� �޽��ϴ�
		request.setCharacterEncoding("UTF-8");
		String uno = request.getParameter("uno");
		System.out.println("alramCount() : uno :" + uno);
		
		// DB ���� ����
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		// SB����� �ۼ� ���� 
		try {
			// DB����
			conn = DBConn.conn();
			// SQL �ۼ�
			// �ش� uno���� �� �޼��� ����Ʈ�� ��û�մϴ�
			
			String sql  = " select a.*,( select u.unick from user u where u.uno= a.uno) as tuno, "
						+" 		(select u.unick from user u where u.uno = f.uno) as funo , 0 as bno"
						+" 	from alram a, follow f "
						+"    where a.no = f.fno"
						+"      and a.uno = f.tuno "
						+"      and a.type='F'"
						+" 	 and a.state = 'N' "
						+" 	 and a.uno = ?"
						+" union all"
						+"  select  a.*,( select u.unick from user u where u.uno= a.uno) as tuno, "
						+" 		(select u.unick from user u where u.uno = c.uno) as funo, b.bno "
						+"   from alram a, comments c, board b"
						+"  where a.no = c.cno"
						+"    and c.bno = b.bno "
						+"    and a.uno = b.uno "
						+"    and a.type='R'"
						+"    and a.state = 'N' "
						+"    and a.uno = ?"
						+" union all"
						+"   select a.*,( select u.unick from user u where u.uno= a.uno) as tuno, "
						+" 	 (select u.unick from user u where u.uno = l.uno) as funo, b.bno "
						+"   from alram a, love l, board b"
						+"  where a.no = l.lno"
						+"    and l.bno = b.bno "
						+"    and a.uno = b.uno  "
						+"    and a.type='L'"
						+"    and a.state='N'"
						+"    and a.uno = ?"
						+" union all"
						+"  select a.*,( select u.unick from user u where u.uno= a.uno) as tuno, "
						+" 	 (select u.unick from user u where u.uno = cp.uno) as funo, b.bno "
						+"   from alram a, complaint_board cp, board b"
						+"  where a.no = cp.cpno"
						+"    and cp.bno= b.bno"
						+"    and a.uno = b.uno  "
						+"    and a.type='C'"
						+"    and a.state='N'"
						+"    and a.uno = ?"
						+" order by rdate desc"; 
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,Integer.parseInt(uno));
			psmt.setInt(2,Integer.parseInt(uno));
			psmt.setInt(3,Integer.parseInt(uno));
			psmt.setInt(4,Integer.parseInt(uno));
			rs = psmt.executeQuery();
			
			List<JSONObject> list = new ArrayList<JSONObject>();
			
			// ����Ʈ ����
			while(rs.next()){
				//	json ��ü ����
				JSONObject json = new JSONObject();
				//  json ��ü�� ������ �ֱ�
				json.put("alno", rs.getInt("alno"));		// �˶� ��ȣ
				json.put("uno", rs.getInt("uno"));			// �˶��� ���� ���� ��ȣ
				json.put("rdate", rs.getString("rdate"));
				json.put("state", rs.getString("state"));	// ���� ����
				json.put("type", rs.getString("type"));		// �˶� ����
				json.put("tuno", rs.getString("tuno"));		// �ȷο� ���� ���
				json.put("funo", rs.getString("funo"));		// �ȷο� ���ѻ��
				json.put("no", rs.getInt("no"));			// ��� �ۿ��� �ȷο� ��û�� �ߴ°�
				json.put("bno", rs.getInt("bno"));			// ��� �ۿ��� �ȷο� ��û�� �ߴ°�
				//  ����Ʈ�� json ��ü �ֱ�
				list.add(json);
			//}
			}
			
			// ������ Content-Type�� JSON���� ����
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			// ����Ʈ�� ���ڿ��� �ٲ㼭 ��ȯ
			PrintWriter out = response.getWriter();
			out.print(list.toString());
			out.flush();
			
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}
	
	public void updateState(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		// �Ķ��Ÿ�� �Ѿ�� uno�� �޽��ϴ�
		request.setCharacterEncoding("UTF-8");
		String alno = request.getParameter("alno");
		System.out.println("updateState() : alno :" + alno);
		
		// DB ���� ����
		Connection conn = null;
		PreparedStatement psmt = null;
		
		// SB����� �ۼ� ���� 
		try {
			// DB����
			conn = DBConn.conn();
			// SQL �ۼ�
			
			// ���� alno�� �˶��� ���¸� �����ϴ� sql���� �ۼ�
			
			String sql = " update alram "
					   + "    set state = 'Y' "
					   + "  where alno = ? ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,Integer.parseInt(alno));
			
			int result = psmt.executeUpdate();
					
			PrintWriter out = response.getWriter();

			if(result > 0){
				//���°� ������Ʈ ��.
				out.print("ok");
			}else {
				//����� �����Ͱ� ����.
				out.print("fail");
			}
			

			
		}catch(Exception e ){
			e.printStackTrace();
		}
		finally {
			try {
				DBConn.close(psmt, conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void myPageWrite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String Struno = request.getParameter("uno");
		if (Struno == null) {
			return;
		}
		int uno = Integer.parseInt(Struno);
		System.out.println("uno ====================================" + uno);

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";

		PreparedStatement psmtFollow = null;
		ResultSet rsFollow = null;
		
		
		try {
			conn = DBConn.conn();
			sql = " SELECT *,a.pname,a.fname "
					+ "  FROM board b"
					+ " INNER JOIN user u"
					+ "	   ON b.uno = u.uno"
					+ " INNER JOIN attach a"
					+ "	   ON b.bno = a.bno"
					+ " where u.uno = ? and b.state='E'";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, uno);
			rs = psmt.executeQuery();
			ArrayList<BoardVO> board = new ArrayList<>();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setUno(rs.getInt("uno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRdate(rs.getString("rdate"));
				vo.setState(rs.getString("state"));
				vo.setUnick(rs.getString("unick"));
				vo.setPname(rs.getString("a.pname"));
				vo.setFname(rs.getString("a.fname"));
				board.add(vo);
			}
			request.setAttribute("board", board);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void myPageBookmark(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String Struno = request.getParameter("uno");
		if (Struno == null) {
			return;
		}
		int uno = Integer.parseInt(Struno);
		System.out.println("uno ====================================" + uno);

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			conn = DBConn.conn();
			sql = " SELECT *,u.uno as uuno"
				+ "  FROM board b"
				+ " INNER JOIN love l"
				+ "    ON b.bno = l.bno"
				+ " INNER JOIN user u"
				+ "	   ON l.uno = u.uno"
				+ " INNER JOIN attach a"
				+ "	   ON b.bno = a.bno"
				+ " where u.uno = ?  and b.state='E' ";
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, uno);
			rs = psmt.executeQuery();
			ArrayList<BoardVO> board = new ArrayList<>();
			while (rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setUno(rs.getInt("uno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRdate(rs.getString("rdate"));
				vo.setState(rs.getString("state"));
				vo.setUnick(rs.getString("unick"));
				vo.setPname(rs.getString("a.pname"));
				vo.setFname(rs.getString("a.fname"));
				board.add(vo);
			}
			
			request.setAttribute("board", board);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConn.close(rs, psmt, conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}