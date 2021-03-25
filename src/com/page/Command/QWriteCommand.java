package com.page.Command;

import javax.servlet.http.*;
import com.page.Dao.*;

public class QWriteCommand implements BCommand{
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String qUserId = (String) session.getAttribute("userId");
		String qTitle = request.getParameter("qTitle");
		String qContent = request.getParameter("qContent");
		QDao dao = new QDao();
		dao.write(qUserId,qTitle,qContent);
	}
}
