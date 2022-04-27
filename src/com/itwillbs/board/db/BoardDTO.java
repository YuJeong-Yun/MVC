package com.itwillbs.board.db;

import java.sql.Date;

public class BoardDTO {
	// itwill_board 테이블의 정보를 저장/전송하는 객체

	private int num; 			// 글 번호
	private String name; 		// 글 쓴 사람의 이름
	private String pass; 		// 해당 '글'의 비밀번호 (글 수정, 살제 시 필요)
	private String subject;		// 글 제목
	private String content; 	// 글 내용

	private int readcount; 		// 조회수 - 0부터 시작
	private int re_ref; 		// 답글 - 그룹 번호
	private int re_lev; 		// 답글 - 들여 쓰기
	private int re_seq; 		// 답글 - 순서

	private Date date; 			// 글 작성일 / 시간
	private String ip;			// 작성자 IP
	private String file; 		// 업로드된 파일 정보


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getRe_ref() {
		return re_ref;
	}

	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}

	public int getRe_lev() {
		return re_lev;
	}

	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}

	public int getRe_seq() {
		return re_seq;
	}

	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "BoardBean [num=" + num + ", name=" + name + ", pass=" + pass + ", subject=" + subject + ", content="
				+ content + ", readcount=" + readcount + ", re_ref=" + re_ref + ", re_lev=" + re_lev + ", re_seq="
				+ re_seq + ", date=" + date + ", ip=" + ip + ", file=" + file + "]";
	}


}


