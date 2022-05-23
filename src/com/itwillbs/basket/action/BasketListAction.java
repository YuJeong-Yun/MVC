package com.itwillbs.basket.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;

public class BasketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : BasketListAction_execute 호출 ");
		
		// 세션제어(로그인제어)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./GoodsList.go");
			forward.setRedirect(true);
			return forward;
		}
		
		
		// DAO 객체 - 장바구니 정보 가져오기
		BasketDAO dao = new BasketDAO();

		// 장바구니 정보 + 상품정보(필요한 일부정보) 
		Vector totalList = dao.getBasketList(id);
		
		//System.out.println(" M : totalList " + totalList);
		// request 영역에 저장
		//request.setAttribute("totalList", totalList);

		// view페이지에 정보를 전달할때는 데이터를 
		// 바로 사용가능한 형태로 전달 하는것이 좋다.
		request.setAttribute("basketList", totalList.get(0));
		request.setAttribute("goodsList", totalList.get(1));
		
		
		// 페이지 이동("./basket/goods_basket_list.jsp")
		forward.setPath("./basket/goods_basket_list.jsp");
		forward.setRedirect(false);		
		
		return forward;
	}

}
