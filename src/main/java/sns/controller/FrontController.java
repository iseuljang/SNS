package sns.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("frontcontroller ���� url:"+request.getRequestURI());
		
		//1. ��û uri ������ ������ �´�
		String uri = request.getRequestURI();
		
		//2. ������Ʈ ��θ� ������ �´�.
		String contextPath = request.getContextPath();
		//3. ��û uri���� �ʿ���� ������Ʈ ��θ� ������ uri�� ������ �´�
		String comment = uri.substring(contextPath.length()+1);
		/*
		/frontControllerPJT/sample/main.do����
		/frontControllerPJT/ �κ��� �߸�
		 */
		System.out.println(comment);
		
		//4. 3������ ã�� ��û ��ο��� /�� �������� ���ڿ��� �ڸ���.
		String[] comments = comment.split("/");
		System.out.println("comments[0] : "+comments[0]);
		
		//5. ������� ���ڿ� �迭�� ù��° ���ڿ��� � ��Ʈ�ѷ��� ����� ��û�ؾ����� �����Ѵ�.
		if(comments[0].equals("board")) {
			BoardController board = new BoardController(request,response,comments);
		}else if(comments[0].equals("user")) {
			UserController user = new UserController(request,response,comments);
		}else if(comments[0].equals("admin")) {
			AdminController admin = new AdminController(request,response,comments);
			//SampleController ���� ó�� ����
		}else if(comments[0].equals("reply")) {
			CommentController reply = new CommentController(request,response,comments);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test post!!"+request.getParameter("title"));
		doGet(request, response);
	}
}