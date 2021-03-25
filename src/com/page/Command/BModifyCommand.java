package com.page.Command;

import javax.servlet.http.*;
import com.page.Dao.*;

public class BModifyCommand implements BCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bId= request.getParameter("bId");
		String bTitle= request.getParameter("bTitle");
		System.out.println(bTitle);
		String bContent= request.getParameter("bContent");
		System.out.println(bContent);
		BDao dao = new BDao();
		dao.modify(bId, bTitle, bContent);
	}
}
