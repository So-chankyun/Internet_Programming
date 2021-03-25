package com.page.Command;

import javax.servlet.http.*;

import com.page.Dao.*;

public class BDeleteCommand implements BCommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bId = request.getParameter("bId");
		System.out.println(bId);
		
		BDao dao = new BDao();
		dao.delete(bId);
	}

}
