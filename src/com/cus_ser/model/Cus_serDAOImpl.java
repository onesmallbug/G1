package com.cus_ser.model;

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

public class Cus_serDAOImpl implements Cus_serDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JOIN");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	private static final String INSERT_CUS_SER = 
			"INSERT INTO CUSTOMESERVICERECORD (customer_service_event_no,member_no,customer_service_start_date,customer_service_status) VALUES ('C'||LPAD(to_char(CUSTOMESERVICERECORD_seq.NEXTVAL),6,'0'),?,?,?)";
	 
	private static final String GET_ALL_CUS_SER =
			"SELECT customer_service_event_no,member_no,to_char(customer_service_start_date,'yyyy-mm-dd') customer_service_start_date,customer_service_status FROM CUSTOMESERVICERECORD order by customer_service_event_no ";
	
	private static final String GET_ONE_CUS_SER = 
			"SELECT customer_service_event_no,member_no,to_char(customer_service_start_date,'yyyy-mm-dd') customer_service_start_date,customer_service_status FROM CUSTOMESERVICERECORD where customer_service_event_no = ? ";
	
	private static final String DELETE = 
			"DELETE FROM CUSTOMESERVICERECORD where customer_service_event_no = ?";
	
	private static final String UPDATE = 
			"UPDATE CUSTOMESERVICERECORD set member_no=?,customer_service_start_date=?,customer_service_status=? where customer_service_event_no=?";
	
	
	
	
	
	
	
	@Override
	public void insert(Cus_serVO cus_serVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_CUS_SER);
			
			pstmt.setString(1, cus_serVO.getMember_no());
			pstmt.setDate(2, cus_serVO.getCustomer_service_start_date());
			pstmt.setInt(3, cus_serVO.getCustomer_service_status());
			
			pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		
		
	}

	@Override
	public void update(Cus_serVO cus_serVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, cus_serVO.getMember_no());
			pstmt.setDate(2, cus_serVO.getCustomer_service_start_date());
			pstmt.setInt(3, cus_serVO.getCustomer_service_status());
			pstmt.setString(4, cus_serVO.getCustomer_service_event_no());
			
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			
		}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
	}
}

	@Override
	public void delete(String customer_service_event_no) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			
			pstmt.setString(1,customer_service_event_no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			
		}
			
		}
		
		
		
		
	}

	@Override
	public Cus_serVO findByPrimaryKey(String customer_service_event_no) {
		// TODO Auto-generated method stub
		
		Cus_serVO cus_serVO =  null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_CUS_SER);
			
			pstmt.setString(1, customer_service_event_no );
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				cus_serVO = new Cus_serVO();
				cus_serVO.setCustomer_service_event_no(rs.getString("customer_service_event_no"));
				cus_serVO.setMember_no(rs.getString("member_no"));
				cus_serVO.setCustomer_service_start_date(rs.getDate("customer_service_start_date"));
				cus_serVO.setCustomer_service_status(rs.getInt("customer_service_status"));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		
		return cus_serVO;
	}

	@Override
	public List<Cus_serVO> getAll() {
		// TODO Auto-generated method stub
		
		List<Cus_serVO> list =new ArrayList<Cus_serVO>();
		Cus_serVO cus_serVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_CUS_SER);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				cus_serVO = new Cus_serVO();
				cus_serVO.setCustomer_service_event_no(rs.getString("customer_service_event_no"));
				cus_serVO.setMember_no(rs.getString("member_no"));
				cus_serVO.setCustomer_service_start_date(rs.getDate("customer_service_start_date"));
				cus_serVO.setCustomer_service_status(rs.getInt("customer_service_status"));
				list.add(cus_serVO);
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if(con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}	
		
		return list;
	}
	

}
