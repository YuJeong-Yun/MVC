package com.itwillbs.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.goods.db.GoodsDAO;

public class GoodsDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : GodosDetailAction_execute() 실행 ");
		
		// 전달 정보 저장
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DAO 객체 생성
		GoodsDAO dao = new GoodsDAO();
		GoodsDTO dto = dao.getGoods(num);
		System.out.println("dto : "+dto);
		
		request.setAttribute("dto", dto);
		
		// ./goods/goods_detail.jsp
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_detail.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
