package com.itwillbs.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.order.db.OrderDAO;

public class OrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : OrderDetailAction_execute() 호출");
		
		// 로그인 처리(생략)
		
		// 전달한 정보(trade_num) 저장
		String trade_num = request.getParameter("trade_num");
		
		// OrderDAO - orderDetail
		OrderDAO dao = new OrderDAO();
		// request 영역 저장
		request.setAttribute("detailList", dao.orderDetail(trade_num));

		ActionForward forward = new ActionForward();
		forward.setPath("./goods_order/order_detail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
