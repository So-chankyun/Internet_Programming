package com.page.Command;
import javax.servlet.http.*;
import com.page.Dao.*;
import com.page.Dto.*;

public class PProductDetail implements BCommand {
	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		String pId = request.getParameter("pId");
		PDao dao = new PDao();
		PDto dto = dao.contentView(pId);
		// �ش� ���̵� �ش��ϴ� ���븸 �������� �Ǳ� ������ �ϳ��� �����ϸ� ��.
		request.setAttribute("product_detail_view",dto);
		session.setAttribute("contentId", dto.getUserId());
	}
}
