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
import com.ds.vo.RN_Member;

@WebServlet("/edit.do")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public EditServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String mem_id = (String) request.getSession().getAttribute("sid");
		if (mem_id == null) {
			RNCommon.errAccessAjax(request, response, "index.do");
		}
		
		try {
			
			RN_Member vo = new DBHelper(new RN_Member(), OraConn.getConn(), SqlConfig.memberSelectOne, mem_id).selectOne();
			//RN_Member vo = new MemberDAO().memberSelectOne(mem_id);		
			request.setAttribute("mem_id", vo.getMem_id());
			request.setAttribute("mem_pw", vo.getMem_pw());
			request.setAttribute("mem_name", vo.getMem_name());
			request.setAttribute("mem_email", vo.getMem_email());
			request.setAttribute("mem_age", vo.getMem_age());
			request.setAttribute("mem_addr1", vo.getMem_addr1());
			request.setAttribute("mem_addr2", vo.getMem_addr2());
			
			request.getRequestDispatcher("/WEB-INF/member/edit.jsp").forward(request, response);
			
		} catch (Exception e) {
			RNCommon.exceptionRtn(e, "edit.do GET ERROR!");
			RNCommon.locaChange(request, response, "회원정보수정 실패, 잠시 후에 다시 실행해주세요.[t/c]", "index.do");
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String msg = "";
		String href = "";
		
		try {
			
			String sname = request.getParameter("mem_name");
			
			//RN_Member member = new RN_Member(
			//			request.getParameter("mem_id") 
			//		,	request.getParameter("mem_pw")
			//		,	sname
			//		,	request.getParameter("mem_email")
			//		,	Integer.parseInt(request.getParameter("mem_age"))
			//		,	request.getParameter("mem_addr1") 
			//		,	request.getParameter("mem_addr2")
			//		);
			//int rtn = new MemberDAO().memberEdit(member);
			
			Object[] values = {
					request.getParameter("mem_pw") 
				,	sname
				,	request.getParameter("mem_email")
				,	Integer.parseInt(request.getParameter("mem_age"))
				,	request.getParameter("mem_addr1") 
				,	request.getParameter("mem_addr2")
				,	request.getParameter("mem_id")
				};
			int rtn = new DBHelper(new Integer(0), OraConn.getConn(), SqlConfig.memberEdit, values).insUpDel();			
			if (rtn != 1) {
				msg = "회원정보수정 실패, 잠시 후에 다시 실행해주세요.[q/e]";
			} else {
				msg = "수정완료.";
				request.getSession().setAttribute("sname", sname);
			}
			
		} catch (Exception e) {
			RNCommon.exceptionRtn(e, "edit.do POST ERROR!");
			msg = "회원정보수정 실패, 잠시 후에 다시 실행해주세요.[t/c]";
		}
		RNCommon.locaChange(request, response, msg, "index.do");
		
	}	

}
