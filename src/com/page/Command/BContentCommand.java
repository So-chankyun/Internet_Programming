package com.page.Command;

import javax.servlet.http.*;

import com.page.Dao.*;
import com.page.Dto.*;

public class BContentCommand implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId);
		// 해당 아이디에 해당하는 내용만 가져오면 되기 때문에 하나만 리턴하면 됨.
		request.setAttribute("content_view",dto);
		session.setAttribute("contentId",dto.getbUserId());
	}
}
