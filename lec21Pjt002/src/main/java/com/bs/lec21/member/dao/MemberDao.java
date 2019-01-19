package com.bs.lec21.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bs.lec21.member.Member;

@Repository
public class MemberDao implements IMemberDao {

	// 해쉬맵 대신 디비 연결
	private String driver = "oracle.jdbc.driver.ORacleDriver";
	private String url = "jdbc:oracle:thin:@localhost:59161:xe";
	private String userId = "system";
	private String userPw = "oracle";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private HashMap<String, Member> dbMap;

	public MemberDao() {
//		dbMap = new HashMap<String, Member>();
	}

	@Override
	public int memberInsert(Member member) {

		int result = 0;
		try {
			// 드라이버 로딩
			Class.forName(driver);

			// 연결 객체 불러오기
			conn = DriverManager.getConnection(url, userId, userPw);

			// 쿼리문 작성
			String sql = "INSERT INTO member(memId, memPw, memMail) values(?,?,?)";

			// 실제 쿼리 전송
			// 1번째로 이 값을 보내라
			pstmt.setString(1, member.getMemId());
			// 2번째로 이 값을 보내라
			pstmt.setString(2, member.getMemPw());
			// 3번째
			pstmt.setString(3, member.getMemMail());
			// 실행
			// result = 성공한 횟수. 1이 나오면 성공했다는 뜻
			result = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

//		dbMap.put(member.getMemId(), member);
		return result;

	}

	@Override
	public Member memberSelect(Member member) {

		Member mem = dbMap.get(member.getMemId());
		return mem;

	}

	@Override
	public Member memberUpdate(Member member) {

		dbMap.put(member.getMemId(), member);
		return dbMap.get(member.getMemId());

	}

	@Override
	public Map<String, Member> memberDelete(Member member) {

		dbMap.remove(member.getMemId());
		return dbMap;

	}

}
