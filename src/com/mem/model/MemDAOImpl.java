package com.mem.model;

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

import com.dep.model.DepDAOImpl;
import com.dep.model.DepVO;
import com.sessions.model.SessionsVO;
import com.ticketorder.model.TicketorderVO;






public class MemDAOImpl implements MemDAO_interface {
	
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
	
	private static final String INSERT_MEM = 
			"INSERT INTO MEMBER (MEMBER_NO,MEMBER_ACCOUNT,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_NICK,MEMBER_SEX,MEMBER_BIRTHDAY,MEMBER_ADDRESS,MEMBER_TELEPHONE,MEMBER_EMAIL,MEMBER_PICTURE,MEMBER_CREDIT_NUMBER,MEMBER_BACK_VERIFICATION,MEMBER_BUILDDAY,MEMBER_POINT,MEMBER_STATUS) VALUES ('M'||LPAD(to_char(MEMBER_seq.NEXTVAL),6,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_ALL_MEM = 
			"SELECT MEMBER_NO,MEMBER_ACCOUNT,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_NICK,MEMBER_SEX,to_char(MEMBER_BIRTHDAY,'yyyy-mm-dd')MEMBER_BIRTHDAY,MEMBER_ADDRESS,MEMBER_TELEPHONE,MEMBER_EMAIL , MEMBER_PICTURE,MEMBER_CREDIT_NUMBER,MEMBER_BACK_VERIFICATION,MEMBER_BUILDDAY,MEMBER_POINT,MEMBER_STATUS FROM MEMBER order by MEMBER_NO DESC";
		
	private static final String GET_ONE_MEM = 
			"SELECT MEMBER_NO,MEMBER_ACCOUNT,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_NICK,MEMBER_SEX,to_char(MEMBER_BIRTHDAY,'yyyy-mm-dd')MEMBER_BIRTHDAY,MEMBER_ADDRESS,MEMBER_TELEPHONE,MEMBER_EMAIL,MEMBER_PICTURE,MEMBER_CREDIT_NUMBER,MEMBER_BACK_VERIFICATION,MEMBER_BUILDDAY,MEMBER_POINT,MEMBER_STATUS FROM MEMBER where MEMBER_NO = ?";
	
	private static final String GET_ONE_BY_ACCOUNT_MEM = 
			"SELECT MEMBER_NO,MEMBER_ACCOUNT,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_NICK,MEMBER_SEX,to_char(MEMBER_BIRTHDAY,'yyyy-mm-dd')MEMBER_BIRTHDAY,MEMBER_ADDRESS,MEMBER_TELEPHONE,MEMBER_EMAIL,MEMBER_PICTURE,MEMBER_CREDIT_NUMBER,MEMBER_BACK_VERIFICATION,MEMBER_BUILDDAY,MEMBER_POINT,MEMBER_STATUS FROM MEMBER where MEMBER_ACCOUNT = ?";
	
	private static final String DELETE = 
			"DELETE FROM MEMBER where MEMBER_NO = ?";
	private static final String UPDATE = 
			"UPDATE MEMBER set MEMBER_ACCOUNT =?,MEMBER_PASSWORD=?,MEMBER_NAME=?,MEMBER_NICK=?,MEMBER_SEX=?,MEMBER_BIRTHDAY=?,MEMBER_ADDRESS=?,MEMBER_TELEPHONE=?,MEMBER_EMAIL=?,MEMBER_PICTURE=? ,MEMBER_CREDIT_NUMBER=?,MEMBER_BACK_VERIFICATION=?,MEMBER_BUILDDAY=?,MEMBER_POINT=?,MEMBER_STATUS=? where MEMBER_NO = ?";
	
	private static final String UPDATE_MEMBER_STATUS = 
			"UPDATE MEMBER SET MEMBER_STATUS = '1'  WHERE MEMBER_NO = ?";
	
	private static final String UPDATE_DEPOSIT_POINT = 
			"UPDATE MEMBER SET MEMBER_POINT = ((SELECT MEMBER_POINT FROM MEMBER WHERE MEMBER_NO = ?)+?) WHERE MEMBER_NO = ?";
	//交易區間專用指令--開始
	
		private static final String UPDATE_MEMBER_POINT = 
				"UPDATE MEMBER SET MEMBER_POINT = ((SELECT MEMBER_POINT FROM MEMBER WHERE MEMBER_NO = ?)-?) WHERE MEMBER_NO = ?";
		
		//交易區間專用指令--結束
		
		
	//20190318更新
		private static final String UPDATE_MEMBER_PASSWORD = 
				"UPDATE MEMBER SET MEMBER_PASSWORD = ? WHERE MEMBER_ACCOUNT = ?";
		

	@Override
	public void insert(MemVO memVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_MEM, new String[] { "MEMBER_NO" });
			
