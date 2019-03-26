package com.dep.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mem.model.MemDAOImpl;
import com.mem.model.MemVO;
import com.sessions.model.SessionsDAOImpl;
import com.sessions.model.SessionsVO;

public class DepDAOImpl implements DepDAO_interface {
	
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JOIN");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	String username = "JOIN";
//	String password = "123456";
			
	private static final String INSERT_DEP =
			"INSERT INTO DEPOSITDETAIL (DEPOSIT_CHANGE_NO,DEPOSIT_MEMBER_NO,DEPOSIT_CHANGE_MONEY,DEPOSIT_CHANGE_DATE) VALUES ('D'||LPAD(to_char(DEPOSITDETAIL_seq.NEXTVAL),6,'0'),?,?,?)";
	private static final String UPDATE = 
			"UPDATE DEPOSITDETAIL set DEPOSIT_MEMBER_NO = ?,DEPOSIT_CHANGE_MONEY =?,DEPOSIT_CHANGE_DATE =? ";
	private static final String DELETE =
			"DELETE FROM DEPOSITDETAIL where DEPOSIT_CHANGE_NO = ?";
	private static final String GET_ALL_DEP = 
			//"SELECT DEPOSIT_CHANGE_NO,DEPOSIT_MEMBER_NO,DEPOSIT_CHANGE_MONEY,to_char(DEPOSIT_CHANGE_DATE,'yyyy-mm-dd hh-mm-ss.fffffffff')DEPOSIT_CHANGE_DATE FROM DEPOSITDETAIL order by DEPOSIT_CHANGE_NO";
			"SELECT * FROM DEPOSITDETAIL order by DEPOSIT_CHANGE_NO DESC";
	private static final String GET_ONE_DEP =
			//"SELECT DEPOSIT_CHANGE_NO,DEPOSIT_MEMBER_NO,DEPOSIT_CHANGE_MONEY,to_char(DEPOSIT_CHANGE_DATE,'yyyy-mm-dd hh-mm-ss.fffffffff')DEPOSIT_CHANGE_DATE FROM DEPOSITDETAIL where DEPOSIT_CHANGE_NO = ?";
			"SELECT * FROM DEPOSITDETAIL WHERE DEPOSIT_CHANGE_NO =? ";
	private static final String GET_ONE_DEP_MEM_NO =
			//"SELECT DEPOSIT_CHANGE_NO,DEPOSIT_MEMBER_NO,DEPOSIT_CHANGE_MONEY,to_char(DEPOSIT_CHANGE_DATE,'yyyy-mm-dd hh-mm-ss.fffffffff')DEPOSIT_CHANGE_DATE FROM DEPOSITDETAIL where DEPOSIT_CHANGE_NO = ?";
			"SELECT * FROM DEPOSITDETAIL WHERE DEPOSIT_MEMBER_NO =? ";
	
	
	
	//交易區間專用指令--開始
	
	private static final String INSERT_DEP_BYTICKETORDER=
			"INSERT INTO DEPOSITDETAIL VALUES ('D'||LPAD(to_char(DEPOSITDETAIL_seq.NEXTVAL),6,'0'),?,?,?)";
		
	//交易區間專用指令--結束
	
	
	
	

	@Override
	public String insert(DepVO depVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String pk = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_DEP, new String[] {"DEPOSIT_CHANGE_NO"});
			
