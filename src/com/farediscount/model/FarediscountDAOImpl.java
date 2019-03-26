package com.farediscount.model;

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

public class FarediscountDAOImpl implements FarediscountDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JOIN");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO FAREDISCOUNT VALUES ('FD'||LPAD(FAREDISCOUNT_SEQ.NEXTVAL, 3, '0'),?,?,?,?,?,?,?,?)";
	private static final String GET_ALL = "SELECT * FROM FAREDISCOUNT";
	private static final String GET_ONE = "SELECT * FROM FAREDISCOUNT WHERE FD_NO = ?";
	private static final String DELETE = "DELETE FROM FAREDISCOUNT WHERE FD_NO = ?";
	private static final String UPDATE = "UPDATE FAREDISCOUNT SET FD_NARRATIVE=?, FD_NAME=?, FD_OFFER=?, FD_START=?, FD_END=?, FD_UPPER=?, FD_LOWER=?, FD_BLOB=? WHERE FD_NO = ?";

	@Override
	public void insert(FarediscountVO farediscountVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			// UPDATE FAREDISCOUNT SET FD_NARRATIVE=?, FD_NAME=?, FD_OFFER=?, FD_START=?, FD_END=?, FD_UPPER=?, 
			//FD_LOWER=?, FD_BLOB=? WHERE FD_NO = ?

			pstmt.setString(1, farediscountVO.getFd_narrative());
			pstmt.setString(2, farediscountVO.getFd_name());
			pstmt.setInt(3, farediscountVO.getFd_offer());
			pstmt.setDate(4, farediscountVO.getFd_start());
			pstmt.setDate(5, farediscountVO.getFd_end());
			pstmt.setInt(6, farediscountVO.getFd_upper());
			pstmt.setInt(7, farediscountVO.getFd_lower());
			pstmt.setBytes(8, farediscountVO.getFd_blob());

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
	public void update(FarediscountVO farediscountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			// UPDATE FAREDISCOUNT SET FD_NARRATIVE=?, FD_NAME=?, FD_OFFER=?, FD_START=?, FD_END=?, FD_UPPER=?, 
						//FD_LOWER=?, FD_BLOB=? WHERE FD_NO = ?
			pstmt.setString(1, farediscountVO.getFd_narrative());
			pstmt.setString(2, farediscountVO.getFd_name());
			pstmt.setInt(3, farediscountVO.getFd_offer());
			pstmt.setDate(4, farediscountVO.getFd_start());
			pstmt.setDate(5, farediscountVO.getFd_end());
			pstmt.setInt(6, farediscountVO.getFd_upper());
			pstmt.setInt(7, farediscountVO.getFd_lower());
			pstmt.setBytes(8, farediscountVO.getFd_blob());
			pstmt.setString(9, farediscountVO.getFd_no());

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
	public void delete(String fd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			// DELETE FROM FAREDISCOUNT WHERE FD_NO = ?
			pstmt.setString(1, fd_no);

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
	public FarediscountVO findByPrimaryKey(String fd_no) {
		FarediscountVO farediscountVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, fd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				farediscountVO = new FarediscountVO();
				farediscountVO.setFd_no(fd_no);
				farediscountVO.setFd_narrative(rs.getString("FD_NARRATIVE"));
				farediscountVO.setFd_name(rs.getString("FD_NAME"));
				farediscountVO.setFd_offer(rs.getInt("FD_OFFER"));
				farediscountVO.setFd_start(rs.getDate("FD_START"));
				farediscountVO.setFd_end(rs.getDate("FD_END"));
				farediscountVO.setFd_upper(rs.getInt("FD_UPPER"));
				farediscountVO.setFd_lower(rs.getInt("FD_LOWER"));
				farediscountVO.setFd_blob(rs.getBytes("FD_BLOB"));
				// UPDATE FAREDISCOUNT SET FD_NARRATIVE=?, FD_NAME=?, FD_OFFER=?, FD_START=?, FD_END=?, FD_UPPER=?, 
				//FD_LOWER=?, FD_BLOB=? WHERE FD_NO = ?

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
		return farediscountVO;
	}

	@Override
	public List<FarediscountVO> getAll() {
		List<FarediscountVO> list = new ArrayList<FarediscountVO>();
		FarediscountVO farediscountVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				farediscountVO = new FarediscountVO();
				farediscountVO.setFd_no(rs.getString("FD_NO"));
				farediscountVO.setFd_narrative(rs.getString("FD_NARRATIVE"));
				farediscountVO.setFd_name(rs.getString("FD_NAME"));
				farediscountVO.setFd_offer(rs.getInt("FD_OFFER"));
				farediscountVO.setFd_start(rs.getDate("FD_START"));
				farediscountVO.setFd_end(rs.getDate("FD_END"));
				farediscountVO.setFd_upper(rs.getInt("FD_UPPER"));
				farediscountVO.setFd_lower(rs.getInt("FD_LOWER"));
				farediscountVO.setFd_blob(rs.getBytes("FD_BLOB"));
				// UPDATE FAREDISCOUNT SET FD_NARRATIVE=?, FD_NAME=?, FD_OFFER=?, FD_START=?, FD_END=?, FD_UPPER=?, 
				//FD_LOWER=?, FD_BLOB=? WHERE FD_NO = ?
				list.add(farediscountVO);
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
