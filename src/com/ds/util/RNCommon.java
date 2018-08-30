package com.ds.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class RNCommon {
	
	public static void exceptionRtn(Exception e, String msg) {
		e.printStackTrace();
		System.out.println(msg);
		System.out.println(e.getMessage());
	}

	/**
	 * 예외처리할 때 메세지를 출력하는 기능
	 * @param e
	 * @param msg
	 * @param type
	 * @return
	 */
	public static Object exceptionRtn(Exception e, String msg, String type) {
		e.printStackTrace();
		System.out.println(msg);
		System.out.println(e.getMessage());
		
		Object rtn = new Object();
		
		//void
		if (type.equals("s") || type.equals("S")) {
			rtn = "";
		//int
		}else if (type.equals("i") || type.equals("I")) {
			rtn =  0;
		//Boolean
		}else if (type.equals("b") || type.equals("B")) {
			rtn =  false;			
		//Object
		}else if (type.equals("o") || type.equals("o")) {
			rtn =  null;
		}
		return rtn;
	}
	
	/**
	 * json 파일로 만들어서 값을 return 함.
	 * @param response
	 * @param key
	 * @param val
	 */
	public static void returnAjax(HttpServletResponse response, String key, Object val) {
		try {
			JSONObject jsonObject = new JSONObject();
			PrintWriter out = response.getWriter();			
			jsonObject.put(key, val);
			out.print(jsonObject.toString());
		} catch (Exception e) {
			RNCommon.exceptionRtn(e, "returnAjax FAIL!");
		}		
	}
	
	/**
	 * 페이지 잘못 접근시 특정주소(main 등..)로 보냄. 
	 * @param request
	 * @param response
	 * @param href
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void errAccessAjax(HttpServletRequest request, HttpServletResponse response, String href)  throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		response.getWriter().print(
			"<script>"
			+ "alert('잘못된 접근입니다.');"
			+ "location.href='" + href + "'"
			+ "</script>"
		);
	}
	
	/**
	 * 특정주소로 보낼 때 특정 메세지도 같이 포함해서 보내고 싶은 경우에 씀.
	 * @param request
	 * @param response
	 * @param msg
	 * @param href
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void locaChange(HttpServletRequest request, HttpServletResponse response, String msg, String href)  throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		response.getWriter().print(
			"<script>"
			+ "alert('"+ msg + "');"
			+ "window.location.href='" + href + "'"
			+ "</script>"
		);
	}

	/**
	 * 배열을 던지면 추가로 배열을 만들어줌.
	 * @param arr
	 * @param element
	 * @return
	 */
	public static <T> T[] append(T[] arr, T element) {
	    final int N = arr.length;
	    arr = Arrays.copyOf(arr, N + 1);
	    arr[N] = element;
	    return arr;
	}
}
