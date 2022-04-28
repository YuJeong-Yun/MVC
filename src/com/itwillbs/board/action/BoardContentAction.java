package com.itwillbs.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println(" M : BoardContentAction_execute() 호출 ");
		
		
		// 전달된 데이터 저장(num, pageNum)
		
		
		// BoardDAO 객체 생성
		
		// 조회수 1증가 동작 실행
		
		// 글번호에 해당하는 글 전체의 정보를 가져오기
		
		// request 영역에 글정보를 저장
		
		// 페이지 이동(출력) (./center/content.jsp)
		
		
		return null;
	}

}
