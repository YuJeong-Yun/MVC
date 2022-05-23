package com.itwillbs.order.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itwillbs.basket.db.BasketDAO;
import com.itwillbs.goods.db.GoodsDAO;
import com.itwillbs.order.db.OrderDAO;
import com.itwillbs.order.db.OrderDTO;

public class OrderAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : OrderAddAction_execute() 실행 ");
		
		// 세션제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null) {
			forward.setPath("./GoodsList.go");
			forward.setRedirect(true);
			return forward;
		}
		
		
		// 전달된 정보 저장 (주문) - OrderDTO
		OrderDTO dto = new OrderDTO();
		
		dto.setO_r_name(request.getParameter("o_r_name"));
		dto.setO_r_phone(request.getParameter("o_r_phone"));
		dto.setO_r_addr1(request.getParameter("o_r_addr1"));
		dto.setO_r_addr2(request.getParameter("o_r_addr2"));
		dto.setO_r_msg(request.getParameter("o_r_msg"));
		
		dto.setO_trade_payer(request.getParameter("o_trade_payer"));
		dto.setO_trade_type(request.getParameter("o_trade_type"));
		
		dto.setO_m_id(id);
		
//		System.out.println(dto);
		
		// 장바구니 정보(장바구니+상품)
		BasketDAO bkDAO = new BasketDAO();
		
		Vector totalList = bkDAO.getBasketList(id);
		
		List basketList = (List) totalList.get(0);
		List goodsList = (List)totalList.get(1);
		
		// 결제모듈(API)
		System.out.println("------------------------------------------");
		System.out.println("			KAKAOPAY 실행 완료            ");
		System.out.println("------------------------------------------");
		
		// 주문정보 저장(OrderDAO)
		OrderDAO orDAO = new OrderDAO();
		orDAO.addOrder(dto, basketList, goodsList);
		
		// 메일, 문자 알림 (Thread)
		System.out.println("------------------------------------------");
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5000); // 스레드를 5초간 잠자기(메모리 전환)
					System.out.println("			메일/알림 전송 완료!          ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("------------------------------------------");
		
		// 상품 수량 수정 (Goods 테이블)
		GoodsDAO gdao = new GoodsDAO();
		gdao.updateAmount(basketList);
		
		System.out.println(" M: 구매후 상품 재고 수량 변경");
		
		// 장바구니 삭제(비우기)
		bkDAO.basketDelete(id);
		
		// 페이지 이동 (./OrderList.or)
		forward.setPath("./OrderList.or");
		forward.setRedirect(true);
		
		return forward;
	}

}
