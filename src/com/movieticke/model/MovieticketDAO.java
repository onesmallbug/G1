package com.movieticke.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dep.model.DepVO;
import com.mem.model.MemDAOImpl;
import com.mem.model.MemVO;
import com.sessions.model.SessionsVO;
import com.ticketorder.model.TicketorderVO;

import java.sql.*;

public class MovieticketDAO implements MovieticketDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JOIN");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = "INSERT INTO MOVIETICKET (mt_no,order_no,ti_no,mt_qr,mt_admission,mt_share) VALUES (MOVIETICKET_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT mt_no,order_no,ti_no,mt_qr,mt_admission,mt_share FROM MOVIETICKET order by mt_no";
	private static final String GET_ONE_STMT = "SELECT mt_no,order_no,ti_no,mt_qr,mt_admission,mt_share FROM MOVIETICKET where mt_no = ?";
	private static final String DELETE = "DELETE FROM MOVIETICKET where mt_no = ?";
	private static final String UPDATE = "UPDATE MOVIETICKET set order_no=?, ti_no=?, mt_qr=?, mt_admission=?, mt_share=? where mt_no = ?";

	// 交易區間專用指令--開始

	private static final String INSERT_MOVIETICKET_BYTICKORDER = "INSERT INTO MOVIETICKET VALUES (?, ?, ?, ?, ?, ?)";

	// 交易區間專用指令--結束?, mt_qr=?, mt_admission=?, mt_share=? where mt_no = ?";
	
	
	//20190317更新
	private static final String GET_ONE_TIC_ORD_NO=
			"SELECT * FROM MOVIETICKET WHERE ORDER_NO=?";
	
	
	@Override
	public void insert(MovieticketVO movieticketVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			//pstmt.setString(1, ticketorderVO.getOrder_no());
			pstmt.setString(1, movieticketVO.getOrder_no());
			pstmt.setString(2, movieticketVO.getTi_no());
			pstmt.setBytes(3, movieticketVO.getMt_qr());
			pstmt.setInt(4, movieticketVO.getMt_admission());
			pstmt.setString(5, movieticketVO.getMt_share());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void update(MovieticketVO movieticketVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
//			pstmt.setString(1, movieticketVO.getMt_no());
			pstmt.setString(1, movieticketVO.getOrder_no());
			pstmt.setString(2, movieticketVO.getTi_no());
			pstmt.setBytes(3, movieticketVO.getMt_qr());
			pstmt.setInt(4, movieticketVO.getMt_admission());
			pstmt.setString(5, movieticketVO.getMt_share());
			pstmt.setString(6, movieticketVO.getMt_no());
			
//			"UPDATE MOVIETICKET set order_no=?, ti_no=?, mt_qr=?, mt_admission=?, mt_share=? where mt_no = ?";

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String mt_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mt_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public MovieticketVO findByPrimaryKey(String mt_no) {
		MovieticketVO movieticketVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mt_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				movieticketVO = new MovieticketVO();
				movieticketVO.setMt_no(rs.getString("mt_no"));
				movieticketVO.setOrder_no(rs.getString("order_no"));
				movieticketVO.setTi_no(rs.getString("ti_no"));
				movieticketVO.setMt_qr(rs.getBytes("mt_qr"));
				movieticketVO.setMt_admission(rs.getInt("mt_admission"));
				movieticketVO.setMt_share(rs.getString("mt_share"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
		
		return movieticketVO;
	}

	@Override
	public List<MovieticketVO> getAll() {
		List<MovieticketVO> list = new ArrayList<MovieticketVO>();
		MovieticketVO movieticketVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movieticketVO = new MovieticketVO();
				movieticketVO.setMt_no(rs.getString("mt_no"));
				movieticketVO.setOrder_no(rs.getString("order_no"));
				movieticketVO.setTi_no(rs.getString("ti_no"));
				movieticketVO.setMt_qr(rs.getBytes("mt_qr"));
				movieticketVO.setMt_admission(rs.getInt("mt_admission"));
				movieticketVO.setMt_share(rs.getString("mt_share"));
				list.add(movieticketVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void insertByTicketorder(TicketorderVO ticketorderVO, MemVO memVO, DepVO depVO, SessionsVO sessionsVO, List<MovieticketVO> ListOfMovieticketVO, Connection con) throws SQLException{

		PreparedStatement pstmt = null;
		MemDAOImpl memDAO = null;
		try {
			pstmt = con.prepareStatement(INSERT_MOVIETICKET_BYTICKORDER);
			for (MovieticketVO movieticketVO : ListOfMovieticketVO) {
				
				pstmt.setString(1, movieticketVO.getMt_no());
				pstmt.setString(2, ticketorderVO.getOrder_no());
				pstmt.setString(3, movieticketVO.getTi_no());
				pstmt.setBytes(4, movieticketVO.getMt_qr());
				pstmt.setInt(5, movieticketVO.getMt_admission());
				pstmt.setString(6, movieticketVO.getMt_share());
				
				pstmt.executeUpdate();
			}
			
			memDAO = new MemDAOImpl();
			memDAO.updateMember_point(ticketorderVO, memVO, depVO, sessionsVO, con);			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw(e);
		} finally {
			if(pstmt != null) {
				pstmt.close();
			}
		}
		
	}

	//20190317更新
	@Override
	public List<MovieticketVO> findByOrder_no(String order_no) {
		// TODO Auto-generated method stub
		List<MovieticketVO> list = new ArrayList<MovieticketVO>();
		MovieticketVO movieticketVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_TIC_ORD_NO);
			pstmt.setString(1, order_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				movieticketVO = new MovieticketVO();
				movieticketVO.setMt_no(rs.getString("mt_no"));
				movieticketVO.setOrder_no(rs.getString("order_no"));
				movieticketVO.setTi_no(rs.getString("ti_no"));
				movieticketVO.setMt_qr(rs.getBytes("mt_qr"));
				movieticketVO.setMt_admission(rs.getInt("mt_admission"));
				movieticketVO.setMt_share(rs.getString("mt_share"));
				list.add(movieticketVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
