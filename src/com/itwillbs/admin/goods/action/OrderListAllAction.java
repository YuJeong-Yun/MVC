package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;

public class OrderListAllAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : OrderListAllAction_execute() 호출");
		
		// 로그인 세션(+관리자)
		
		
		// AdminGoodsDAO - getAdminOrderList();
		AdminGoodsDAO dao = new AdminGoodsDAO();
		request.setAttribute("adminOrderList", dao.getAdminOrderList());
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./adminGoods/admin_order_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