			pstmt.setString(1,memVO.getMember_account());
			pstmt.setString(2,memVO.getMember_password());
			pstmt.setString(3,memVO.getMember_name());
			pstmt.setString(4,memVO.getMember_nick());
			pstmt.setInt(5,memVO.getMember_sex());
			pstmt.setDate(6,memVO.getMember_birthday());
			pstmt.setString(7,memVO.getMember_address());
			pstmt.setString(8,memVO.getMember_telephone());
			pstmt.setString(9,memVO.getMember_email());
			pstmt.setBytes(10,memVO.getMember_picture());
			pstmt.setString(11,memVO.getMember_credit_number());
			pstmt.setInt(12,memVO.getMember_back_verification());
			pstmt.setDate(13,memVO.getMember_buildday());
			pstmt.setInt(14,memVO.getMember_point());
			pstmt.setString(15,memVO.getMember_status());
			
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			rs.next();
			
			memVO.setMember_no(rs.getString(1));
			
			
		}catch (SQLException se) {
			se.printStackTrace();
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
	}finally {
		if(rs != null) {
			try {
				rs.close();
			}catch(SQLException se) {
				se.printStackTrace(System.err);
				throw new RuntimeException("Could't load database driver."
					+se.getMessage());
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException se) {
				se.printStackTrace(System.err);
				throw new RuntimeException("Could't load database driver."
					+se.getMessage());
			}
		}
		
		if(con != null) {
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace(System.err);
				throw new RuntimeException("Could't load database driver."
						+e.getMessage());
				}
			}
		}
	}
		
		
		
		
		
		
		

	@Override
	public MemVO findByPrimaryKey(String member_no) {
		// TODO Auto-generated method stub
		
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_MEM);
			
			pstmt.setString(1,member_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMember_no(rs.getString("member_no"));
				memVO.setMember_account(rs.getString("member_account"));
				memVO.setMember_password(rs.getString("member_password"));
				memVO.setMember_name(rs.getString("member_name"));
				memVO.setMember_nick(rs.getString("member_nick"));
				memVO.setMember_sex(rs.getInt("member_sex"));
				memVO.setMember_birthday(rs.getDate("member_birthday"));
				memVO.setMember_address(rs.getString("member_address"));
				memVO.setMember_telephone(rs.getString("member_telephone"));
				memVO.setMember_email(rs.getString("member_email"));
				memVO.setMember_picture(rs.getBytes("member_picture"));
				memVO.setMember_credit_number(rs.getString("member_credit_number"));
				memVO.setMember_back_verification(rs.getInt("member_back_verification"));
				memVO.setMember_buildday(rs.getDate("member_buildday"));
				memVO.setMember_point(rs.getInt("member_point"));
				memVO.setMember_status(rs.getString("member_status"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
					throw new RuntimeException("Could't load database driver."
							+se.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
					throw new RuntimeException("Could't load database driver."
							+se.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return memVO;
	}

	@Override
	public List<MemVO> getAll() {
		// TODO Auto-generated method stub
		List<MemVO> list = new ArrayList<MemVO>();
		MemVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_MEM);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMember_no(rs.getString("member_no"));
				memVO.setMember_account(rs.getString("member_account"));
				memVO.setMember_password(rs.getString("member_password"));
				memVO.setMember_name(rs.getString("member_name"));
				memVO.setMember_nick(rs.getString("member_nick"));
				memVO.setMember_sex(rs.getInt("member_sex"));
				memVO.setMember_birthday(rs.getDate("member_birthday"));
				memVO.setMember_address(rs.getString("member_address"));
				memVO.setMember_telephone(rs.getString("member_telephone"));
				memVO.setMember_email(rs.getString("member_email"));
				memVO.setMember_picture(rs.getBytes("member_picture"));
				memVO.setMember_credit_number(rs.getString("member_credit_number"));
				memVO.setMember_back_verification(rs.getInt("member_back_verification"));
				memVO.setMember_buildday(rs.getDate("member_buildday"));
				memVO.setMember_point(rs.getInt("member_point"));
				memVO.setMember_status(rs.getString("member_status"));
				list.add(memVO);
			}
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured"
					+ se.getMessage()); 
			
		}finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;
	}
	
	








	@Override
	public void update(MemVO memVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setString(1,memVO.getMember_account());
			pstmt.setString(2,memVO.getMember_password());
			pstmt.setString(3,memVO.getMember_name());
			pstmt.setString(4,memVO.getMember_nick());
			pstmt.setInt(5,memVO.getMember_sex());
			pstmt.setDate(6,memVO.getMember_birthday());
			pstmt.setString(7,memVO.getMember_address());
			pstmt.setString(8,memVO.getMember_telephone());
			pstmt.setString(9,memVO.getMember_email());
			pstmt.setBytes(10,memVO.getMember_picture());
			pstmt.setString(11,memVO.getMember_credit_number());
			pstmt.setInt(12,memVO.getMember_back_verification());
			pstmt.setDate(13,memVO.getMember_buildday());
			pstmt.setInt(14,memVO.getMember_point());
			pstmt.setString(15,memVO.getMember_status());
			pstmt.setString(16,memVO.getMember_no());
			pstmt.executeUpdate();
			
			
			
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			
			throw new RuntimeException ("A database error occured"
					+se.getMessage());
			
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					throw new RuntimeException ("Could't load datebase driver "
							+se.getMessage());
				}
			}
			
			if (con != null) {
				try {
					con.close();
				}catch (SQLException se) {
					throw new RuntimeException ("Could't load datebase driver "
							+se.getMessage());
				}
			}
			
		}
		
		
		
		
		
		
		
	}








	@Override
	public void delete(String member_no) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, member_no);
			
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			
			throw new RuntimeException ("A database error occured"
					+ se.getMessage());
			
		}finally {
			if (pstmt != null) {
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
		
	}



	@Override
	public MemVO findByMember_account(String member_account) {
		// TODO Auto-generated method stub
		MemVO memVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_ACCOUNT_MEM);
			
			pstmt.setString(1, member_account);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memVO = new MemVO();
				memVO.setMember_no(rs.getString("member_no"));
				memVO.setMember_account(rs.getString("member_account"));
				memVO.setMember_password(rs.getString("member_password"));
				memVO.setMember_name(rs.getString("member_name"));
				memVO.setMember_nick(rs.getString("member_nick"));
				memVO.setMember_sex(rs.getInt("member_sex"));
				memVO.setMember_birthday(rs.getDate("member_birthday"));
				memVO.setMember_address(rs.getString("member_address"));
				memVO.setMember_telephone(rs.getString("member_telephone"));
				memVO.setMember_email(rs.getString("member_email"));
				memVO.setMember_picture(rs.getBytes("member_picture"));
				memVO.setMember_credit_number(rs.getString("member_credit_number"));
				memVO.setMember_back_verification(rs.getInt("member_back_verification"));
				memVO.setMember_buildday(rs.getDate("member_buildday"));
				memVO.setMember_point(rs.getInt("member_point"));
				memVO.setMember_status(rs.getString("member_status"));
			
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
			
		
		
		
		
		return memVO;
	}
	
	
	
	
	
	
	

