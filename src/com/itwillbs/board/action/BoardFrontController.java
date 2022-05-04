package com.itwillbs.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" BoardFrontController - doPROCESS() 호출");
		System.out.println(" GET/POST방식 모두 처리!! \n");
		
		//////////////////////////////1. 가상 주소 계산 /////////////////////////////////
		System.out.println(" C :1. 가상 주소 계산 시작");
		
		// 가상주소 가져오기
		String requestURI =	request.getRequestURI();
		System.out.println(" C : requestURI -  " + requestURI);
		// 프로젝트명  가져오기
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		// 가상주소 계산 (가상주소 - 프로젝트명)
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - " + command);
		
		
		System.out.println(" C : 1. 가상 주소 계산 끝\n");
		//////////////////////////////1. 가상 주소 계산 /////////////////////////////////
		//////////////////////////////2. 가상 주소 매핑 /////////////////////////////////
		System.out.println(" C : 2. 가상 주소 매핑 시작 ");
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/BoardWrite.bo")) { // 글쓰기 페이지로 이동
			System.out.println(" C : /BoardWrite.bo 호출 " );
			System.out.println(" C : DB사용 X 정보입력 페이지(view)");
			
			// 페이지이동 객체 생성
			forward = new ActionForward();
			forward.setPath("./center/boardWrite.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/BoardWriteAction.bo")) { // 글쓰기 페이지에서 글쓰기 버튼 클릭했을 때
			System.out.println(" C : /BoardWriteAction.bo 호출 ");
			System.out.println(" C : DB사용ㅇ, 페이지 이동");
			
			// BoardWriteAction 객체 생성
			action = new BoardWriteAction(); // 업캐스팅
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardList.bo")) { // 글쓰기 리스트 페이지
			System.out.println(" C : /BoardList.bo 호출 ");
			System.out.println(" C : DB사용ㅇ, 해당 페이지 출력 ");
			
			// BoardListAction 객체 생성
			action = new BoardListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardContent.bo")){
			System.out.println(" C : /BoardContent.bo 호출");
			System.out.println(" C : DB사용, 화면에 출력");
			
			// BoardContentAction 객체 생성
			action = new BoardContentAction();
			
			try {
				forward = action.execute(request, response);
				System.out.println(" c : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardUpdate.bo")){
			System.out.println(" C : /BoardUpdate.bo 호출 ");
			System.out.println(" C : DB정보 가져와서, 화면에 출력 ");
			
			// BoardUpdateAction 객체 생성
			action = new BoardUpdateAction();
			
			try {
				forward = action.execute(request, response);
				System.out.println(" C : " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardUpdateProAction.bo")){
			System.out.println(" C : /BoardUpdateProAction.bo 호출 ");
			System.out.println(" C : DB사용(수정), 페이지 이동 ");
			
			// BoardUpdateProAction 객체 생성
			action = new BoardUpdateProAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardDelete.bo")){
			System.out.println(" C : /BoardDelete.bo 호출 ");
			System.out.println(" C : DB사용X, 화면출력(view)");
			
			forward = new ActionForward();
			forward.setPath("./center/deleteForm.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/BoardDeleteAction.bo")){
			System.out.println(" C : /BoardDeleteAction.bo 호출");
			System.out.println(" C : DB사용, 페이지 이동 ");
			
			// BoardDeleteAction 객체 생성
			action = new BoardDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardRewrite.bo")){
			System.out.println(" C : /BoardRewrite.bo 호출");
			System.out.println(" C : DB 사용x, view 출력");
			
			forward = new ActionForward();
			forward.setPath("./center/boardReWrite.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/BoardReWriteAction.bo")){
			System.out.println(" C : /BoardReWriteAction.bo 호출 ");
			System.out.println(" C : 디비 사용O, 페이지이동 ");
			
			// BoardReWriteAction 객체 생성
			action = new BoardReWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/BoardFileUpload.bo")){
			System.out.println(" C : /BoardFileUpload.bo 호출" );
			System.out.println(" C : DB사용 X, view 페이지 이동");
			
			forward = new ActionForward();
			forward.setPath("./center/boardUpload.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/BoardFileUploadAction.bo")){
			System.out.println(" C : /BoardFileUploadAction.bo 호출");
			System.out.println(" C : 업로드, DB사용 ㅇ, view페이지 이동");
			
			// BoardFileUploadAction 객체
			action = new BoardFileUploadAction();
			
			//exeucte 호출
			try {
				forward = action.execute(request, response);
				System.out.println(" C forward: " + forward);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		System.out.println(" C : 2. 가상 주소 매핑 끝\n ");
		//////////////////////////////2. 가상 주소 매핑 /////////////////////////////////
		//////////////////////////////3. 페이지 이동 /////////////////////////////////
		System.out.println(" C : 3. 페이지 이동 시작");
		if(forward != null) { // 페이지 이동정보가 있을 때
			if(forward.isRedirect()) { // true
				System.out.println(" C : redirect 방식, "+forward.getPath()+"로 이동");
				response.sendRedirect(forward.getPath());
			} else { //false
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				System.out.println(" C : forward방식, "+forward.getPath()+"로 이동");
				dis.forward(request, response);
			} 
		}
		
		System.out.println(" C : 3. 페이지 이동 끝\n");
		//////////////////////////////3. 페이지 이동 /////////////////////////////////
		
		
	} // doProcess
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" BoardFrontController - doGET() 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" BoardFrontController - doPOST() 호출");
		doProcess(request, response);
	}

	
}
