package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;

public class AdminOrderModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : AdminOrderModifyAction_execute 호출 ");
		
		// 전달된 정보 저장
		String trade_num = request.getParameter("trade_num");
		
		// AdminGoodsDAO - AdminOrderDetail(trade_num)
		AdminGoodsDAO dao = new AdminGoodsDAO();
		request.setAttribute("orderDetailList", dao.adminOrderDetail(trade_num));
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./adminGoods/admin_order_detail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
