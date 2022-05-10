package com.itwillbs.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;

public class GoodsModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : GoodsModifyProAction.execute() 호출 ");
		
		// 한글처리-생략
		// 전달정보 저장 (카테고리, 가격, 이름, 컬러, 수량, 사이즈, 상품정보, 인기상품)
		// => DTO에 저장
		GoodsDTO dto = new GoodsDTO();
		
		dto.setAmount(Integer.parseInt(request.getParameter("amount")));
		dto.setBest(Integer.parseInt(request.getParameter("best")));
		dto.setCategory(request.getParameter("category"));
		dto.setColor(request.getParameter("color"));
		dto.setContent(request.getParameter("content"));
		dto.setName(request.getParameter("name"));
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setPrice(Integer.parseInt(request.getParameter("price")));
		dto.setSize(request.getParameter("size"));
		
		// DAO 객체 생성 - 정보 수정 메서드 호출 (modifyGoods()) 
		AdminGoodsDAO dao = new AdminGoodsDAO();
		dao.modifyGoods(dto); 
		
		// 페이지 이동 (list)
		ActionForward forward = new ActionForward();
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}

}
