package com.ho.lec24.member.dao;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.ho.lec24.member.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class MemberDao implements IMemberDao {

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:59161:xe";
	private String userid = "system";
	private String userpw = "oracle";

//	private DriverManagerDataSource dataSource; // c3p0
//	private DriverManagerDataSource dataSource;		// spring
	private ComboPooledDataSource dataSource;

	private JdbcTemplate template;

	// dao객체가 생성될 때 딱 한번만 세팅된다.
	public MemberDao() {
		// spring
//		dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);

		// c3p0
//		dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClass(driver);
//		dataSource.setJdbcUrl(url);
//		dataSource.setUser(userid);
//		dataSource.setPassword(userpw);

		// template 설정
		template = new JdbcTemplate();
		template.setDataSource(dataSource); // datasource에 담긴 정보를 template에 담음.
		// 커넥션 풀도 데이터소스를 쓰기 때문에 템플릿을 쓰는 건 똑같다.

		// 커넥션 풀을 이용할 때는 예외처리를 꼭 해줘야 한다.
		dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(driver);
			dataSource.setJdbcUrl(url);
			dataSource.setUser(userid);
			dataSource.setPassword(userpw);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

	}

	/*
	 * private Connection conn = null; private PreparedStatement pstmt = null;
	 * private ResultSet rs = null;
	 */

	@Override
	public int memberInsert(final Member member) {

		int result = 0;

		// 이제 쿼리문만 간단하게 쓸 수 있다.
		// 2, 3번째 방법을 쓸 때는 보안상의 문제로 sql에 final을 붙여준다.
		final String sql = "INSERT INTO member (memId, memPw, memMail) values (?,?,?)";

//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);
//		
//		JdbcTemplate template = new JdbcTemplate();
//		template.setDataSource(dataSource);

//		1st
		result = template.update(sql, member.getMemId(), member.getMemPw(), member.getMemMail());

//		2nd
//		result = template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				pstmt.setString(3, member.getMemMail());
//				
//				return pstmt;
//			}
//		});

//      3rd
//		result = template.update(sql, new PreparedStatementSetter() {
//
//			@Override
//			public void setValues(PreparedStatement pstmt) throws SQLException {
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				pstmt.setString(3, member.getMemMail());
//
//			}
//		});

		return result;

		/*
		 * int result = 0;
		 * 
		 * try { Class.forName(driver); conn = DriverManager.getConnection(url, userid,
		 * userpw); String sql =
		 * "INSERT INTO member (memId, memPw, memMail) values (?,?,?)"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1, member.getMemId());
		 * pstmt.setString(2, member.getMemPw()); pstmt.setString(3,
		 * member.getMemMail()); result = pstmt.executeUpdate(); } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); } catch (SQLException e) {
		 * e.printStackTrace(); } finally { try { if(pstmt != null) pstmt.close();
		 * if(conn != null) conn.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 * 
		 * return result;
		 */
	}

	@Override
	public Member memberSelect(final Member member) {

		List<Member> members = null;

		final String sql = "SELECT * FROM member WHERE memId = ? AND memPw = ?";

//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);
//		
//		JdbcTemplate template = new JdbcTemplate();
//		template.setDataSource(dataSource);

//		1st
		members = template.query(sql, new PreparedStatementSetter() {
			// 실제 넣을 데이터를 매핑하는 방법
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
			}
		}, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				mem.setMemId(rs.getString("memId")); // record set의 정보를 가져옴.
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemMail(rs.getString("memMail"));
				mem.setMemPurcNum(rs.getInt("memPurcNum"));
				return mem;
			}
		});

//		2nd
//		members = template.query(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				return pstmt;
//			}
//		}, new RowMapper<Member>() {
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member mem = new Member();
//				mem.setMemId(rs.getString("memId"));
//				mem.setMemPw(rs.getString("memPw"));
//				mem.setMemMail(rs.getString("memMail"));
//				mem.setMemPurcNum(rs.getInt("memPurcNum"));
//				return mem;
//			}
//		});

//		3rd
//		members = template.query(sql, new RowMapper<Member>() {
//
//			@Override
//			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Member mem = new Member();
//				mem.setMemId(rs.getString("memId"));
//				mem.setMemPw(rs.getString("memPw"));
//				mem.setMemMail(rs.getString("memMail"));
//				mem.setMemPurcNum(rs.getInt("memPurcNum"));
//				return mem;
//			}
//			
//		}, member.getMemId(), member.getMemPw());

