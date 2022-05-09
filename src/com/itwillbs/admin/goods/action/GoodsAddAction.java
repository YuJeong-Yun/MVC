package com.itwillbs.admin.goods.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itwillbs.admin.goods.db.AdminGoodsDAO;
import com.itwillbs.admin.goods.db.GoodsDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GoodsAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : GoodsAddAction_execute() 호출");
		
		// 파일업로드
		// 업로드할 폴더 생성(가상폴더)
		ServletContext ctx = request.getServletContext();
		String realPath = ctx.getRealPath("/shopUpload");
		
		//System.out.println(" M : realPath : "+realPath);
		int maxSize = 5 * 1024 * 1024; // 5MB
		
		MultipartRequest multi 
		     = new MultipartRequest(
		    		 request,
		    		 realPath,
		    		 maxSize,
		    		 "UTF-8",
		    		 new DefaultFileRenamePolicy()
		    		 );
		
		System.out.println(" M : 파일 업로드 완료!!! ");
		
		
		// 상품정보 저장(파라메터) => GoodsDTO
		// (카테고리,상품명,가격,컬러,수량,사이즈,제품정보)
		
		GoodsDTO goods = new GoodsDTO();
		goods.setCategory(multi.getParameter("category"));
		goods.setName(multi.getParameter("name"));
		goods.setPrice(Integer.parseInt(multi.getParameter("price")));
		goods.setColor(multi.getParameter("color"));
		goods.setAmount(Integer.parseInt(multi.getParameter("amount")));
		goods.setSize(multi.getParameter("size"));
		goods.setContent(multi.getParameter("content"));
		
		// 인기상품,이미지,날짜(DAO)
		goods.setBest(0); // 0:일반상품, 1:인기상품
		
		String image = multi.getFilesystemName("file1")+","
		              +multi.getFilesystemName("file2")+","
		              +multi.getFilesystemName("file3")+","
		              +multi.getFilesystemName("file4");
		// => 이미지1,이미지2,이미지3,이미지4
		System.out.println(" M : image : "+image);
		goods.setImage(image);
		
		// DAO 객체 생성 - insertGoods()
		AdminGoodsDAO dao = new AdminGoodsDAO();
		
		dao.insertGoods(goods);
		
		// 페이지 이동(list)
		ActionForward forward = new ActionForward();
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}

}
