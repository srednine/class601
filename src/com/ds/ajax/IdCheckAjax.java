package com.ds.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.ds.dbconn.OraConn;
import com.ds.dbconn.SqlConfig;
import com.ds.util.DBHelper;
import com.ds.util.RNCommon;

@WebServlet("/ajax_id_check.do")
public class IdCheckAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IdCheckAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RNCommon.errAccessAjax(request, response, "login.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			RNCommon.returnAjax(response, "rtn", new DBHelper(new Integer(0), OraConn.getConn(), SqlConfig.idchk, new JSONObject(request.getParameter("temp")).getString("mem_id")).selectOne());
		} catch (Exception e) {
			RNCommon.exceptionRtn(e, "idchk Fail!!");
		}
		
		//JSONObject req_json = new JSONObject(request.getParameter("temp"));
		//RN_Member member = new RN_Member();
		//member.setMem_id(req_json.getString("mem_id"));
		//RnCommon.returnAjax(response, "rtn", new MemberDAO().idchk(member));
	}

}
