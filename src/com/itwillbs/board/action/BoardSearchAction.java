package com.itwillbs.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println(" M : BoardSearchAction_execute 호출");
		
		// list동작 + 검색어 
		
		// 전달된 정보 저장 (search)
		String search = request.getParameter("search");
		System.out.println(" M 검색어 : " + search);
		
		// DAO 객체 생성
		BoardDAO dao = new BoardDAO();
		// 검색어가 포함된 글이 있는지 체크(개수)
		int searchCnt = dao.getBoardCount(search);
		
		System.out.println(" M : 검색된 글 개수  " + searchCnt);
		
		
		///////////////////////////////////////////////////////////////////////////////////
		// 페이징 처리 1
		// 한 페이지에 보여줄 글의 개수
		int pageSize = 5;
		
		// 현 페이지 정보 계산하기
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
		pageNum = "1"; // pageNum정보가 없을경우 항상 1페이지
		}
		
		// 페이지 시작행 계산 1, 11, 21, 31, 41,......
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage - 1) * pageSize + 1;
		// 페이지 끝행 계산 10,20,30,40,....
		int endRow = currentPage * pageSize;
		
		///////////////////////////////////////////////////////////////////////////////////


		
		List<BoardDTO> searchList = null;
		// 검색어가 포함된 제목 있을 때
		// => 해당 내용만 디비에서 저장해서 가져오기
		if(searchCnt > 0) {
			// getBoardList() - 오버로딩 (검색어 + 페이징처리)
			searchList = dao.getBoardList(startRow, endRow, search);
		}
		
		// 검색어가 포함된 제목 없을 때
		// => 일반글 전체 가져오기 / 또는 그냥 null 처리해도 OK
		
		
		
		// DB에서 전달받은 데이터를 request 영역에 저장
		request.setAttribute("boardList", searchList);
		
		request.setAttribute("result", Integer.parseInt(request.getParameter("result")));
		request.setAttribute("pageCount", Integer.parseInt(request.getParameter("pageCount")));
		request.setAttribute("pageBlock", Integer.parseInt(request.getParameter("pageBlock")));
		request.setAttribute("startPage", Integer.parseInt(request.getParameter("startPage")));
		request.setAttribute("endPage", Integer.parseInt(request.getParameter("endPage")));

		
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./center/notice.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
