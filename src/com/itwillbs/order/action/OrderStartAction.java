package com.itwillbs.order.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;

public class OrderStartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : OrderStartAction_execute() 호출");
		
		// 세션제어
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./GoodsList.go");
			forward.setRedirect(true);
			
			return forward;
		}
		
		// 장바구니+상품정보 각각 저장
		BasketDAO bkDao = new BasketDAO();
		Vector totalList = bkDao.getBasketList(id);
		
		// member관련 동작은 처리 안했음!! 
		// 회원 정보(MemberDAO -> 객체)
		// MebrmberDTO dto = mDao.getMember(id);
		
		// DB 에서 가져온 정보를 reqeust 영역에 저장
		// 장바구니, 상품정보, 회원정보
		request.setAttribute("basketList", totalList.get(0));
		request.setAttribute("goodsList", totalList.get(1));
		request.setAttribute("memberDTO", "dtoOK");
		
		// 전달("./goods_order/goods_buy.jsp");
		forward.setPath("./goods_order/goods_buy.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
