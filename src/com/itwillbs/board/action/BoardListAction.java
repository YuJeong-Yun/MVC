package com.itwillbs.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : BoardListAction-execute() 호출 ");
		
		// 전달정보 X
			
		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		// 글 개수 확인 동작 실행 - getBoardCount();
		int num=0;
		num = dao.getBoardCount();
		
		// 글이 있을 때, 글정보 전부를 가져오기
		// getBoardList();
		ArrayList boardList;
		if(num>0) {
			boardList = dao.getBoardList(0, 5);
		} else {
			boardList = null;
		}
		
		// request 영역에 글정보(list) 저장
		request.setAttribute("boardList", boardList);
		
		// 페이지 이동 (./center/notice.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./center/notice.jsp");
		forward.setRedirect(true);
		
		return forward;
	}

}
