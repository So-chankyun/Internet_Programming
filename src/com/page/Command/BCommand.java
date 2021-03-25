package com.page.Command;

import javax.servlet.http.*;

public interface BCommand {
	void execute(HttpServletRequest request, HttpServletResponse response);
}
