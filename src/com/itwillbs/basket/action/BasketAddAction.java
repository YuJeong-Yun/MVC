package com.itwillbs.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.basket.db.BasketDTO;

public class BasketAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : BasketAddAction_execute 호출 ");
		
		// 사용자정보 체크(로그인여부)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		System.out.println("id "+id);
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			System.out.println(" M : ID 정보 없음 ");
			forward.setPath("./GoodsList.go");
			forward.setRedirect(true);
			return forward;
		}
		
		// 전달된 정보 저장(상품 번호, 구매수량, 구매 크기, 구매 색상, ID)
		// DTO
		BasketDTO dto = new BasketDTO();
		dto.setB_g_num(Integer.parseInt(request.getParameter("num")));
		dto.setB_g_amount(Integer.parseInt(request.getParameter("amount")));
		dto.setB_g_size(request.getParameter("size"));
		dto.setB_g_color(request.getParameter("color"));
		dto.setB_m_id(id);
		
		System.out.println(" M : "+dto);
		
		// BasketDAO 객체
		BasketDAO dao = new BasketDAO();
		// 동일상품 수량만 update - checkGoods(dto)
		int ck = dao.checkGoods(dto);
		// ck == 0 (해당옵션 X, 처음구매)
		// ck == 1 (해당옵션 O, 기존 상품 추가구매)
		
		// 장바구니 저장
		if(ck != 1){ // 기존의 상품이 없음
			dao.basketAdd(dto);
		}
		
		// 페이지 이동 (BasketList.ba)
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);
		
		return forward;
	}

}
