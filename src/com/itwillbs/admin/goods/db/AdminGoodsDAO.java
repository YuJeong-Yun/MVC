package com.itwillbs.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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

}
