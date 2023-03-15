package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conn.DBConn;
import main.Constants;

/**
 * 1. 회원가입
 * 2. 로그인
 * 3. 로그아웃
 * 4. 내정보확인
 * 5. 내정보수정
 * 6. 탈퇴
 * @author gayeong
 *
 */
public class MemberDao {
	
	private DBConn dbConn;
	private String sql;
	private String errorMsg = "처리에 에러가 발생했습니다.";
	private int result = 0;
	private String flag = "";
	
	private PreparedStatement pstmt;
	
	public MemberDao() {
		dbConn = dbConn.getInstance();
	}

	public String register(MemberVo vo) {
		Connection conn = dbConn.conn();
		sql = "INSERT INTO MEMBER VALUES (?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  vo.getId());
			pstmt.setString(2,  vo.getPwd());
			pstmt.setString(3,  vo.getName());
			pstmt.setString(4,  vo.getEmail());

			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("회원가입이 완료되었습니다.");
				flag = "11";
			} else {
				System.out.println(errorMsg);
				flag = "00";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	public String login(MemberVo vo) {
		if (!chkExistsId(vo)) {
			if (chkUserInfo(vo)) {
				Constants.mLoginId = vo.getId();
				System.out.println("로그인 되었습니다.");
				flag = "12";
			} else {
				System.out.println("로그인 정보가 올바르지 않습니다.");
				flag = "00";
			}
		} else {
			flag = "00";
			System.out.println("로그인 정보가 올바르지 않습니다.");
		}
		return flag;
	}

	public String chkMyInfo(MemberVo vo) {
		Connection conn = dbConn.conn();
		sql = "SELECT * FROM MEMBER WHERE ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  Constants.mLoginId);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setPwd(rs.getString("PWD"));
				vo.setName(rs.getString("NAME"));
				vo.setEmail(rs.getString("EMAIL"));
				System.out.println(vo.toString());
				flag = "14";
			} else {
				flag = "00";
				System.out.println(errorMsg);
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

	public String setMyInfo(MemberVo vo) {
		Connection conn = dbConn.conn();
		sql = "UPDATE MEMBER SET PWD=?, NAME=? WHERE ID=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, Constants.mLoginId);
			
			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("회원 정보 수정이 완료되었습니다.");
				flag = "15";
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

	public String delUserInfo() {
		Connection conn = dbConn.conn();
		sql = "DELETE FROM MEMBER WHERE ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Constants.mLoginId);
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("회원 탈퇴가 완료되었습니다.");
				flag = "16";
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
	
	public boolean chkExistsId (MemberVo vo) {
		Connection conn = dbConn.conn();
		sql = "SELECT * FROM MEMBER WHERE ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (result > 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean chkUserInfo(MemberVo vo) {
		ResultSet rs;
		boolean flag = true;
		Connection conn = dbConn.conn();
		sql = "SELECT ID, PWD FROM MEMBER WHERE ID = ? AND PWD = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  vo.getId());
			pstmt.setString(2,  vo.getPwd());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				flag = true;
			} else {
				flag = false;
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
	
}