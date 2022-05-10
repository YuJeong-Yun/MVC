package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class GoodsModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : GoodsModifyAction.execute() 호출 ");
		// 상품번호
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println(" M : 상품번호 : " + num);
		
		// DAO 객체 생성 - 상품번호에 해당하는 상품정보모두를 가져오는 동작
		AdminGoodsDAO dao = new AdminGoodsDAO();
		GoodsDTO dto = dao.getGoods(num);
		
		// request 영역에 저장
		request.setAttribute("dto", dto);

		// * 참고 - Action 페이지에서 session 객체 사용
		// HttpSession session = request.getSession();
		
		
		// 페이지 이동(./admin_goods_modify.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./adminGoods/admin_goods_modify.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
