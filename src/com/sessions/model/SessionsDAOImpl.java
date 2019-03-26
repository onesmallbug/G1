package com.sessions.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SessionsDAOImpl implements SessionsDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JOIN");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO SESSIONS VALUES ('SESSIONS'||LPAD(SESSIONS_SEQ.NEXTVAL, 8, '0'),?,?,?,?,?)";
	private static final String GET_ALL = "SELECT * FROM SESSIONS";
	private static final String GET_ONE = "SELECT * FROM SESSIONS WHERE SESSIONS_NO = ?";
	private static final String DELETE = "DELETE FROM SESSIONS WHERE SESSIONS_NO = ?";
	private static final String UPDATE = "UPDATE SESSIONS SET MOVIE_NO=?, CINEMA_NO=?, SESSIONS_START=?, SESSIONS_STATUS=?, SESSIONS_REMAINING=? WHERE SESSIONS_NO = ?";

	//交易區間專用指令--開始
	
		private static final String UPDATE_SESSIONS_BYTICKORDER = 
				"UPDATE SESSIONS SET SESSIONS_STATUS=? , SESSIONS_REMAINING=? WHERE SESSIONS_NO = ?";
		
		//交易區間專用指令--結束
	
	@Override
	public void insert(SessionsVO sessionsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			// INSERT INTO SESSIONS VALUES ('SESSIONS'||LPAD(SESSIONS_SEQ.NEXTVAL, 8, '0'),?,?,?,?,?)

			pstmt.setString(1, sessionsVO.getMovie_no());
			pstmt.setString(2, sessionsVO.getCinema_no());
			pstmt.setTimestamp(3, sessionsVO.getSessions_start());
			pstmt.setString(4, sessionsVO.getSessions_status());
			pstmt.setInt(5, sessionsVO.getSessions_remaining());
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(SessionsVO sessionsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			//UPDATE SESSIONS SET MOVIE_NO=?, CINEMA_NO=?, SESSIONS_START=?, SESSIONS_STATUS=?, SESSIONS_REMAINING=? WHERE SESSIONS_NO = ?

			pstmt.setString(1, sessionsVO.getMovie_no());
			pstmt.setString(2, sessionsVO.getCinema_no());
			pstmt.setTimestamp(3, sessionsVO.getSessions_start());
			pstmt.setString(4, sessionsVO.getSessions_status());
			pstmt.setInt(5, sessionsVO.getSessions_remaining());
			pstmt.setString(6, sessionsVO.getSessions_no());
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void delete(String sessions_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			//DELETE FROM SESSIONS WHERE SESSIONS_NO = ?
			pstmt.setString(1, sessions_no);

			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public SessionsVO findByPrimaryKey(String sessions_no) {
		SessionsVO sessionsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, sessions_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				sessionsVO = new SessionsVO();
				sessionsVO.setSessions_no(sessions_no);
				sessionsVO.setMovie_no(rs.getString("MOVIE_NO"));
				sessionsVO.setCinema_no(rs.getString("CINEMA_NO"));
				sessionsVO.setSessions_start(rs.getTimestamp("SESSIONS_START"));
				sessionsVO.setSessions_status(rs.getString("SESSIONS_STATUS"));
				sessionsVO.setSessions_remaining(rs.getInt("SESSIONS_REMAINING"));
				//UPDATE SESSIONS SET MOVIE_NO=?, CINEMA_NO=?, SESSIONS_START=?, SESSIONS_STATUS=?, SESSIONS_REMAINING=? WHERE SESSIONS_NO = ?

			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return sessionsVO;
	}

	@Override
	public List<SessionsVO> getAll() {
		List<SessionsVO> list = new ArrayList<SessionsVO>();
		SessionsVO sessionsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sessionsVO = new SessionsVO();
				sessionsVO.setSessions_no(rs.getString("SESSIONS_NO"));
				sessionsVO.setMovie_no(rs.getString("MOVIE_NO"));
				sessionsVO.setCinema_no(rs.getString("CINEMA_NO"));
				sessionsVO.setSessions_start(rs.getTimestamp("SESSIONS_START"));
				sessionsVO.setSessions_status(rs.getString("SESSIONS_STATUS"));
				sessionsVO.setSessions_remaining(rs.getInt("SESSIONS_REMAINING"));
				list.add(sessionsVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public void updateByTicketorder(SessionsVO sessionsVO, Connection con) throws SQLException {
		
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(UPDATE_SESSIONS_BYTICKORDER);
			pstmt.setString(1, sessionsVO.getSessions_status());
			pstmt.setInt(2, sessionsVO.getSessions_remaining());
			pstmt.setString(3, sessionsVO.getSessions_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw(e);
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}		
	}

}
