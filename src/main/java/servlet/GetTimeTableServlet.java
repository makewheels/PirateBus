package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetTimeTableServlet extends HttpServlet {
	private static final long serialVersionUID = 910341781110464124L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String busName = request.getParameter("busName");
		String direction = request.getParameter("direction");
		// 读发车时间
		GetTimeTableServlet.class.getResource("").getPath();
	}

}
