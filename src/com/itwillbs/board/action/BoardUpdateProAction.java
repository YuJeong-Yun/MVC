package com.itwillbs.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.db.BoardDAO;
import com.itwillbs.board.db.BoardDTO;

public class BoardUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println(" M : BoardProAction_execute 호출 ");
		
		// 한글 처리 ( web.xml 에서 세팅함. -> 생략가능!)
//		request.setCharacterEncoding("UTF-8");
		
		// 전달된 정보 체크 (수정할 데이터 name, pass, subject, content)
		// => DTO 객체에 저장
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDTO dto = new BoardDTO();
		dto.setNum(num);
		dto.setName(request.getParameter("name"));
		dto.setPass(request.getParameter("pass"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		
		System.out.println(" M : " + dto);
		String pageNum = request.getParameter("pageNum");
		System.out.println(" M : pageNum : "+pageNum);
		
		
		// DAO 객체 생성 - 글 수정 메서드 호출 updateBoard()
		BoardDAO dao = new BoardDAO();
		// 처리결과 저장 (-1, 0, 1)
		int result = dao.updateBoard(dto);
		System.out.println(result);
		// 처리 결과에 따른 페이지 이동(js)
		// => Action 페이지에서 js 이동시에는 컨트롤러를 통한 페이지 이동 X
		
		// 처리 응답 결과는 html 형태로 보여주겠다. (MIME 타입)
		response.setContentType("text/html; charset=UTF-8");
		// 응답결과를 처리하는 연결통로를 지정(데이터 보낼 준비)
		PrintWriter out = response.getWriter();
		
		if(result==0) { // 비밀번호 오류
			//out.print("HTML 코드를 출력가능!");
			out.print("<script>");
			out.print("alert('비밀번호 오류!!!(수정 x'); ");
			out.print("history.back(); ");
			out.print("</script>");
		
			// 응답처리하던 연결통로를 제거 (자원해제)
			out.close();
			
			return null;
			
		}else if(result==-1) {
			//out.print("HTML 코드를 출력가능!");
			out.print("<script>");
			out.print("alert('글정보 없음!!!(수정 x)'); ");
			out.print("history.back(); ");
			out.print("</script>");
		
			// 응답처리하던 연결통로를 제거 (자원해제)
			out.close();
			
			return null;
		}else { // result == 1
			//out.print("HTML 코드를 출력가능!");
			out.print("<script>");
			out.print("alert('글 수정 완료!!!(수정 O)'); ");
			out.print("location.href='./BoardContent.bo?pageNum="+pageNum+"&num="+num+"'; ");
			out.print("</script>");
		
			// 응답처리하던 연결통로를 제거 (자원해제)
			
			out.close();
			return null;
		}
		

	}

}
