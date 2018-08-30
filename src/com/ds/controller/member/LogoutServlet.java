package com.ds.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ds.util.RNCommon;

@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		String sname = (String) httpSession.getAttribute("sname");
		httpSession.invalidate();
		RNCommon.locaChange(req, resp, sname + " 님 다음에 또 오세요~", "index.do");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	

}
