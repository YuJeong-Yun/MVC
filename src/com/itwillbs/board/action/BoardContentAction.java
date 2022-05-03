package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardContentAction_execute() 호출 ");
		
		
		// 전달된 데이터 저장(num, pageNum)
		// 디비 저장 유무에 따라 데이터 타입 다르게 저장
		int num = Integer.parseInt(request.getParameter("num")); // 디비에 들어가므로 데이터 타입 맞춰줌
		String pageNum = request.getParameter("pageNum"); // 디비에 안들어감
		
		// BoardDAO 객체 생성
		BoardDAO dao = new BoardDAO();
		
		// 조회수 1증가 동작 실행
		dao.updateReadCount(num);
		System.out.println(" M : 조회수 1증가 완료"); // 조회수 증가 체크
		
		// 글번호에 해당하는 글 전체의 정보를 가져오기
		BoardDTO dto = dao.getBoard(num);
		System.out.println(" M :글정보 1개 조회 완료");
		
		
		// request 영역에 글정보를 저장
		request.setAttribute("dto", dto);
//		request.setAttribute("dto", dao.getBoard(num)); // 데이터 전달만 할 경우 이 방식으로 사용해도 OK
		request.setAttribute("pageNum", pageNum);
		
		// 페이지 이동(출력) (./center/content.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./center/content.jsp");
		forward.setRedirect(false); // request영역의 정보를 전달해야 하므로 forward 방식으로 이동해야함
		
		return forward;
	}

}
