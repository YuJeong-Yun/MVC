package com.itwillbs.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.admin.goods.db.GoodsDTO;
import com.itwillbs.basket.db.BasketDTO;

public class OrderDAO {
		// 공통변수 선언
		private Connection con = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String sql = "";


		// 디비연결 메서드
		private Connection getCon() throws Exception{
			// 1.2. 디비 연결

			// 1) 프로젝트 정보를 초기화
			//   Context => 내 프로젝트를 의미
			Context initCTX = new InitialContext();
			// 2) 프로젝트에 저장된 리소스 정보를 불러오기
			DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/board");
			// 3) 디비연결
			con = ds.getConnection();

			System.out.println("DAO : 디비연결 성공(커넥션풀) ");
			System.out.println("DAO : " + con);

			return con;
		}
		// 디비연결 메서드
		
		
		// 디비 자원해제 메서드
		public void closeDB() {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
				System.out.println("DAO : 디비 연결 자원해제!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 디비 자원해제 메서드
		////////////////////////////////////////////////////////////////////


	// addOrder(OrderDTO dto, List basketList, List goodsList)
	public void addOrder(OrderDTO dto, List basketList, List goodsList) {
		// 일련번호(테이블)
		int o_num = 0;
		// 주문번호
		int trade_num = 0;
		
		// 날짜 정보 (시스템 날짜/시간정보)
		Calendar cal = Calendar.getInstance();
		// 날짜 포맷객체
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		// 일련번호 계산하기
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. slq 작성 & pstmt 객체
			sql = "select max(o_num) from itwill_order";
			pstmt = con.prepareStatement(sql);
			// 4. sql 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if(rs.next()) {
				o_num = rs.getInt(1)+1;
			}
			trade_num = o_num;
			System.out.println("DAO : o_num="+o_num+",trade_num="+trade_num);
			
			// 주문정보 저장
			// (배송, 결제) + (상품+장바구니)
			
			for(int i=0; i<basketList.size();i++) {
				BasketDTO bkDTO = (BasketDTO) basketList.get(i);
				GoodsDTO gDTO = (GoodsDTO) goodsList.get(i);
				
				sql = "insert into itwill_order "
						+ "values(?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,now(),?,now(),?)";
				pstmt = con.prepareStatement(sql);
				// ???
				pstmt.setInt(1, o_num); // 일련번호
				pstmt.setString(2, sdf.format(cal.getTime())+"-"+trade_num); // 주문번호 : 날짜-일련번호
				pstmt.setInt(3, bkDTO.getB_g_num()); // 상품번호
				pstmt.setString(4, gDTO.getName()); // 이름
				pstmt.setInt(5, bkDTO.getB_g_amount()); // 수량
				pstmt.setString(6, bkDTO.getB_g_size()); // 사이즈
				pstmt.setString(7, bkDTO.getB_g_color()); // 컬러
				
				pstmt.setString(8, dto.getO_m_id()); // ID
				pstmt.setString(9, dto.getO_r_name()); // 받는사람
				pstmt.setString(10, dto.getO_r_addr1()); // 주소1
				pstmt.setString(11, dto.getO_r_addr2()); // 주소2
				pstmt.setString(12, dto.getO_r_phone()); // 연락처
				pstmt.setString(13, dto.getO_r_msg()); // 메모
				
				pstmt.setInt(14, bkDTO.getB_g_amount() * gDTO.getPrice()); // 해당 주문 총 금액
				pstmt.setString(15, dto.getO_trade_type()); // 결제 타입
				pstmt.setString(16, dto.getO_trade_payer()); // 결제자 명
				pstmt.setString(17, "운송장번호 준비중.."); // 운송장 번호
				pstmt.setInt(18, 0); // 주문상태
				// 0-대기중, 1-발송준비, 2-발송완료, 3-배송중, 4-배송완료, 5-주문취소
				
				pstmt.executeUpdate();
				System.out.println("DAO : 주문 생성완료! ("+o_num+")");
				// 일련번호 증가
				o_num++;
			
			} //for
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	} // addOrder(OrderDTO dto, List basketList, List goodsList)
		
}
