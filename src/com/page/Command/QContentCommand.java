package com.page.Command;

import javax.servlet.http.*;

import com.page.Dao.*;
import com.page.Dto.*;

public class QContentCommand implements BCommand {
	
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String qId = request.getParameter("qId");
		QDao dao = new QDao();
		QDto dto = dao.contentView(qId);
		// �ش� ���̵� �ش��ϴ� ���븸 �������� �Ǳ� ������ �ϳ��� �����ϸ� ��.
		request.setAttribute("content_view",dto);
		session.setAttribute("contentId",dto.getqUserId());
	}
}
