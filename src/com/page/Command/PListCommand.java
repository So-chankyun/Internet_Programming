package com.page.Command;

import java.util.*;

import javax.servlet.http.*;

import com.page.Dao.*;
import com.page.Dto.*;

public class PListCommand implements BCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		PDao dao = new PDao();
		ArrayList<PDto> dtos = dao.list();
		request.setAttribute("plist", dtos);
	}
}
