package sns.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import sns.util.DBConn;
import sns.vo.BoardVO;
import sns.vo.UserVO;


public class BoardController {
	public BoardController(HttpServletRequest request
			, HttpServletResponse response
			, String[] comments) throws ServletException, IOException {
		
		if(comments[comments.length-1].equals("write.do")) {
			if(request.getMethod().equals("GET")) {
				write(request,response);
			}else if (request.getMethod().equals("POST")) {
				writeOk(request,response);
			}
		
		}else if (comments[comments.length-1].equals("view.do")) {
			view(request,response);
		}else if (comments[comments.length-1].equals("loadReco.do")) {
			loadReco(request,response);
		}else if (comments[comments.length-1].equals("recoAdd.do")) {
			recoAdd(request,response);
		}
		
	}
	
	public void write(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/board/write.jsp").forward(request, response);
	}
	
	public void writeOk(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		/* String uploadPath = request.getServletContext().getRealPath("/upload"); */
		/*
		 * String uploadPath =
		 * "C:\\Users\\DEV\\Desktop\\JangAWS\\01.java\\workspace\\sns\\src\\main\\webapp\\upload";
		 */
		String uploadPath = "C:\\DEV\\GIT\\first-SNS\\sns\\src\\main\\webapp\\upload";
		System.out.println("������ ���ε� ���� ��� : " + uploadPath);
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		int uno = Integer.parseInt(loginUser.getUno()); 
		
		int size = 10 * 1024 * 1024;
		MultipartRequest multi;
		try {
		    // ���� ���ε� ó��
		    multi = new MultipartRequest(request, uploadPath, size, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.getStackTrace();
		    // ���� ���ε� ���� �� ó��
		    response.sendRedirect(request.getContextPath());
		    return;
		}

		// input Ÿ�Կ� ������ ������ �����ϴ� ���  
		Enumeration files = multi.getFileNames();
		/*
			// ������ input Ÿ�Կ� �� ���� �����ϴ� ��� 
			multi.getFilesystemName("attach");
		*/
		String filename = multi.getFilesystemName("attach");  // ���� ���� �̸�
		String phyname = null;   // ������ ����� ���� �̸�
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		
		if (files.hasMoreElements()) {
		    String fileid = (String) files.nextElement();
		    filename = multi.getFilesystemName(fileid);  // ���� ���� �̸� ��������

		    if (filename != null) {
		        System.out.println("���ε�� ���� �̸�: " + filename);
		        System.out.println("title:"+title);
		        System.out.println("content:"+content);
		        
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
	
		if (phyname == null) phyname = "";
		if (filename == null) filename = "";
		
		Connection conn = null;			
		PreparedStatement psmt = null;
		
		Connection connAttach = null;			
		PreparedStatement psmtAttach = null;
		int resultAttach =0;
		try {
			conn = DBConn.conn();
			String sql = " INSERT INTO board (uno,title,content)"
					+ " VALUES(?,?,?)";
			// sql�� ���, ���� Ű�� ����
			psmt =conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			psmt.setInt(1, uno);
			psmt.setString(2, title);
			psmt.setString(3, content);
			int result = psmt.executeUpdate();
			if(result>0) {
				//������Ʈ�� �����ϸ�, getGeneratedKeys()�� ���ؼ� Ű�� �޾ƿ�
				ResultSet rs = psmt.getGeneratedKeys();
				// �̶� getLong�� longŸ���̱� ������ longŸ���� ������ �����������
				long key = 0L;
				if (rs.next()) {
				    key = rs.getLong(1);
				}
				String sql1 = " SELECT LAST_INSERT_ID() as bno";
				sql1 = " INSERT INTO attach (bno, pname, fname) VALUES (?, ?, ?)";
				// select last_insert_id()�� �޾ƿͼ� , bno�� ���� 
				psmtAttach =conn.prepareStatement(sql1);
				psmtAttach.setLong(1, key);  // ���� �޾ƿ� Ű�� bno�� ������,
				psmtAttach.setString(2, phyname);  // �������� �̸� (������ ����� ������ �̸�)
				psmtAttach.setString(3, filename); // ���� ���� �̸� (����ڰ� ���ε��� ���� �̸�)
				resultAttach = psmtAttach.executeUpdate();
				
				System.out.println("result::"+result);
				System.out.println("result::"+ resultAttach);
				
				/*
				 // redirect�� ���ΰ�ħ �ϱ� ������ ,ajax�� ������ ��������
				    ���ΰ�ħ���� �ʰ� ������ �����͸� �ְ� �޴°� �� �� ����
				response.sendRedirect(request.getContextPath()+"index.jsp");*/
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();  
				out.print("success");  
				out.flush();
				out.close();      
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();   
            out.print("error");  
	        out.flush();
	        out.close();	
		}finally {
			try {
				DBConn.close(psmt, conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
	}
	
	public void view (HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bno = request.getParameter("bno");
		BoardVO vo = new BoardVO();
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			conn = DBConn.conn();
			String sql = " SELECT b.*,u.unick,a.pname,a.fname, "
					+"   (select count(*) from love where bno = b.bno) as cnt, "
					+"   (select pname from user where uno = b.uno) as upname "
					+"   FROM board b "
					+ " inner join user u " 
					+ " on b.uno = u.uno "
					+ " inner join attach a " 
					+ " on b.bno = a.bno "
					+"  WHERE b.bno=? and state='E' ";
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(bno));
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				 vo.setBno(rs.getInt("bno"));
				 vo.setUno(rs.getInt("uno"));
				 vo.setHit(rs.getInt("hit"));
				 vo.setTitle(rs.getString("title"));
				 vo.setContent(rs.getString("content"));
				 vo.setRdate(rs.getString("rdate"));
				 vo.setState(rs.getString("state"));
				 vo.setUnick(rs.getString("unick"));
				 vo.setPname(rs.getString("pname"));
				 vo.setFname(rs.getString("fname"));
				 vo.setReco(rs.getInt("cnt"));
				 vo.setUpname(rs.getString("upname"));
			}
			
			request.setAttribute("board", vo);
			request.getRequestDispatcher("/WEB-INF/board/view.jsp").forward(request, response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				DBConn.close(rs, psmt, conn);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void loadReco(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String bno = request.getParameter("bno");
		String uno = "0";
		String lState = "D";
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null){
			UserVO user = (UserVO)session.getAttribute("loginUser");
			uno = user.getUno();
		}
		System.out.println("���� bno ��: " + bno + ", uno : " + uno);
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
		    conn = DBConn.conn();

		    // ����ڰ� �� �Խù��� ��õ�ߴ��� Ȯ��
		    String checkReco = "select * from love where uno = ? and bno = ?";
		    System.out.println("sql checkReco: "+checkReco);
		    psmt = conn.prepareStatement(checkReco);
		    psmt.setString(1, uno);
		    psmt.setString(2, bno);
		    
		    rs = psmt.executeQuery();
		    
		    if(rs.next()) {
		    	lState = "E";
		    }
		    
		    request.setAttribute("lState", lState);
		    request.setAttribute("bno", bno);
			request.getRequestDispatcher("/WEB-INF/board/loadReco.jsp").forward(request, response);

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
	
	public void recoAdd(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		UserVO user = (UserVO)session.getAttribute("loginUser");
		String uno = user.getUno();
		String bno = request.getParameter("bno"); 

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "";


		try {
		    conn = DBConn.conn();

		    sql = "select lno from love where uno = ? and bno = ?";
		    psmt = conn.prepareStatement(sql);
		    psmt.setString(1, uno);
		    psmt.setString(2, bno);

		    rs = psmt.executeQuery();

		    if (rs.next()) {
		        // ��õ�� �̹� �����ϸ� delete
		    	sql = "delete from love where uno = ? and bno = ?";
		        psmt = conn.prepareStatement(sql);
		        psmt.setString(1, uno);
		        psmt.setString(2, bno);
		    } else {
		        // ��õ�� ������ insert
		        sql = "insert into love (uno, bno) values (?, ?)";
		        psmt = conn.prepareStatement(sql);
		        psmt.setString(1, uno);
		        psmt.setString(2, bno);
		    }
		    psmt.executeUpdate();

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
		
}