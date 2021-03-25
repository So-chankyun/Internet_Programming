package com.page.Command;

import javax.servlet.http.*;
import com.page.Dao.*;

public class QModifyCommand implements BCommand {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String qId= request.getParameter("qId");
		String qTitle= request.getParameter("qTitle");
		String qContent= request.getParameter("qContent");
		QDao dao = new QDao();
		dao.modify(qId, qTitle, qContent);
	}
}
