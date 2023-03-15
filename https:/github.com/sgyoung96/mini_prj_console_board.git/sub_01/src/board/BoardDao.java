package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import conn.DBConn;
import main.Constants;

public class BoardDao {

	private DBConn dbConn;
	private String sql;
	private String errorMsg = "처리에 에러가 발생했습니다.";
	private ResultSet rs;
	private int result = 0;
	private String flag = "";

	private PreparedStatement pstmt;
	
	private ArrayList<BoardVo> vos;
	private BoardVo vo;

	public BoardDao() {
		dbConn = dbConn.getInstance();
	}

	public String writePost(BoardVo vo) {
		Connection conn = dbConn.conn();
		sql = "INSERT INTO (NUM, WRITER, W_DATE, TITLE, CONTENT) VALUES (SEQ_BOARD.NEXTVAL, ?, SYSDATE, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Constants.mLoginId);
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			
			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("작성글이 게시되었습니다.");
				flag = "21";
			} else {
				System.out.println(errorMsg);
				flag = "00";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public ArrayList<BoardVo> showLists(BoardVo vo) {
		Connection conn = dbConn.conn();
		sql = "SELECT * FROM BOARD ORDER BY W_DATE, WRITER";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vos = (ArrayList<BoardVo>) rs;
			} else {
				System.out.println(errorMsg);
				flag = "00";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return vos;
	}

	public String selPostDetail(BoardVo vo) {
		return flag;
	}

	public String modSelPost(BoardVo vo) {
		Connection conn = dbConn.conn();
		sql = "";
		try {
			pstmt = conn.prepareStatement(sql);

			if (result > 0) {

			} else {
				System.out.println(errorMsg);
				flag = "00";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public String rmSelPost(BoardVo vo) {
		Connection conn = dbConn.conn();
		sql = "";
		try {
			pstmt = conn.prepareStatement(sql);

			if (result > 0) {

			} else {
				System.out.println(errorMsg);
				flag = "00";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public String selByWriter(BoardVo vo) {
		return flag;
	}

	public String selByTitle(BoardVo vo) {
		return flag;
	}
}
