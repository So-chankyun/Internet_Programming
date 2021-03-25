package com.page.Command;
import javax.servlet.http.*;

import com.page.Dao.*;

public class PDeleteCommand implements BCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String pId = request.getParameter("pId");
		System.out.println(pId);
		PDao dao = new PDao();
		dao.delete(pId);
	}
}
