package com.itwillbs.board.action;

import java.util.List;

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
		int result = 0;
		result = dao.getBoardCount();
		System.out.println(" M : 글 개수 " + result + "개");
		
		System.out.println("페이징 1 시작");
		//////////////////////////////////////////////////////////////////////
		// 페이징 처리 1
		// 한 페이지에 보여줄 글의 개수 설정 (5개로 설정)
		int pageSize = 5;
		// 현재 페이지 정보 계산하기
		String pageNum = request.getParameter("pageNum");		
		if(pageNum == null) {
			pageNum = "1";  // pageNum 정보가 없을 경우 항상 1페이지
		}
		
		// 페이지 시작 행 계산 ( 1페이지에 10개씩 나오므로 - 1, 11, 21, 31, 41, ..... )
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		// 페이지 끝 행 계산
		int endRow = currentPage * pageSize;
		
		//////////////////////////////////////////////////////////////////////
		
		System.out.println("글 정보 가져오기");
		// 글이 있을 때, 글정보 전부를 가져오기
		// getBoardList();
		List boardList = null;
		
		if(result > 0) {
			boardList = dao.getBoardList(startRow, endRow);
		} 
		
		System.out.println("페이징 2 시작");
		//////////////////////////////////////////////////////////////////////
		// 페이징 2
		// 전체 필요한 페이지 수 계산
		int pageCount = result / pageSize + (result%pageSize==0 ? 0 : 1);
		
		// 한 화면에 보여 줄 페이지 블럭의 수 (3개로 설정)
		int pageBlock = 3;
		
		
		// 페이지 블럭의 시작 번호   1~10=>1  11~20=>11  21~30=>21
		// 1~3=>1 4~6=>2 7~9=>3
		int startPage = ((currentPage-1) / pageBlock) * pageBlock + 1;		
		// 페이지 블럭의 끝 번호
		int endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) { 
			endPage = pageCount;
		}
		//////////////////////////////////////////////////////////////////////
	
			
		System.out.println("result : "+result+"\n\n\n");
		// request 영역에 글정보(list) 저장
		request.setAttribute("boardList", boardList);
		
		// reqeust 영역에 페이징처리 정보를 저장
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("result", result);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 페이지 이동 (./center/notice.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./center/notice.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
