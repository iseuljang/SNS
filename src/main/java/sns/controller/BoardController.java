package sns.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sns.util.DBConn;
import sns.vo.BoardVO;


public class BoardController {
	public BoardController(HttpServletRequest request
			, HttpServletResponse response
			, String[] comments) throws ServletException, IOException {
		
		if(comments[comments.length-1].equals("write.do")) {
			write(request,response);
		}
	}
	
	public void write(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/board/write.jsp").forward(request, response);
	}
}
