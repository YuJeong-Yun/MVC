package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;

public class GoodsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : GoodsDeleteAction_execute() 호출 ");
		
		// 전달정보 저장
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DAO 객체 생성 - 정보 삭제 메서드 호출 (deleteGoods()) 
		AdminGoodsDAO dao = new AdminGoodsDAO();
		dao.deleteGoods(num); 
		
		// 페이지 이동 (list)
		ActionForward forward = new ActionForward();
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true);
		return forward;
	}

}
