package com.page.Command;

import javax.servlet.http.*;
import com.page.Dao.*;

public class BWriteCommand implements BCommand{
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String bUserId = (String) session.getAttribute("userId");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		BDao dao = new BDao();
		dao.write(bUserId,bTitle,bContent);
	}
}
