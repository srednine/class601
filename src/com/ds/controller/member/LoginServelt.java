package com.ds.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ds.dbconn.OraConn;
import com.ds.dbconn.SqlConfig;
import com.ds.util.DBHelper;
import com.ds.util.RNCommon;
import com.ds.vo.RN_Member;

@WebServlet("/login.do")
public class LoginServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/member/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String msg = "";
		String href = "";
		
		try {			
			//RN_Member member = new MemberDAO().login(new RN_Member(request.getParameter("mem_id"), request.getParameter("mem_pw")));
			RN_Member member = new DBHelper(new RN_Member(), OraConn.getConn(), SqlConfig.login, request.getParameter("mem_id"), request.getParameter("mem_pw")).selectOne();
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("sid", member.getMem_id());
			
			String sname = member.getMem_name();
			httpSession.setAttribute("sname", sname);

			msg = sname + " 님 환영합니다 :)";
			href = "index.do";
			
		} catch (Exception e) {
			RNCommon.exceptionRtn(e, "login.do post ERROR!");
			msg = "로그인 실패, 잠시 후에 다시 실행해주세요.[t/c]";
			href = "login.do";
		}
		
		RNCommon.locaChange(request, response, msg, href);
	}

}
