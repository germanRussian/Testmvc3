package mapper;

import java.sql.*;

import domain.BoardVO;

public class UpdateMapper {

	public void update(BoardVO vo) {
		// JDBC 프로그래밍
		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE gallery SET title = ?, content = ?, writer = ?, writerDate = (now()), realFileName = ?, realSaveFileName = ? ");
		sql.append(" where num = ? ");

		Connection conn = null;
		PreparedStatement stmt = null;
		int res = 0;

		try {
			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			conn = DriverManager.getConnection(url, user, password);
			// SQL
			stmt = conn.prepareStatement(sql.toString());
			// 값 설정(쿼리 문 '?'에 들어 갈 값)
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setString(3, vo.getWriter());
			stmt.setString(4, vo.getRealFileName());
			stmt.setString(5, vo.getRealSaveFileName());

			stmt.setInt(6, vo.getNum());

			// 출력
			res = stmt.executeUpdate();

		} catch (Exception e) {
			e.getLocalizedMessage();
		} finally {
			// 닫기

			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