@Override
public void updateMember_point(TicketorderVO ticketorderVO, MemVO memVO, DepVO depVO, SessionsVO sessionsVO, Connection con) throws SQLException {
	 
	PreparedStatement pstmt = null;
	DepDAOImpl depDAO = null;
	
		try {
			pstmt = con.prepareStatement(UPDATE_MEMBER_POINT);
			pstmt.setString(1,memVO.getMember_no());
			pstmt.setInt(2,ticketorderVO.getOrder_amount());
			pstmt.setString(3,memVO.getMember_no());
			pstmt.executeUpdate();
			pstmt.close();
			
			depDAO = new DepDAOImpl();
			depDAO.insertByTicketorder(depVO, sessionsVO, con);
			
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
public void update_member_status(String member_no) {
	// TODO Auto-generated method stub
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		
		con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE_MEMBER_STATUS);
		
		pstmt.setString(1,member_no);
	
		pstmt.executeUpdate();
		
	} catch (SQLException se) {
		// TODO Auto-generated catch block
		
		throw new RuntimeException ("A database error occured"
				+se.getMessage());
		
	}finally {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException se) {
				throw new RuntimeException ("Could't load datebase driver "
						+se.getMessage());
			}
		}
		
		if (con != null) {
			try {
				con.close();
			}catch (SQLException se) {
				throw new RuntimeException ("Could't load datebase driver "
						+se.getMessage());
			}
		}
		
	}
	
}


@Override
public void updateDeposit_point(DepVO depVO, MemVO memVO, Connection con) throws SQLException {
	// TODO Auto-generated method stub
	
	PreparedStatement pstmt = null;
	DepDAOImpl depDAO = null;
	
	try {
		pstmt=con.prepareStatement(UPDATE_DEPOSIT_POINT);
		pstmt.setString(1, memVO.getMember_no());
		pstmt.setInt(2,depVO.getDeposit_change_money());
		pstmt.setString(3,memVO.getMember_no());
		pstmt.executeUpdate();
//pstmt.close();
	}catch(SQLException e) {
		e.printStackTrace();
		throw(e);
	} finally {
		if(pstmt != null) {
			pstmt.close();
		}
	}
}




