package com.itwillbs.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.or")
public class OrderFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println(" OrderFrontController - doPROCESS() 호출");
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
				
		if(command.equals("/OrderStart.or")) {
			System.out.println(" C : /OrderStart.or 호출 ");
			// DB 사용 ㅇ, 화면에 출력
			
			// OrderStartAction 객체
			action = new OrderStartAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/OrderAdd.or")) {
			System.out.println(" C : /OrderAdd.or 호출");
			// DB O, 페이지 이동
			
			// OrderAddAction
			action = new OrderAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/OrderList.or")) {
			System.out.println(" C : /OrderList.or 호출");
			// OrderListAction() 객체(DB 사용, 화면 출력)
			
			action = new OrderListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/OrderDetail.or")) {
			System.out.println(" C : /OrderDetail.or 호출");
			// OrderDetailAction (DB 사용, 화면출력)
			
			action = new OrderDetailAction();
			
			try {
				forward = action.execute(request, response);
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
