package com.page.Command;

import javax.servlet.http.*;

import com.page.Dao.*;

public class QDeleteCommand implements BCommand {

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String qId = request.getParameter("qId");
		
		QDao dao = new QDao();
		dao.delete(qId);
	}

}
