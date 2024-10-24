package sns.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComplaintBoardController {
	public ComplaintBoardController(HttpServletRequest request
			, HttpServletResponse response
			, String[] comments) throws ServletException, IOException {
		
		if(comments[comments.length-1].equals("blackList.do")) {
			blackList(request,response);
		}else if(comments[comments.length-1].equals("complainList.do")) {
			complainList(request,response);
		}
	}
	
	public void blackList(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/admin/blackList.jsp").forward(request, response);
	}
	
	public void complainList(HttpServletRequest request
			, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/admin/complainList.jsp").forward(request, response);
	}
}
