package com.cinema.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CinemaDAOImpl implements CinemaDAO_interface{
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JOIN");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO CINEMA VALUES ('CINEMA'||LPAD(CINEMA_SEQ.NEXTVAL,3,0),?,?,?,?,?)";
	private static final String GET_ALL = "SELECT * FROM CINEMA";
	private static final String GET_ONE = "SELECT * FROM CINEMA WHERE CINEMA_NO = ?";
	private static final String DELETE = "DELETE FROM CINEMA WHERE CINEMA_NO = ?";
	private static final String UPDATE = "UPDATE CINEMA SET CINEMA_TYPE=?, CINEMA_SIZE=?, CINEMA_NAME=?, CINEMA_CORRECT=?, CINEMA_STATUS=? WHERE CINEMA_NO = ?";
	

	@Override
	public void insert(CinemaVO cinemaVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			// INSERT INTO CINEMA VALUES ('CINEMA'||LPAD(CINEMA_SEQ.NEXTVAL,3,0),?,?,?,?,?)
			//UPDATE CINEMA SET CINEMA_TYPE=?, CINEMA_SIZE=?, CINEMA_NAME=?, CINEMA_CORRECT=?, CINEMA_STATUS=? WHERE CINEMA_NO = ?

			pstmt.setString(1, cinemaVO.getCinema_type());
			pstmt.setInt(2, cinemaVO.getCinema_size());
			pstmt.setString(3, cinemaVO.getCinema_name());
			pstmt.setInt(4, cinemaVO.getCinema_correct());
			pstmt.setString(5, cinemaVO.getCinema_status());
			
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
	public void update(CinemaVO cinemaVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			////UPDATE CINEMA SET CINEMA_TYPE=?, CINEMA_SIZE=?, CINEMA_NAME=?, CINEMA_CORRECT=?, CINEMA_STATUS=? WHERE CINEMA_NO = ?

			pstmt.setString(1, cinemaVO.getCinema_type());
			pstmt.setInt(2, cinemaVO.getCinema_size());
			pstmt.setString(3, cinemaVO.getCinema_name());
			pstmt.setInt(4, cinemaVO.getCinema_correct());
			pstmt.setString(5, cinemaVO.getCinema_status());
			pstmt.setString(6, cinemaVO.getCinema_no());
			
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
	public void delete(String cinema_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			//DELETE FROM CINEMA WHERE CINEMA_NO = ?
			pstmt.setString(1, cinema_no);

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
	public CinemaVO findByPrimaryKey(String cinema_no) {
		CinemaVO cinemaVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, cinema_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				cinemaVO = new CinemaVO();
				cinemaVO.setCinema_no(cinema_no);
				cinemaVO.setCinema_type(rs.getString("CINEMA_TYPE"));
				cinemaVO.setCinema_size(rs.getInt("CINEMA_SIZE"));
				cinemaVO.setCinema_name(rs.getString("CINEMA_NAME"));
				cinemaVO.setCinema_correct(rs.getInt("CINEMA_CORRECT"));
				cinemaVO.setCinema_status(rs.getString("CINEMA_STATUS"));
				//UPDATE CINEMA SET CINEMA_TYPE=?, CINEMA_SIZE=?, CINEMA_NAME=?, CINEMA_CORRECT=?, CINEMA_STATUS=? WHERE CINEMA_NO = ?

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
		return cinemaVO;
	}

	@Override
	public List<CinemaVO> getAll() {
		List<CinemaVO> list = new Vector<CinemaVO>();
		CinemaVO cinemaVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cinemaVO = new CinemaVO();
				cinemaVO.setCinema_no(rs.getString("CINEMA_NO"));
				cinemaVO.setCinema_type(rs.getString("CINEMA_TYPE"));
				cinemaVO.setCinema_size(rs.getInt("CINEMA_SIZE"));
				cinemaVO.setCinema_name(rs.getString("CINEMA_NAME"));
				cinemaVO.setCinema_correct(rs.getInt("CINEMA_CORRECT"));
				cinemaVO.setCinema_status(rs.getString("CINEMA_STATUS"));
				list.add(cinemaVO);
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
}
