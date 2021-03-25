package com.page.FrontController;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.page.Command.*;

/**
 * Servlet implementation class QfrontController
 */
@WebServlet("*.qdo")
public class QfrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QfrontController() {
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
		String viewPage = null; // � view�� ������ ���ΰ�?
		BCommand command = null; // ��� ����� ���� ���ΰ�?
		
		String uri = request.getRequestURI();// ~.do������ ��θ� ��� ��ȯ�Ѵ�.
		String conPath = request.getContextPath(); // /*.do �ձ����� ��ȯ�Ѵ�.
		String com = uri.substring(conPath.length()); // �� conPath�� �״�� ���� �ʴ°�?
		System.out.println(com);
		
		if(com.equals("/qanda_write_view.qdo")) {
			viewPage = "qanda_write_view.jsp";
		}else if(com.equals("/qanda_writeAction.qdo")) {
			command = new QWriteCommand();
			command.execute(request,response);
			viewPage = "qanda_view.qdo";
		}else if(com.equals("/qanda_view.qdo")) {
			command = new QListCommand();
			command.execute(request,response);
			viewPage = "qanda_view.jsp";
		}else if(com.equals("/qanda_content_view.qdo")) {
			command = new QContentCommand();
			command.execute(request,response);
			viewPage = "qanda_content_view.jsp";
		}else if(com.equals("/qanda_modify_view.qdo")) {
			viewPage = "qanda_modify_view.jsp";
		}else if(com.equals("/qanda_modify.qdo")) {
			command = new QModifyCommand();
			command.execute(request,response);
			viewPage = "qanda_view.qdo";
		}else if(com.equals("/qanda_delete.qdo")) {
			command = new QDeleteCommand();
			command.execute(request,response); 
			viewPage = "qanda_view.qdo";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request,response);
	}
}