			pstmt.setString(1, depVO.getDeposit_member_no());
			pstmt.setInt(2,depVO.getDeposit_change_money());
			pstmt.setTimestamp(3,depVO.getDeposit_change_date());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			pk = rs.getString(1);
			
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
		return pk;
	}

	@Override
	public void update(DepVO depVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1,depVO.getDeposit_member_no());
			pstmt.setInt(2,depVO.getDeposit_change_money());
			pstmt.setTimestamp(3,depVO.getDeposit_change_date());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
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

	@Override
	public void delete(String deposit_change_no) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, deposit_change_no);
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			}catch(SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		
	}

	@Override
	public DepVO findByPrimaryKey(String deposit_change_no) {
		// TODO Auto-generated method stub
		
		DepVO depVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DEP);
			
			pstmt.setString(1,deposit_change_no);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				depVO = new DepVO();
				depVO.setDeposit_change_no(rs.getString("deposit_change_no"));
				depVO.setDeposit_member_no(rs.getString("deposit_member_no"));
				depVO.setDeposit_change_money(rs.getInt("deposit_change_money"));
				depVO.setDeposit_change_date(rs.getTimestamp("deposit_change_date"));
				
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
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		
		return depVO;
	}
	
	
	@Override
	public DepVO findByMem_no(String deposit_member_no) {
		// TODO Auto-generated method stub
		DepVO depVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DEP_MEM_NO);
			
			pstmt.setString(1,deposit_member_no);
			rs= pstmt.executeQuery();
			
			while(rs.next()) {
				depVO = new DepVO();
				depVO.setDeposit_change_no(rs.getString("deposit_change_no"));
				depVO.setDeposit_member_no(rs.getString("deposit_member_no"));
				depVO.setDeposit_change_money(rs.getInt("deposit_change_money"));
				depVO.setDeposit_change_date(rs.getTimestamp("deposit_change_date"));
				
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
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
		
		return depVO;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public List<DepVO> getAll() {
		// TODO Auto-generated method stub
		
		
		List<DepVO> list = new ArrayList<DepVO>();
		DepVO depVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DEP);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				
				depVO = new DepVO();
				depVO.setDeposit_change_no(rs.getString("deposit_change_no"));
				depVO.setDeposit_member_no(rs.getString("deposit_member_no"));
				depVO.setDeposit_change_money(rs.getInt("deposit_change_money"));
				depVO.setDeposit_change_date(rs.getTimestamp("deposit_change_date"));
				list.add(depVO);
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
			if (con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		
		}	
		
		
		return list;
	}
	
	
	@Override
	public List<DepVO> findByMem_no1(String deposit_member_no) {
		// TODO Auto-generated method stub
		List<DepVO> list = new ArrayList<DepVO>();
		DepVO depVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DEP_MEM_NO);
			pstmt.setString(1, deposit_member_no);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				
				depVO = new DepVO();
				depVO.setDeposit_change_no(rs.getString("deposit_change_no"));
				depVO.setDeposit_member_no(rs.getString("deposit_member_no"));
				depVO.setDeposit_change_money(rs.getInt("deposit_change_money"));
				depVO.setDeposit_change_date(rs.getTimestamp("deposit_change_date"));
				list.add(depVO);
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
			if (con != null) {
				try {
					con.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		
		}	
		
		
		return list;
	}
	
	
	
	@Override
	public void insertByTicketorder(DepVO depVO, SessionsVO sessionsVO, Connection con) throws SQLException {
		
		PreparedStatement pstmt = null;
		SessionsDAOImpl sesDAO = null;
		
			try {
				pstmt = con.prepareStatement(INSERT_DEP_BYTICKETORDER, new String[] { "DEPOSIT_CHANGE_NO" });
				pstmt.setString(1, depVO.getDeposit_member_no());
				pstmt.setInt(2,depVO.getDeposit_change_money());
				pstmt.setTimestamp(3,depVO.getDeposit_change_date());
				
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				rs.next();
				depVO.setDeposit_change_no(rs.getString(1));
				rs.close();
				pstmt.close();
				
				sesDAO = new SessionsDAOImpl();
				sesDAO.updateByTicketorder(sessionsVO, con);
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw(e);
			} finally {
				if(pstmt != null) {
					pstmt.close();
				}
			}
			
		
		}

	@Override
	public void insertDepositMain(DepVO depVO, MemVO memVO) {
		// TODO Auto-generated method stub
		
			
	
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			MemDAOImpl memDAO = null;
			
			try {
				con=ds.getConnection();
				con.setAutoCommit(false);
				
				pstmt = con.prepareStatement(INSERT_DEP , new String[] {"deposit_change_no"});
				pstmt.setString(1, depVO.getDeposit_member_no());
				pstmt.setInt(2,depVO.getDeposit_change_money());
				pstmt.setTimestamp(3,depVO.getDeposit_change_date());
				
				pstmt.executeUpdate();
				
				rs = pstmt.getGeneratedKeys();
				rs.next();
				
				depVO.setDeposit_change_no(rs.getString(1));
				
				rs.close();
				pstmt.close();
				
				memDAO = new MemDAOImpl();
				memDAO.updateDeposit_point(depVO, memVO, con);
				
				con.commit();
				con.setAutoCommit(true);
			
		}catch(SQLException se) {
		se.printStackTrace();
		try {
			con.rollback();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured." +e.getMessage());
		}
		throw new RuntimeException("A database error occured." +se.getMessage());
		
	}finally {
		if(rs!=null) {
			try {
				rs.close();
			}catch(SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		
		if(pstmt!=null) {
			try {
				pstmt.close();
			}catch(SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		
		if(con!=null) {
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
		
	}


public static void main(String[]args) {
	
	DepDAOImpl dao = new DepDAOImpl();
	
	//新增
	DepVO depVO1 = new DepVO();
	depVO1.setDeposit_member_no("M000001");
	depVO1.setDeposit_change_money(new Integer(3000));
	depVO1.setDeposit_change_date(java.sql.Timestamp.valueOf("2019-02-13 12:05:09"));
	
	dao.insert(depVO1);
	
	
	
	//修改
//	DepVO depVO2 = new DepVO();
//	depVO2.setDeposit_change_no("D000015");
//	depVO2.setDeposit_member_no("M000002");
//	depVO2.setDeposit_change_money(new Integer(4000));
//	depVO2.setDeposit_change_date(java.sql.Timestamp.valueOf("2019-02-11 12:05:09"));
//	dao.update(depVO2);
	
	

	
	//刪除
	
	dao.delete("D000005");
	
	//查詢
	DepVO depVO3 = dao.findByPrimaryKey("D000012");
	System.out.print(depVO3.getDeposit_change_no()+",");
	System.out.print(depVO3.getDeposit_member_no()+",");
	System.out.print(depVO3.getDeposit_change_money()+",");
	System.out.print(depVO3.getDeposit_change_date());
	System.out.println("-----------------------");
	
	//查詢
	List<DepVO> list = dao.getAll();
	for (DepVO aDep : list) {
		
		System.out.print(aDep.getDeposit_change_no()+",");
		System.out.print(aDep.getDeposit_member_no()+",");
		System.out.print(aDep.getDeposit_change_money()+",");
		System.out.print(aDep.getDeposit_change_date());
		System.out.println();
		}
	
	
	}






}