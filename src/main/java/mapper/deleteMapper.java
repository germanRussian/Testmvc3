package mapper;

import java.sql.*;

import domain.BoardVO;

public class deleteMapper {

	public void delete(int num) {

		/* DB연결 */

		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		Connection conn = null;
		PreparedStatement stmt = null;
	

		StringBuffer sql = new StringBuffer();
		sql.append(" delete FROM gallery");
		sql.append(" where num = ? ");

		

		try {

			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			conn = DriverManager.getConnection(url, user, password);
			// SQL
			stmt = conn.prepareStatement(sql.toString());
			// 값 설정(쿼리 문 '?'에 들어 갈 값)
			stmt.setInt(1, num);
			// 출력
			stmt.executeUpdate();

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
