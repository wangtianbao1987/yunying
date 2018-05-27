package com.wtb.fuwu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/orderConfirm")
public class OrderConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String winId = request.getParameter("winId");
		List<Map<String,Object>> items = (List<Map<String,Object>>)session.getAttribute(winId);
		if(items == null) {
			PrintWriter out = response.getWriter();
			out.print("会话失效，请返回重新确定订单。");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/orderConfirm.jsp").forward(request, response);
	}

}
