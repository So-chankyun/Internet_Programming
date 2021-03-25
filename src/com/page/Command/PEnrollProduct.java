package com.page.Command;

import java.io.IOException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.tomcat.util.http.fileupload.servlet.*;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
import com.page.Dao.*;
import com.page.Dto.*;

/**
 * Servlet implementation class PnrollProduct
 */
@WebServlet("/PEP")
public class PEnrollProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PEnrollProduct() {
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
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			HttpSession session = request.getSession();
			
			String file = "";
			String oriFile = ""; // �̸��� ����Ǳ��� ���� ������ �̸�(original name), ���� �̸����� ������ ����Ǵ� ��찡 �ֱ� �����̴�.
			int maxSize = 3*1024*1024; // 3MB
			String encoding = "euc-kr";
			ServletContext context = getServletContext(); //���ø����̼ǿ� ���� ������ ServletContext ��ü�� ���� ��. 
			String saveDir = context.getRealPath("Product"); //�����θ� ������
					   
			   boolean isMulti = ServletFileUpload.isMultipartContent(request);// booleanŸ��. ??????
			   if (isMulti) {
			       MultipartRequest multi = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
			       Enumeration<String> files = multi.getFileNames(); 
					String str = (String) files.nextElement(); // ��Ȯ�� � ������ ��Ÿ���� ����...
					file = multi.getFilesystemName(str);
					System.out.println(file);
					oriFile = multi.getOriginalFileName(str);
			       System.out.println(oriFile);
			        PDao dao = new PDao();
			        String userId = (String)session.getAttribute("userId");
					String pName = multi.getParameter("pName");
					String pTTC= multi.getParameter("pTTC");
					String pPrice= multi.getParameter("pPrice");
					String pTradeMethod= multi.getParameter("pTradeMethod");
					String pPhoneNumber= multi.getParameter("pPhoneNumber");
					PDto dto = new PDto(userId, pName, pTTC,pPrice,pTradeMethod,pPhoneNumber,oriFile,file);
					dao.uploadFile(dto);
			   }
			   
			   
			   RequestDispatcher dispatcher = request.getRequestDispatcher("main.pdo");
			   dispatcher.forward(request,response);
	}
}
