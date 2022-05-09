package com.itwillbs.admin.goods.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.board.action.Action;
import com.itwillbs.board.action.ActionForward;
import com.itwillbs.board.action.BoardContentAction;
import com.itwillbs.board.action.BoardDeleteAction;
import com.itwillbs.board.action.BoardFileUploadAction;
import com.itwillbs.board.action.BoardListAction;
import com.itwillbs.board.action.BoardReWriteAction;
import com.itwillbs.board.action.BoardSearchAction;
import com.itwillbs.board.action.BoardUpdateAction;
import com.itwillbs.board.action.BoardUpdateProAction;
import com.itwillbs.board.action.BoardWriteAction;

public class AdminGoodsFrontController extends HttpServlet {

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
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doGet() -> doProcess()");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" C : doPost() -> doProcess()");
		doProcess(request, response);
	}
	

}