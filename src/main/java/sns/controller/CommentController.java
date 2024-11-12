package sns.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import sns.util.DBConn;
import sns.vo.UserVO;

public class CommentController {
	public CommentController(HttpServletRequest request, HttpServletResponse response, String[] comments)
			throws ServletException, IOException {

		if (comments[comments.length - 1].equals("view.do")) {
			commentWrite(request, response);
		}else if (comments[comments.length - 1].equals("cmodi.do")) {
			commentModify(request, response);
		}else if (comments[comments.length - 1].equals("cdele.do")) {
			commentDelete(request, response);
		}
	}

	public void commentDelete(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		String sql ="";
		
		try {
			conn = DBConn.conn();
			sql =" UPDATE comments SET state = 'D' "
					+ " WHERE cno =? ";
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, cno);
			int result = psmt.executeUpdate();
			if(result > 0 ) {
				
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();  
				out.print("success");  
				out.flush();
				out.close();   
			}
		}
		catch(Exception e){
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

	public void commentModify(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		System.out.println("commentsModify ȣ��");
		
		request.setCharacterEncoding("UTF-8");
		
		// �� ������ ��������
		int cno = Integer.parseInt(request.getParameter("cno"));
		String content = request.getParameter("content");
		
		// DB����
				Connection conn = null;
				PreparedStatement psmt = null;
				ResultSet rs = null;
				
				try{
					conn = DBConn.conn();
					String sql = " UPDATE comments SET content = ? WHERE cno= ? ";
					
					psmt = conn.prepareStatement(sql);
					psmt.setString(1,content);
			 		psmt.setInt(2,cno);
			 		psmt.executeUpdate();
			 		
			 		JSONObject json = new JSONObject();

					try {
						sql = " select c.cno, c.rdate, u.unick, u.fname, u.pname from comments as c join user as u on c.uno = u.uno where cno = ?; ";

						psmt = conn.prepareStatement(sql);
						psmt.setInt(1, cno);

						rs = psmt.executeQuery();

						if (rs.next()) {
							// JSON ��ü ����
							json.put("cno", cno);
							json.put("name", rs.getString("unick"));
							json.put("content", content);
							json.put("rdate", rs.getString("rdate"));
							json.put("fname", rs.getString("fname"));
							json.put("pname", rs.getString("pname"));
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

					// JSON ���� ����
					PrintWriter out = response.getWriter();
					out.print(json.toString());
					out.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
	
	public void commentWrite(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("commentWrite ȣ��");

		// ������ Content-Type�� JSON���� ����
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		UserVO loginUser = (UserVO)session.getAttribute("loginUser");
		// �� ������ ��������
		int bno = Integer.parseInt(request.getParameter("bno"));
		int uno = Integer.parseInt(loginUser.getUno());
		String content = request.getParameter("content");

		// DB����
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBConn.conn();

			String sql = " insert into comments (bno,uno,content)value(?,?,?); ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bno);
			psmt.setInt(2, uno);
			psmt.setString(3, content);

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��ݵ�ϵ� ��� ��ȣ�� db���� �޾ƿ´�
		int cno = 0;

		try {
			String sql = " select last_insert_id() as cno; ";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			if (rs.next()) {
				cno = rs.getInt("cno");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("cno : " + cno);
		
		
		// ����� ��ϵǸ�, ��� �˸��� �˶� ���̺� �ۼ��մϴ�
		try {

			conn = DBConn.conn();
			// uno�� �˸��� ���� ���
			// no�� ��۹�ȣ
			// bno�� �˶� ��� �������� sql���� cno�� ã��
			String sql = " insert into alram ( uno, type, no ) value ((select uno from board where bno = ?), 'R', ?); ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bno);
			psmt.setInt(2, cno);

			psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ��۹�ȣ�� ��� ������ db���� �޾ƿ´�
		// ����� �� ��� ��ȣ�� ������ �̹��� ���ϸ�� ���������� �����´�

		JSONObject json = new JSONObject();

		try {
			String sql = " select c.cno, c.rdate, u.unick, u.fname, u.pname from comments as c join user as u on c.uno = u.uno where cno = ?; ";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, cno);

			rs = psmt.executeQuery();

			if (rs.next()) {
				// JSON ��ü ����
				json.put("cno", cno);
				json.put("name", rs.getString("unick"));
				json.put("content", content);
				json.put("rdate", rs.getString("rdate"));
				json.put("fname", rs.getString("fname"));
				json.put("pname", rs.getString("pname"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// JSON ���� ����
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
	}
}