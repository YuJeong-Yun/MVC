package com.itwillbs.admin.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class GoodsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : GoodsListAction_execute() 호출 ");
		
		// DAO 객체 - 상품정보를 모두 가져오는 동작
		AdminGoodsDAO dao = new AdminGoodsDAO();
		
		// request 영역에 저장
		request.setAttribute("goodsList", dao.getGoodsList());
		
		// 페이지 이동 (./admingoods/admin_goods_list.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./adminGoods/admin_goods_list.jsp");
		forward.setRedirect(false);

		return forward;
	}
}
