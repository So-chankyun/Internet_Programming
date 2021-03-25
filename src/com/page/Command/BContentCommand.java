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
		// �ش� ���̵� �ش��ϴ� ���븸 �������� �Ǳ� ������ �ϳ��� �����ϸ� ��.
		request.setAttribute("content_view",dto);
		session.setAttribute("contentId",dto.getbUserId());
	}
}
