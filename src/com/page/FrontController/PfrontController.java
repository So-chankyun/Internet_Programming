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
		String viewPage = null; // 어떤 view를 보여줄 것인가?
		BCommand command = null; // 어떠한 명령을 내릴 것인가?
		
		String uri = request.getRequestURI();// ~.do까지의 경로를 모두 반환한다.
		String conPath = request.getContextPath(); // /*.do 앞까지만 반환한다.
		String com = uri.substring(conPath.length()); // 왜 conPath를 그대로 쓰지 않는가?
		System.out.println(com);
		
		if(com.equals("/enroll_product_view.pdo")) { // 물품 등록 페이지로 이동
			viewPage = "enroll_product_view.jsp";
		}else if(com.equals("/enroll_product.pdo")) { // 물품 등록 진행
			viewPage = "/PEP";
		}else if(com.equals("/product_detail_view.pdo")) { // 물품 내용
			command = new PProductDetail();
			command.execute(request,response);
			viewPage = "product_detail_view.jsp";
		}else if(com.equals("/main.pdo")) { // 물품 내용
			command = new PListCommand();
			command.execute(request,response);
			viewPage = "main.jsp";
		}else if(com.equals("/modify.pdo")) { // 물품 수정 페이지로 이동
			viewPage = "modify_product_view.jsp";
		}else if(com.equals("/delete.pdo")) { // 물품 삭제
			command = new PDeleteCommand();
			command.execute(request,response); 
			viewPage = "main.pdo";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request,response);
	}

}
