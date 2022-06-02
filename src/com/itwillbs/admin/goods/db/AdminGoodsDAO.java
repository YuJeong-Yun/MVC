package com.itwillbs.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.itwillbs.board.action.ActionForward;
import com.itwillbs.order.db.OrderDTO;

public class AdminGoodsDAO {

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


	// insertGoods(goods)
	public void insertGoods(GoodsDTO dto) {
		int goodsNum = 0;
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. SQL작성 & pstmt 객체
			sql = "select max(num) from itwill_goods";
			pstmt = con.prepareStatement(sql);
			// 4. SQL 실행 (상품번호 계산, 상품 등록)
			rs = pstmt.executeQuery();
			// 5. 데이터 처리 
			if(rs.next()) {
				goodsNum = rs.getInt(1) + 1;
			}
			
			///////////////////////////////////////////////////////////
			// 상품 등록
			
			// 3. sql 작성 & pstmt 객체
			sql="insert into itwill_goods values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, goodsNum);
			pstmt.setString(2, dto.getCategory());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getSize());
			pstmt.setString(6, dto.getColor());
			pstmt.setInt(7, dto.getAmount());
			pstmt.setInt(8, dto.getPrice());
			pstmt.setString(9, dto.getImage());
			pstmt.setInt(10, dto.getBest());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	} // insertGoods
	
	
	// getGoodsList()
	public List<GoodsDTO> getGoodsList() {
		
		List<GoodsDTO> goodsList = new ArrayList<GoodsDTO>();
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 (select) & pstmt 객체
			sql = "select * from itwill_goods";
			pstmt = con.prepareStatement(sql);
			// 4. slq 실행
			rs = pstmt.executeQuery();
			// 5. 데이터처리 (DB -> DTO -> Array)
			while(rs.next()){
				GoodsDTO dto = new GoodsDTO();
				
				dto.setAmount(rs.getInt("amount"));
				dto.setBest(rs.getInt("best"));
				dto.setCategory(rs.getString("category"));
				dto.setColor(rs.getString("color"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setPrice(rs.getInt("price"));
				dto.setSize(rs.getString("size"));
				
				goodsList.add(dto);
			}
			System.out.println(" DAO : 관리자 상품목록 조회 성공 ");
			
		} catch (Exception e) {
			System.out.println(" DAO : 관리자 상품목록 조회 실패 ");
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return goodsList;
	} // getGoodsList
	
	
	// getGoods(num)
	public GoodsDTO getGoods(int num) {
		GoodsDTO dto = null;
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체 생성
			sql = "select * from itwill_goods where num = ?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리

			if(rs.next()) {
				dto = new GoodsDTO();
				
				dto.setAmount(rs.getInt("amount"));
				dto.setBest(rs.getInt("best"));
				dto.setCategory(rs.getString("category"));
				dto.setColor(rs.getString("color"));
				dto.setContent(rs.getString("content"));
				dto.setDate(rs.getDate("date"));
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setNum(rs.getInt("num"));
				dto.setPrice(rs.getInt("price"));
				dto.setSize(rs.getString("size"));
			}
			System.out.println(" DAO : "+num+"번 상품정보 저장완료 ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return dto;
	} // getGoods(num)


	// modifyGoods()
	public void modifyGoods(GoodsDTO dto) {
		
		try {
			// 1.2. 디비연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "update itwill_goods set category=?,price=?,name=?,color=?,amount=?,size=?,content=?,best=? where num=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, dto.getCategory());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getColor());
			pstmt.setInt(5, dto.getAmount());
			pstmt.setString(6, dto.getSize());
			pstmt.setString(7, dto.getContent());
			pstmt.setInt(8, dto.getBest());
			pstmt.setInt(9, dto.getNum());
			
			// 4. sql 실행 
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 관리자 상품 정보 수정 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	} // modifyGoods()


	// deleteGoods(num)
	public void deleteGoods(int num) {
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql 작성 & pstmt 객체
			sql = "delete from itwill_goods where num=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 관리자 상품 정보 삭제 완료 ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	} // deleteGoods(num)

	
	// getAdminOrderList
	public List<OrderDTO> getAdminOrderList() {
		List<OrderDTO> adminOrderList = new ArrayList<>();
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			// 3. sql & pstmt
			sql = "select o_m_id, o_trade_num, o_g_name, o_g_amount, o_g_size, o_g_color, "
					+ "sum(o_sum_money) as o_sum_money, o_trans_num, o_date,"
					+ "o_status, o_trade_type from itwill_order "
					+ "group by o_trade_num order by o_trade_num";
			
			pstmt = con.prepareStatement(sql);
			
			// 4. 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				
				dto.setO_m_id(rs.getString("o_m_id"));
				dto.setO_trade_num(rs.getString("o_trade_num"));
				dto.setO_g_name(rs.getString("o_g_name"));
				dto.setO_g_amount(rs.getInt("o_g_amount"));
				dto.setO_g_size(rs.getString("o_g_size"));
				dto.setO_g_color(rs.getString("o_g_color"));
				dto.setO_sum_money(rs.getInt("o_sum_money"));
				dto.setO_trans_num(rs.getString("o_trans_num"));
				dto.setO_date(rs.getDate("o_date"));
				dto.setO_status(rs.getInt("o_status"));
				dto.setO_trade_type(rs.getString("o_trade_type"));
				
				adminOrderList.add(dto);
			}

			System.out.println(" DAO : 주문정보 저장완료 ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return adminOrderList;
	} // getAdminOrderList


	// adminOrderDetail()
	public List adminOrderDetail(String trade_num) {
		
		List<OrderDTO> orderDetailList = new ArrayList<>();
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql & pstmt
			sql = "select * from itwill_order where o_trade_num=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setString(1, trade_num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()) {
				OrderDTO dto = new OrderDTO();
				
				dto.setO_trade_num(rs.getString("o_trade_num"));
				dto.setO_g_name(rs.getString("o_g_name"));
				dto.setO_g_amount(rs.getInt("o_g_amount"));
				dto.setO_g_size(rs.getString("o_g_size"));
				dto.setO_g_color(rs.getString("o_g_color"));
				dto.setO_sum_money(rs.getInt("o_sum_money"));
				dto.setO_trans_num(rs.getString("o_trans_num"));
				dto.setO_date(rs.getDate("o_date"));
				dto.setO_status(rs.getInt("o_status"));
				dto.setO_trade_type(rs.getString("o_trade_type"));
				
				orderDetailList.add(dto);
			}
			System.out.println(" DAO : 주문 상세정보 저장완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return orderDetailList;
		
	} // adminOrderDetail()


	// updateOrder
	public void updateOrder(OrderDTO dto) {
		
		try {
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql & pstmt
			sql = "update itwill_order set o_status=?, o_trans_num=? "
					+ "where o_trade_num=?";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, dto.getO_status());
			pstmt.setString(2, dto.getO_trans_num());
			pstmt.setString(3, dto.getO_trade_num());
			
			// 4. 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 주문 상태, 운송장 번호 변경완료 ");
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	} // updateOrder

}
