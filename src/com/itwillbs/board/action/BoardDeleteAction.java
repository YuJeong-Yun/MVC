package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println(" M : BoardDeleteAction.execute() 호출");
		
		// 한글처리 (생략 -> web.xml)
		
		// 전달되는 정보 저장 (num, pass, pageNum)
		// => DTO
		BoardDTO dto = new BoardDTO(); 
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setPass(request.getParameter("pass"));
		String pageNum = request.getParameter("pageNum");
		
		// DAO 객체 -> 삭제 메서드
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(dto);
		
		
		// 삭제 처리 결과에 따른 페이지 이동(js)
		// => Action 페이지에서 js 이동시에는 컨트롤러를 통한 페이지 이동 X
		
		response.setContentType("text/html; charset=UTF-8");
		// 응답결과를 처리하는 연결통로를 지정(데이터 보낼 준비)
		PrintWriter out = response.getWriter();
		
		if(result==0) { // 비밀번호 오류
			//out.print("HTML 코드를 출력가능!");
			out.print("<script>");
			out.print("alert('비밀번호 오류!!!(삭제 x)'); ");
			out.print("history.back(); ");
			out.print("</script>");
		
			// 응답처리하던 연결통로를 제거 (자원해제)
			out.close();
			
			return null;
			
		}else if(result==-1) {
			//out.print("HTML 코드를 출력가능!");
			out.print("<script>");
			out.print("alert('글정보 없음!!!(삭제 x)'); ");
			out.print("history.back(); ");
			out.print("</script>");
		
			// 응답처리하던 연결통로를 제거 (자원해제)
			out.close();
			
			return null;
		}else { // result == 1
			//out.print("HTML 코드를 출력가능!");
			out.print("<script>");
			out.print("alert('글 수정 완료!!!(삭제 O)'); ");
			out.print("location.href='./BoardList.bo?pageNum="+pageNum+"';");
			out.print("</script>");
		
			// 응답처리하던 연결통로를 제거 (자원해제)
			
			out.close();
			return null;
		}
	}
}
