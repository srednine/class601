package com.ds.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ds.dbconn.OraConn;
import com.ds.dbconn.SqlConfig;
import com.ds.util.DBHelper;
import com.ds.util.RNCommon;

/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join.do")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		request.getRequestDispatcher("/WEB-INF/member/join.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String msg = "";
		String href = "";
		
		Object[] values = new Object[7];
		values[0] = request.getParameter("mem_id");
		values[1] = request.getParameter("mem_pw");
		values[2] = request.getParameter("mem_name");
		values[3] = request.getParameter("mem_email");
		values[4] = Integer.parseInt(request.getParameter("mem_age"));
		values[5] = request.getParameter("mem_addr1");
		values[6] = request.getParameter("mem_addr2");
		
		try {

			//int rtn = new MemberDAO().memberInsert(member);
			int rtn = new DBHelper(OraConn.getConn(), SqlConfig.memberInsert, values).insUpDel();			
			if (rtn != 1) {
				msg = "회원가입 실패, 잠시 후에 다시 실행해주세요.[q/e]";
				href = "index.do";
			} else {
				msg = "회원가입 성공";
				href = "login.do";
			}
			
		} catch (Exception e) {
			RNCommon.exceptionRtn(e, "join.do POST ERROR!");
			msg = "회원가입 실패, 잠시 후에 다시 실행해주세요.[t/c]";
			href = "index.do";
		}
		
		RNCommon.locaChange(request, response, msg, href);		
	}
}
