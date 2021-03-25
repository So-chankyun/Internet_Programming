package com.page.FrontController;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import com.page.Command.*;

/**
 * Servlet implementation class frontController
 */
@WebServlet("*.do")
public class BfrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BfrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		actionDo(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		String viewPage = null; // 어떤 view를 보여줄 것인가?
		BCommand command = null; // 어떠한 명령을 내릴 것인가?
		
		String uri = request.getRequestURI();// ~.do까지의 경로를 모두 반환한다.
		String conPath = request.getContextPath(); // /*.do 앞까지만 반환한다.
		String com = uri.substring(conPath.length()); // 왜 conPath를 그대로 쓰지 않는가?
		System.out.println(com);
		
		if(com.equals("/write_view.do")) {
			viewPage = "write_view.jsp";
		}else if(com.equals("/writeAction.do")) {
			command = new BWriteCommand();
			command.execute(request,response);
			viewPage = "freeboard_view.do";
		}else if(com.equals("/freeboard_view.do")) {
			command = new BListCommand();
			command.execute(request,response);
			viewPage = "freeboard_view.jsp";
		}else if(com.equals("/content_view.do")) {
			command = new BContentCommand();
			command.execute(request,response);
			viewPage = "content_view.jsp";
		}else if(com.equals("/modify_view.do")) {
			viewPage = "modify_view.jsp";
		}else if(com.equals("/modify.do")) {
			command = new BModifyCommand();
			command.execute(request,response);
			viewPage = "freeboard_view.do";
		}else if(com.equals("/delete.do")) {
			command = new BDeleteCommand();
			command.execute(request,response); 
			viewPage = "freeboard_view.do";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request,response);
	}
}
