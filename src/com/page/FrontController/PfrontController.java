package com.page.FrontController;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.page.Command.*;

/**
 * Servlet implementation class PfrontController
 */
@WebServlet("*.pdo")
public class PfrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PfrontController() {
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
		actionDo(request,response);
	}
	
	private void actionDo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String viewPage = null; // � view�� ������ ���ΰ�?
		BCommand command = null; // ��� ����� ���� ���ΰ�?
		
		String uri = request.getRequestURI();// ~.do������ ��θ� ��� ��ȯ�Ѵ�.
		String conPath = request.getContextPath(); // /*.do �ձ����� ��ȯ�Ѵ�.
		String com = uri.substring(conPath.length()); // �� conPath�� �״�� ���� �ʴ°�?
		System.out.println(com);
		
		if(com.equals("/enroll_product_view.pdo")) { // ��ǰ ��� �������� �̵�
			viewPage = "enroll_product_view.jsp";
		}else if(com.equals("/enroll_product.pdo")) { // ��ǰ ��� ����
			viewPage = "/PEP";
		}else if(com.equals("/product_detail_view.pdo")) { // ��ǰ ����
			command = new PProductDetail();
			command.execute(request,response);
			viewPage = "product_detail_view.jsp";
		}else if(com.equals("/main.pdo")) { // ��ǰ ����
			command = new PListCommand();
			command.execute(request,response);
			viewPage = "main.jsp";
		}else if(com.equals("/modify.pdo")) { // ��ǰ ���� �������� �̵�
			viewPage = "modify_product_view.jsp";
		}else if(com.equals("/delete.pdo")) { // ��ǰ ����
			command = new PDeleteCommand();
			command.execute(request,response); 
			viewPage = "main.pdo";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request,response);
	}

}
