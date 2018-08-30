package com.ds.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

	public static Map<String, Object> paramToHashMap(HttpServletRequest request) {

		//넘길 파라미터 생성
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		//파라미터 값 저장
		Map<String, String[]> paramHashMap = request.getParameterMap();
		
		//파라미터값 받은 맵 내용을 iterator 실시
		Iterator<String> it = paramHashMap.keySet().iterator();
		
		//파라미터값 전체 돌리기
		while (it.hasNext()) {
			
			//키 값 등록
			String key = it.next().toString();
			
			//key 값이 끝에 []로 넘어오는 경우엔 [] 빼주기 위함
			String rtn_key = key;
			if (key.substring(key.length() - 2).equals("[]")) {
				rtn_key = key.substring(0, key.length() - 2);
			}
			
			//String 배열에 getParameterValues를 사용하여 같은 이름으로 던지는 내용 담음.
			Object[] parameters = (Object[]) request.getParameterValues(key);

			//parameter가 같은 이름으로 던진 것일땐 해쉬맵에 키값과 함께 배열로 저장
			if (parameters.length > 1) {
				paramMap.put(rtn_key, parameters);
			
			// 아니면 그냥 저장.
			} else { 
				paramMap.put(rtn_key, request.getParameter(key));
			}
		}
		return paramMap;
	}

}