//		4th
		members = template.query(sql, new Object[] { member.getMemId(), member.getMemPw() }, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
				Member mem = new Member();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemMail(rs.getString("memMail"));
				mem.setMemPurcNum(rs.getInt("memPurcNum"));
				return mem;
			}

		});

		if (members.isEmpty()) // 가져온 데이터가 없으면 null
			return null;

		return members.get(0);

		/*
		 * Member mem = null;
		 * 
		 * try {
		 * 
		 * Class.forName(driver); conn = DriverManager.getConnection(url, userid,
		 * userpw); String sql = "SELECT * FROM member WHERE memId = ? AND memPw = ?";
		 * pstmt = conn.prepareStatement(sql); pstmt.setString(1, member.getMemId());
		 * pstmt.setString(2, member.getMemPw()); rs = pstmt.executeQuery();
		 * 
		 * while (rs.next()) { String memId = rs.getString("memid"); String memPw =
		 * rs.getString("mempw"); String memMail = rs.getString("memMail"); int
		 * memPurcNum = rs.getInt("memPurcNum");
		 * 
		 * mem = new Member(); mem.setMemId(memId); mem.setMemPw(memPw);
		 * mem.setMemMail(memMail); mem.setMemPurcNum(memPurcNum); }
		 * 
		 * } catch (ClassNotFoundException e) { e.printStackTrace(); } catch
		 * (SQLException e) { e.printStackTrace(); } finally { try { if(rs != null)
		 * rs.close(); if(pstmt != null) pstmt.close(); if(conn != null) conn.close(); }
		 * catch (SQLException e) { e.printStackTrace(); } }
		 * 
		 * return mem;
		 */

	}

	@Override
	public int memberUpdate(final Member member) {

		int result = 0;

		final String sql = "UPDATE member SET memPw = ?, memMail = ? WHERE memId = ?";

//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);
//		
//		JdbcTemplate template = new JdbcTemplate();
//		template.setDataSource(dataSource);

//		1st
//		result = template.update(sql, member.getMemPw(), member.getMemMail(),  member.getMemId());

//		2nd
//		result = template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemPw());
//				pstmt.setString(2, member.getMemMail());
//				pstmt.setString(3, member.getMemId());
//				
//				return pstmt;
//			}
//		});

//		3rd
		result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemPw());
				pstmt.setString(2, member.getMemMail());
				pstmt.setString(3, member.getMemId());
			}
		});
		return result;

		/*
		 * int result = 0;
		 * 
		 * try {
		 * 
		 * Class.forName(driver); conn = DriverManager.getConnection(url, userid,
		 * userpw); String sql =
		 * "UPDATE member SET memPw = ?, memMail = ? WHERE memId = ?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1, member.getMemPw());
		 * pstmt.setString(2, member.getMemMail()); pstmt.setString(3,
		 * member.getMemId()); result = pstmt.executeUpdate();
		 * 
		 * } catch (ClassNotFoundException e) { e.printStackTrace(); } catch
		 * (SQLException e) { e.printStackTrace(); } finally { try { if(pstmt != null)
		 * pstmt.close(); if(conn != null) conn.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 * 
		 * return result;
		 */

	}

	@Override
	public int memberDelete(final Member member) {

		int result = 0;

		final String sql = "DELETE member WHERE memId = ? AND memPw = ?";

//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(driver);
//		dataSource.setUrl(url);
//		dataSource.setUsername(userid);
//		dataSource.setPassword(userpw);
//		
//		JdbcTemplate template = new JdbcTemplate();
//		template.setDataSource(dataSource);

//		1st
//		result = template.update(sql, member.getMemId(), member.getMemPw());

//		2nd
//		result = template.update(new PreparedStatementCreator() {
//			
//			@Override
//			public PreparedStatement createPreparedStatement(Connection conn)
//					throws SQLException {
//				PreparedStatement pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, member.getMemId());
//				pstmt.setString(2, member.getMemPw());
//				
//				return pstmt;
//			}
//		});

//		3rd
		result = template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, member.getMemId());
				pstmt.setString(2, member.getMemPw());
			}
		});

		return result;

		/*
		 * int result = 0;
		 * 
		 * try {
		 * 
		 * Class.forName(driver); conn = DriverManager.getConnection(url, userid,
		 * userpw); String sql = "DELETE member WHERE memId = ? AND memPw = ?"; pstmt =
		 * conn.prepareStatement(sql); pstmt.setString(1, member.getMemId());
		 * pstmt.setString(2, member.getMemPw()); result = pstmt.executeUpdate();
		 * 
		 * } catch (ClassNotFoundException e) { e.printStackTrace(); } catch
		 * (SQLException e) { e.printStackTrace(); } finally { try { if(pstmt != null)
		 * pstmt.close(); if(conn != null) conn.close(); } catch (SQLException e) {
		 * e.printStackTrace(); } }
		 * 
		 * return result;
		 */
	}

}