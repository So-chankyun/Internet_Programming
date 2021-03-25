package com.page.Command;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.tomcat.util.http.fileupload.servlet.*;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
import com.page.Dao.*;
import com.page.Dto.*;

/**
 * Servlet implementation class PModifyProduct
 */
@WebServlet("/PMP")
public class PModifyProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PModifyProduct() {
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
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println("PMP실행");
		String file = "";
		String oriFile = ""; // 이름이 변경되기전 실제 파일의 이름(original name), 같은 이름으로 사진이 저장되는 경우가 있기 때문이다.
		int maxSize = 3*1024*1024; // 3MB
		String encoding = "euc-kr";
		ServletContext context = getServletContext(); //어플리케이션에 대한 정보를 ServletContext 객체가 갖게 됨. 
		String saveDir = context.getRealPath("Product"); //절대경로를 가져옴
				   
		   boolean isMulti = ServletFileUpload.isMultipartContent(request);// boolean타입. ??????
		   if (isMulti) {
		       MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		       Enumeration<String> files = multi.getFileNames(); 
				String str = (String) files.nextElement(); // 정확히 어떤 파일을 나타내는 거지...
				file = multi.getFilesystemName(str);
				oriFile = multi.getOriginalFileName(str);
		       
		        PDao dao = new PDao();
		        String pId = multi.getParameter("pId");
		        String userId = (String) session.getAttribute("userId");
				String pName = multi.getParameter("pName");
				String pTTC= multi.getParameter("pTTC");
				String pPrice= multi.getParameter("pPrice");
				String pTradeMethod= multi.getParameter("pTradeMethod");
				String pPhoneNumber= multi.getParameter("pPhoneNumber");
				PDto dto = new PDto(userId, pName, pTTC,pPrice,pTradeMethod,pPhoneNumber,oriFile,file);
				dao.modify(pId,dto);
		   }
		   
		   RequestDispatcher dispatcher = request.getRequestDispatcher("main.pdo");
		   dispatcher.forward(request,response);
	}
}
