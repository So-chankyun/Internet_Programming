package com.page.Command;

import java.util.*;

import javax.servlet.http.*;

import com.page.Dao.*;
import com.page.Dto.*;

public class BListCommand implements BCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		request.setAttribute("list", dtos);
	}
}
