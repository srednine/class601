package com.ds.ajax;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.ds.util.RNCommon;
import com.ds.util.RequestUtil;

@WebServlet("/ajax_board_delete_batch.do")
public class BoardDeleteBatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardDeleteBatch() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RNCommon.errAccessAjax(request, response, "board.do");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//JSONObject req_json = new JSONObject(request.getParameter("temp"));
		//System.out.println("req_json.get(\"brdNoArr\") : " + req_json.get("brdNoArr"));		
		//int[] hello = (int[]) req_json.get("brdNoArr");		
		//System.out.println("hello : " + Arrays.toString(hello));
		
		System.out.println("RequestUtil.paramToHashMap(request) : " + RequestUtil.paramToHashMap(request));
		
		RNCommon.returnAjax(response, "rtn", 1);
	}

}
