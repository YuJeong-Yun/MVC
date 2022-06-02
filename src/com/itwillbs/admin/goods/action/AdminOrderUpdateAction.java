package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.order.db.OrderDTO;

public class AdminOrderUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : AdminOrderUpdateAction_execute() 호출");
		
		// 전달정보 (trade_num, status, trans_num)
		OrderDTO dto = new OrderDTO();
		dto.setO_trade_num(request.getParameter("trade_num"));
		dto.setO_status(Integer.parseInt(request.getParameter("status"))); // string 붙여야함??
		dto.setO_trans_num(request.getParameter("trans_num"));
		
		// AdminGoodsDAO - updateOrder(dto)
		AdminGoodsDAO dao = new AdminGoodsDAO();
		dao.updateOrder(dto);
		
		// 페이지 이동 /OrderListAll.ag
		ActionForward forward = new ActionForward();
		forward.setPath("./OrderListAll.ag");
		forward.setRedirect(true);
		
		return forward;
	}

}
