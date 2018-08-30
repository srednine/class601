package com.ds.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ds.dbconn.OraConn;
import com.ds.dbconn.SqlConfig;
import com.ds.util.DBHelper;
import com.ds.util.RNCommon;
import com.ds.vo.RN_Board;
import com.ds.vo.RN_Member;

@WebServlet("/board.do")
public class BoardServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public BoardServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			//현재 페이지
			int nowPage = 1;
			String tmp = request.getParameter("page");
			if (tmp != null) {
				nowPage = Integer.parseInt(tmp);
			}
			
			//밑에 탭에 보여져야 할 페이지의 갯수
			int tapCount = 5;
			
			//한 페이지에 보여줘야 할 row 갯수
			int viewCount = 10;
			
			//전체 갯수
			int rowCount = new DBHelper(new Integer(0), OraConn.getConn(), SqlConfig.boardListCount).selectOne();
			
			//웹 페이지 탭에 시작할 숫자 / 끝인 숫자
			int startTapPage = ((nowPage / tapCount) * tapCount) + ( (nowPage % tapCount) > 0 ? 1 : 0 );
			int endTapPage = startTapPage + (tapCount - 1);
			
			//처음 버튼을 활성화 할지 안할지
			String firstBtnYN = startTapPage == 1 ? "disabled" : "";
			
			//맨끝 버튼을 활성화 할지 안할지
			String lastBtnYN = "";
			
			//마지막 탭인지 아닌지
			int lastPage = (rowCount / viewCount) + ( (rowCount % viewCount) > 0 ? 1 : 0 );
			if (endTapPage >= lastPage) {
				endTapPage = lastPage;
				lastBtnYN = "disabled";
			}
			
			//이전 버튼을 활성화 할지 안할지
			String prevBtnYN = nowPage == 1 ? "disabled" : "";
			
			//다음 버튼을 활성화 할지 안할지
			String nextBtnYn = nowPage == lastPage ? "disabled" : "";
						
			//쿼리문에 조회 끝인 순번 / 시작할 순번
			int sqlEndnum = nowPage * viewCount; 
			int sqlStartnum = sqlEndnum - (viewCount - 1);
			
			List<RN_Board> list = new DBHelper(new RN_Board(), OraConn.getConn(), SqlConfig.boardList, sqlStartnum, sqlEndnum).selectList();
			request.setAttribute("list", list);					//게시글 리스트
			request.setAttribute("startTapPage", startTapPage);	//탭에서 시작할 페이지
			request.setAttribute("endTapPage", endTapPage);		//탭에서 마지막 페이지
			request.setAttribute("lastPage", lastPage);			//제일 마지막 페이지 
			request.setAttribute("firstBtnYN", firstBtnYN);		//처음버튼 활성유무
			request.setAttribute("lastBtnYN", lastBtnYN);		//맨끝버튼 활성유무
			request.setAttribute("prevBtnYN", prevBtnYN);		//이전버튼 활성유무
			request.setAttribute("nextBtnYn", nextBtnYn);		//다음버튼 활성유무
		} catch (Exception e) {
			RNCommon.exceptionRtn(e, "board.do GET ERROR!!");
		}
		request.getRequestDispatcher("/WEB-INF/board/board.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}