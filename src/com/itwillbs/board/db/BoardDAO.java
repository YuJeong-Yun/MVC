package com.itwillbs.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardDAO {
	// 디비에 itwill_board 테이블과 관련된 모든 동작을 처리
	
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

	
	
	// 글쓰기 메서드
	public void insertBoard(BoardDTO bb) {
		// 글번호 저장 변수
		int num = 0;
		try {
			// 1.2. 디비연결
			con = getCon();
			
			// 3 sql 작성 (글 번호를 계산하는 sql) & pstmt 객체
			sql = "select max(num) from itwill_board";
			pstmt = con.prepareStatement(sql);

			// 4 sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			// max(num) 컬럼의 결과는 항상 존재함(커서 있음 (▶ 모양))
			// 따라서 값이 안들어있어도 rs.next() 결과값이 false가 아님!
			if(rs.next()) {  

				// getInt 메서드는 null값일 경우 0을 반환
				num = rs.getInt(1)+1;   			// 인덱스
				// num = rs.getInt("max(num)")+1;  // 컬럼명
			}
			
			System.out.println("DAO  : 글번호 " + num);
			
			////////////////////////////////////////////////////////////
			// 글쓰기
			
			// 앞에서 DB 연결했으므로 1,2 단계 생략함
			// 3. sql(insert) 작성 & pstmt 객체
			sql = "insert into itwill_board(num, name, pass, subject, content, readcount, re_ref, re_lev, re_seq, date, ip, file) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?)";
			pstmt = con.prepareStatement(sql);
			// ???
			pstmt.setInt(1, num);  // 직접 계산한 글번호
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPass());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setInt(6, 0); // readcount	(생성된 모든 글의 조회수는 0)
			pstmt.setInt(7, num);  // re_ref 	답글->그룹번호    일반글은 글번호와 동일
			pstmt.setInt(8, 0);  // re_lev	답글->들여쓰기	일반글은 0
			pstmt.setInt(9, 0);  // re_seq	답글->순서	일반글은 0
			pstmt.setString(10, bb.getIp());
			pstmt.setString(11, bb.getFile());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 글쓰기 완료! ");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	} // insertBoard
	
}
