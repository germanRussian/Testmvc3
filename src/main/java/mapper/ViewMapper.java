package mapper;

import java.sql.*;

import domain.BoardVO;

public class ViewMapper {

	public BoardVO read(BoardVO vo) {
		/* DB연결 */

		String url = "jdbc:mysql://localhost:3306/smart?characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
		String user = "root";
		String password = "smart";

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT *  FROM board");
		sql.append(" where num = ? ");

		
		BoardVO vo2 = null;
		
		/* DB정보를 가져와서 화면에 출력 */
		try {

			// 드라이버로드
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 연결
			conn = DriverManager.getConnection(url, user, password);
			// SQL
			stmt = conn.prepareStatement(sql.toString());
			// 값 설정(쿼리 문 '?'에 들어 갈 값)
			stmt.setInt(1, vo.getNum());
			// 출력
			rs = stmt.executeQuery();

			

			/* 한건에 해당하는 것만 내용을 가져올 것이기 때문에 if문 사용 while문은 여러건을 가져올때. */
			if (rs.next()) {

				vo2 = new BoardVO();

				vo2.setNum(rs.getInt("num"));
				vo2.setTitle(rs.getString("title"));
				vo2.setContent(rs.getString("content"));
				vo2.setWriter(rs.getString("writer"));
				vo2.setRealFileName(rs.getString("realFileName"));
				vo2.setRealSaveFileName(rs.getString("realSaveFileName"));
				

			}

		} catch (Exception e) {
			e.getLocalizedMessage();
		} finally {
			// 닫기

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
		return vo2;
	}

}