@Override
public void updateMember_password(String member_account ,String member_password) {
	// TODO Auto-generated method stub
	
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		
		con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE_MEMBER_PASSWORD);
		

		
		pstmt.setString(1,member_password);
		pstmt.setString(2,member_account);
		pstmt.executeUpdate();
		
		
		
		
		
	} catch (SQLException se) {
		// TODO Auto-generated catch block
		
		throw new RuntimeException ("A database error occured"
				+se.getMessage());
		
	}finally {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException se) {
				throw new RuntimeException ("Could't load datebase driver "
						+se.getMessage());
			}
		}
		
		if (con != null) {
			try {
				con.close();
			}catch (SQLException se) {
				throw new RuntimeException ("Could't load datebase driver "
						+se.getMessage());
			}
		}
		
	}
	
	
	
	
	
	
	

	
	
	
	
}


public static void main(String[]args) { 
	MemDAOImpl dao = new MemDAOImpl();
	
	//新增
	MemVO memVO1 = new MemVO();
	memVO1.setMember_account("sfdgfdhg");
	memVO1.setMember_password("75446");
	memVO1.setMember_name("Beauty");
	memVO1.setMember_nick("M");
	memVO1.setMember_sex(new Integer (1));
	memVO1.setMember_birthday(java.sql.Date.valueOf("2005-01-01"));
	memVO1.setMember_address("桃園市中壢區中央路6號");
	memVO1.setMember_telephone("0933125697");
	memVO1.setMember_email("a7513@gmail.com");
	memVO1.setMember_picture(null);
	memVO1.setMember_credit_number("49053697461256");
	memVO1.setMember_back_verification(new Integer(123));
	memVO1.setMember_buildday(java.sql.Date.valueOf("2019-02-13"));
	memVO1.setMember_point(new Integer (1000));
	memVO1.setMember_status("1");
	
	dao.insert(memVO1);
	

	
	
	
	//修改
	
		MemVO memVO2 = new MemVO();
		memVO2.setMember_no("M000012");
		memVO2.setMember_account("sfdgfhg");
		memVO2.setMember_password("7546");
		memVO2.setMember_name("Beaut");
		memVO2.setMember_nick("s");
		memVO2.setMember_sex(new Integer (1));
		memVO2.setMember_birthday(java.sql.Date.valueOf("2004-01-01"));
		memVO2.setMember_address("桃園市中壢區中央路7號");
		memVO2.setMember_telephone("0933125697");
		memVO2.setMember_email("a753@gmail.com");
		memVO2.setMember_picture(null);
		memVO2.setMember_credit_number("490536461256");
		memVO2.setMember_back_verification(new Integer(123));
		memVO2.setMember_buildday(java.sql.Date.valueOf("2019-02-12"));
		memVO2.setMember_point(new Integer (2000));
		memVO2.setMember_status("1");
		
		dao.update(memVO2);
		
	
	
	//刪除
		
		dao.delete("M000016");
		
	
		
	//查詢
		
		MemVO memVO3 = dao.findByPrimaryKey("M000001");
		System.out.print(memVO3.getMember_no () + ",");
		System.out.print(memVO3.getMember_account() + ",");
		System.out.print(memVO3.getMember_password() + ",");
		System.out.print(memVO3.getMember_name() + ",");
		System.out.print(memVO3.getMember_nick() + ",");
		System.out.print(memVO3.getMember_sex() + ",");
		System.out.print(memVO3.getMember_birthday() + ",");
		System.out.print(memVO3.getMember_address() + ",");
		System.out.print(memVO3.getMember_telephone() + ",");
		System.out.print(memVO3.getMember_email() + ",");
		System.out.print(memVO3.getMember_picture() + ",");
		System.out.print(memVO3.getMember_credit_number() + ",");
		System.out.print(memVO3.getMember_back_verification() + ",");
		System.out.print(memVO3.getMember_buildday() + ",");
		System.out.print(memVO3.getMember_point() + ",");
		System.out.print(memVO3.getMember_status());
		
		System.out.println("----------------------");
		
	
	
	 List<MemVO> list = dao.getAll();
	for (MemVO amem : list) {
		System.out.print(amem.getMember_no  () + ",");
		System.out.print(amem.getMember_account() + ",");
		System.out.print(amem.getMember_password() + ",");
		System.out.print(amem.getMember_name() + ",");
		System.out.print(amem.getMember_nick() + ",");
		System.out.print(amem.getMember_sex() + ",");
		System.out.print(amem.getMember_birthday() + ",");
		System.out.print(amem.getMember_address() + ",");
		System.out.print(amem.getMember_telephone() + ",");
		System.out.print(amem.getMember_email() + ",");
		System.out.print(amem.getMember_picture() + ",");
		System.out.print(amem.getMember_credit_number() + ",");
		System.out.print(amem.getMember_back_verification() + ",");
		System.out.print(amem.getMember_buildday() + ",");
		System.out.print(amem.getMember_point() + ",");
		System.out.print(amem.getMember_status());
		System.out.println();
		
		
		
		
	 }

  }























































}