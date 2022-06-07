package mapper;

import java.sql.*;
import java.util.*;

import domain.BoardVO;

public class ListMapper {

	// 목록 생성 메소드
	public Collection<BoardVO> read() {
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT num, title, writer, writerDate FROM board ");
		sql.append(" order by num desc");

		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo = null;
		try {

			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			conn = DriverManager.getConnection(url, user, password);
			// SQL
			stmt = conn.prepareStatement(sql.toString());
			// 출력
			rs = stmt.executeQuery();

			while (rs.next()) {

				vo = new BoardVO();

				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriterDate(rs.getTimestamp("writerDate"));

				list.add(vo);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// 게시물 갯수
	public int totalRow() {

		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*)as cnt FROM board ");
		sql.append(" order by num desc");

		int totalRow = 0;

		try {

			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			conn = DriverManager.getConnection(url, user, password);
			// SQL
			stmt = conn.prepareStatement(sql.toString());

			// 출력
			rs = stmt.executeQuery();

			if (rs.next()) {

				totalRow = rs.getInt("cnt");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return totalRow;

	}

	// 검색에 대한 게시물 수1
	public int totalRow(String keyWord) {
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*)as cnt FROM board ");
		sql.append(" where title like concat('%',?,'%') ");
		sql.append(" order by num desc");

		int totalRow = 0;

		try {

			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			conn = DriverManager.getConnection(url, user, password);
			// SQL
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, keyWord);

			// 출력
			rs = stmt.executeQuery();

			if (rs.next()) {

				totalRow = rs.getInt("cnt");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return totalRow;

	}

	// 페이징1
	public Collection<BoardVO> read(int startPage, int pageRow, String field, String keyWord) {
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM board ");
		sql.append(" where 1=1 ");

		if ("title".equals(field)) {
			sql.append("and title like concat('%',?,'%') ");
		}
		if ("content".equals(field)) {
			sql.append("and content like concat('%',?,'%') ");
		}
		if ("writer".equals(field)) {
			sql.append("and writer like concat('%',?,'%') ");
		}
		if ("titleContent".equals(field)) {
			sql.append("and ( title like concat('%',?,'%') or content like concat('%',?,'%')) ");
		}
		if ("titleContentAnd".equals(field)) {
			sql.append("and ( title like concat('%',?,'%') and content like concat('%',?,'%')) ");
		}

		sql.append(" order by num desc limit ?,? ");

		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo = null;
		int idx = 1;

		try {

			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			conn = DriverManager.getConnection(url, user, password);
			// SQL
			stmt = conn.prepareStatement(sql.toString());
			// 검색
			if ("title".equals(field)) {
				stmt.setString(idx++, keyWord);
			}
			if ("content".equals(field)) {
				stmt.setString(idx++, keyWord);
			}
			if ("writer".equals(field)) {
				stmt.setString(idx++, keyWord);
			}
			if ("titleContent".equals(field)) {
				stmt.setString(idx++, keyWord);
				stmt.setString(idx++, keyWord);
			}
			if ("titleContentAnd".equals(field)) {
				stmt.setString(idx++, keyWord);
				stmt.setString(idx++, keyWord);
			}

			stmt.setInt(idx++, startPage);
			stmt.setInt(idx++, pageRow);

			// 출력
			rs = stmt.executeQuery();

			while (rs.next()) {

				vo = new BoardVO();

				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriterDate(rs.getTimestamp("writerDate"));
				vo.setRealFileName(rs.getString("realFileName"));
				vo.setRealSaveFileName(rs.getString("realSaveFileName"));

				list.add(vo);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// 검색에 대한 게시물 수2
	public int totalRow1( String keyWord1, String keyWord2, String keyWord3) {
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*)as cnt FROM board ");
		sql.append(
				" where (title like concat('%',?,'%') and writer like concat('%',?,'%')  and content like concat('%',?,'%')) ");
		sql.append(" order by num desc");

		int inx = 1;
		int totalRow = 0;

		try {

			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			conn = DriverManager.getConnection(url, user, password);
			// SQL
			stmt = conn.prepareStatement(sql.toString());
			//종합 검색
			if(keyWord2==null && keyWord3==null) {
				stmt.setString(inx++, keyWord1);
			}
			if(keyWord3==null) {
				stmt.setString(inx++, keyWord1);
				stmt.setString(inx++, keyWord2);
			}
			if(keyWord1 != null && keyWord2 != null && keyWord3 != null) {
				stmt.setString(inx++, keyWord1);
				stmt.setString(inx++, keyWord2);
				stmt.setString(inx++, keyWord3);
			}
//			stmt.setString(1, keyWord1);
//			stmt.setString(2, keyWord2);
//			stmt.setString(3, keyWord3);
//			
			

			// 출력
			rs = stmt.executeQuery();

			if (rs.next()) {

				totalRow = rs.getInt("cnt");

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return totalRow;

	}

	// 페이징2
	public Collection<BoardVO> read1(int startPage, int pageRow, String field1, String keyWord1, String keyWord2,
			String keyWord3) {
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM board ");
		sql.append(" where 1=1 ");

		if ("title".equals(field1)) {
			sql.append("and title like concat('%',?,'%')  ");
		}
		if ("titleWriter".equals(field1)) {
			sql.append("and ( title like concat('%',?,'%') and writer like concat('%',?,'%')) ");
		}
		if ("titleWriterContent".equals(field1)) {
			sql.append(
					"and ( title like concat('%',?,'%') and writer like concat('%',?,'%') and content like concat('%',?,'%')) ");
		}

		sql.append(" order by num desc limit ?,? ");

		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo = null;

		int idxx = 1;

		try {

			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			conn = DriverManager.getConnection(url, user, password);
			// SQL
			stmt = conn.prepareStatement(sql.toString());
			// 검색

			// 종합 검색
			if ("title".equals(field1)) {
				stmt.setString(idxx++, keyWord1);
			}
			if ("titleWriter".equals(field1)) {
				stmt.setString(idxx++, keyWord1);
				stmt.setString(idxx++, keyWord2);
			}
			if ("titleWriterContent".equals(field1)) {
				stmt.setString(idxx++, keyWord1);
				stmt.setString(idxx++, keyWord2);
				stmt.setString(idxx++, keyWord3);
			}

			stmt.setInt(idxx++, startPage);
			stmt.setInt(idxx++, pageRow);

			// 출력
			rs = stmt.executeQuery();

			while (rs.next()) {

				vo = new BoardVO();

				vo.setNum(rs.getInt("num"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setWriterDate(rs.getTimestamp("writerDate"));
				vo.setRealFileName(rs.getString("realFileName"));
				vo.setRealSaveFileName(rs.getString("realSaveFileName"));

				list.add(vo);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

}
