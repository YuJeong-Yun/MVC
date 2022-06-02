package com.itwillbs.order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.order.db.OrderDAO;
import com.itwillbs.order.db.OrderDTO;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : OrderListAction_execute() 호출");
		
		// 로그인 처리
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id==null){
			forward.setPath("./GoodsList.go");
			forward.setRedirect(false);
			return forward;
		}
		
		// OrderDAO 객체 - getOrderList(id)
		OrderDAO dao = new OrderDAO();
		List<OrderDTO> orderList =  dao.getOrderList(id);
		
		// request 영역에 저장
		request.setAttribute("orderList", orderList);
		
		// 페이지 이동
		forward.setPath("./goods_order/order_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
