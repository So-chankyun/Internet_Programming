package com.page.Command;

import java.util.*;

import javax.servlet.http.*;

import com.page.Dao.*;
import com.page.Dto.*;

public class QListCommand implements BCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		QDao dao = new QDao();
		ArrayList<QDto> dtos = dao.list();
		request.setAttribute("list", dtos);
	}
}
