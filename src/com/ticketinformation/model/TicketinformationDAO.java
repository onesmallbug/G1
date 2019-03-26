package com.ticketinformation.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class TicketinformationDAO implements TicketinformationDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JOIN");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_STMT =
			"INSERT INTO TICKETINFORMATION (ti_no,ti_name,ti_price) VALUES (TICKETINFORMATION_seq.NEXTVAL, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT ti_no,ti_name,ti_price FROM TICKETINFORMATION order by ti_no";
	private static final String GET_ONE_STMT = 
			"SELECT ti_no,ti_name,ti_price FROM TICKETINFORMATION where ti_no = ?";
	private static final String DELETE =
			"DELETE FROM TICKETINFORMATION where ti_no = ?";
	private static final String UPDATE = 
			"UPDATE TICKETINFORMATION set ti_name=?, ti_price=? where ti_no = ?";
	

	@Override
	public void insert(TicketinformationVO ticketinformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, ticketinformationVO.getTi_name());
			pstmt.setInt(2, ticketinformationVO.getTi_price());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occuted. " + se.getMessage());
			
		} finally {
			if (pstmt !=  null) {
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
	public void update(TicketinformationVO ticketinformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, ticketinformationVO.getTi_name());
			pstmt.setInt(2, ticketinformationVO.getTi_price());
			pstmt.setString(3, ticketinformationVO.getTi_no());
			
			
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
	public void delete(String ti_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, ti_no);
			
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
	public TicketinformationVO findByPrimaryKey(String ti_no) {
		TicketinformationVO ticketinformationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, ti_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ticketinformationVO = new TicketinformationVO();
				ticketinformationVO.setTi_no(rs.getString("ti_no"));
				ticketinformationVO.setTi_name(rs.getString("ti_name"));
				ticketinformationVO.setTi_price(rs.getInt("ti_price"));
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
		return ticketinformationVO;
	}

	@Override
	public List<TicketinformationVO> getAll() {
		List<TicketinformationVO> list = new ArrayList<TicketinformationVO>();
		TicketinformationVO ticketinformationVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ticketinformationVO = new TicketinformationVO();
				ticketinformationVO.setTi_no(rs.getString("ti_no"));
				ticketinformationVO.setTi_name(rs.getString("ti_name"));
				ticketinformationVO.setTi_price(rs.getInt("ti_price"));
				list.add(ticketinformationVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if(rs != null) {
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
