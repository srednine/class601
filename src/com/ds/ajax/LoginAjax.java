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

@WebServlet("/ajax_login.do")
public class LoginAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginAjax() {
        super();
    }
                         
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RNCommon.errAccessAjax(request, response, "login.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//return 시킬 객체
		Object rtnObj = null;
		
		try {
			JSONObject req_json = new JSONObject(request.getParameter("temp"));
			rtnObj = new DBHelper(new Integer(0), OraConn.getConn(), SqlConfig.loginchk
					,	(String)req_json.get("id"),	(String)req_json.get("pw")
					).selectOne();
			
		} catch (Exception e) {
			rtnObj = RNCommon.exceptionRtn(e, "ajax_login doPost ERROR!! [t/c]", "i");
		}		
		
		RNCommon.returnAjax(response, "rtn", rtnObj);
		
		//JSONObject req_json = new JSONObject(request.getParameter("temp"));
		//RN_Member member = new RN_Member((String)req_json.get("id"), (String)req_json.get("pw"));
		//RNCommon.returnAjax(response, "rtn", new MemberDAO().loginchk(member));
	}
}
