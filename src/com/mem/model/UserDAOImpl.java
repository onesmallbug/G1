package com.mem.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.BLOB;




public class UserDAOImpl implements UserDAO_interface {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String username = "JOIN";
	String password = "123456";
	
	private static final String INSERT_MEM = 
			"INSERT INTO MEMBER (MEMBER_NO,MEMBER_ACCOUNT,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_NICK,MEMBER_SEX,MEMBER_BIRTHDAY,MEMBER_ADDRESS,MEMBER_EMAIL,MEMBER_PICTURE,MEMBER_CREDIT_NUMBER,MEMBER_BACK_VERIFICATION,MEMBER_BUILDDAY,MEMBER_POINT,MEMBER_STATUS) VALUES ('M'||LPAD(to_char(MEMBER_seq.NEXTVAL),6,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String GET_ALL_MEM = 
			"SELECT MEMBER_NO,MEMBER_ACCOUNT,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_NICK,MEMBER_SEX,to_char(MEMBER_BIRTHDAY,'yyyy-mm-dd')MEMBER_BIRTHDAY,MEMBER_ADDRESS,MEMBER_EMAIL , MEMBER_PICTURE,MEMBER_CREDIT_NUMBER,MEMBER_BACK_VERIFICATION,MEMBER_BUILDDAY,MEMBER_POINT,MEMBER_STATUS FROM MEMBER order by MEMBER_NO";
		
	private static final String GET_ONE_MEM = 
			"SELECT MEMBER_NO,MEMBER_ACCOUNT,MEMBER_PASSWORD,MEMBER_NAME,MEMBER_NICK,MEMBER_SEX,to_char(MEMBER_BIRTHDAY,'yyyy-mm-dd')MEMBER_BIRTHDAY,MEMBER_ADDRESS,MEMBER_EMAIL,MEMBER_PICTURE,MEMBER_CREDIT_NUMBER,MEMBER_BACK_VERIFICATION,MEMBER_BUILDDAY,MEMBER_POINT,MEMBER_STATUS FROM MEMBER where MEMBER_NO = ?";
	
	private static final String DELETE = 
			"DELETE FROM MEMBER where MEMBER_NO = ?";
	private static final String UPDATE = 
			"UPDATE MEMBER set MEMBER_ACCOUNT =?,MEMBER_PASSWORD=?,MEMBER_NAME=?,MEMBER_NICK=?,MEMBER_SEX=?,MEMBER_BIRTHDAY=?,MEMBER_ADDRESS=?,MEMBER_EMAIL=?,MEMBER_PICTURE=? ,MEMBER_CREDIT_NUMBER=?,MEMBER_BACK_VERIFICATION=?,MEMBER_BUILDDAY=?,MEMBER_POINT=?,MEMBER_STATUS=? where MEMBER_NO = ?";
	
	

	@Override
	public void insert(UserVO userVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(INSERT_MEM);
			
			pstmt.setString(1,userVO.getMember_account());
			pstmt.setString(2,userVO.getMember_password());
			pstmt.setString(3,userVO.getMember_name());
			pstmt.setString(4,userVO.getMember_nick());
			pstmt.setInt(5,userVO.getMember_sex());
			pstmt.setDate(6,userVO.getMember_birthday());
			pstmt.setString(7,userVO.getMember_address());
			pstmt.setString(8,userVO.getMember_email());
			pstmt.setBlob(9,userVO.getMember_picture());
			pstmt.setString(10,userVO.getMember_credit_number());
			pstmt.setInt(11,userVO.getMember_back_verification());
			pstmt.setDate(12,userVO.getMember_buildday());
			pstmt.setInt(13,userVO.getMember_point());
			pstmt.setString(14,userVO.getMember_status());
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
	}finally {
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
	public UserVO findByPrimaryKey(String member_no) {
		// TODO Auto-generated method stub
		
		UserVO userVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(GET_ONE_MEM);
			
			pstmt.setString(1,member_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				userVO = new UserVO();
				userVO.setMember_no(rs.getString("member_no"));
				userVO.setMember_account(rs.getString("member_account"));
				userVO.setMember_password(rs.getString("member_password"));
				userVO.setMember_name(rs.getString("member_name"));
				userVO.setMember_nick(rs.getString("member_nick"));
				userVO.setMember_sex(rs.getInt("member_sex"));
				userVO.setMember_birthday(rs.getDate("member_birthday"));
				userVO.setMember_address(rs.getString("member_address"));
				userVO.setMember_email(rs.getString("member_email"));
				userVO.setMember_picture((BLOB) rs.getBlob("member_picture"));
				userVO.setMember_credit_number(rs.getString("member_credit_number"));
				userVO.setMember_back_verification(rs.getInt("member_back_verification"));
				userVO.setMember_buildday(rs.getDate("member_buildday"));
				userVO.setMember_point(rs.getInt("member_point"));
				userVO.setMember_status(rs.getString("member_status"));
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		return userVO;
	}

	@Override
	public List<UserVO> getAll() {
		// TODO Auto-generated method stub
		List<UserVO> list = new ArrayList<UserVO>();
		UserVO userVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(GET_ALL_MEM);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				userVO = new UserVO();
				userVO.setMember_no(rs.getString("member_no"));
				userVO.setMember_account(rs.getString("member_account"));
				userVO.setMember_password(rs.getString("member_password"));
				userVO.setMember_name(rs.getString("member_name"));
				userVO.setMember_nick(rs.getString("member_nick"));
				userVO.setMember_sex(rs.getInt("member_sex"));
				userVO.setMember_birthday(rs.getDate("member_birthday"));
				userVO.setMember_address(rs.getString("member_address"));
				userVO.setMember_email(rs.getString("member_email"));
				userVO.setMember_picture((BLOB) rs.getBlob("member_picture"));
				userVO.setMember_credit_number(rs.getString("member_credit_number"));
				userVO.setMember_back_verification(rs.getInt("member_back_verification"));
				userVO.setMember_buildday(rs.getDate("member_buildday"));
				userVO.setMember_point(rs.getInt("member_point"));
				userVO.setMember_status(rs.getString("member_status"));
				list.add(userVO);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Could't load database driver"
					+ e.getMessage());
			
			
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
	public void update(UserVO userVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setString(1,userVO.getMember_account());
			pstmt.setString(2,userVO.getMember_password());
			pstmt.setString(3,userVO.getMember_name());
			pstmt.setString(4,userVO.getMember_nick());
			pstmt.setInt(5,userVO.getMember_sex());
			pstmt.setDate(6,userVO.getMember_birthday());
			pstmt.setString(7,userVO.getMember_address());
			pstmt.setString(8,userVO.getMember_email());
			pstmt.setBlob(9,userVO.getMember_picture());
			pstmt.setString(10,userVO.getMember_credit_number());
			pstmt.setInt(11,userVO.getMember_back_verification());
			pstmt.setDate(12,userVO.getMember_buildday());
			pstmt.setInt(13,userVO.getMember_point());
			pstmt.setString(14,userVO.getMember_status());
			pstmt.setString(15,userVO.getMember_no());
			pstmt.executeUpdate();
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			throw new RuntimeException ("Could't load datebase driver "
					+e.getMessage());
			
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
			Class.forName(driver);
			con = DriverManager.getConnection(url,username,password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, member_no);
			
			pstmt.executeUpdate();
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException ("Could't load datebase driver "
					+e.getMessage());
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




	
	
	
	
	

public static void main(String[]args) { 
	UserDAOImpl dao = new UserDAOImpl();
	
	//新增
	UserVO userVO1 = new UserVO();
	userVO1.setMember_account("sfdgfdhg");
	userVO1.setMember_password("75446");
	userVO1.setMember_name("Beauty");
	userVO1.setMember_nick("M");
	userVO1.setMember_sex(new Integer (1));
	userVO1.setMember_birthday(java.sql.Date.valueOf("2005-01-01"));
	userVO1.setMember_address("桃園市中壢區中央路6號");
	userVO1.setMember_email("a7513@gmail.com");
	userVO1.setMember_picture(null);
	userVO1.setMember_credit_number("49053697461256");
	userVO1.setMember_back_verification(new Integer(123));
	userVO1.setMember_buildday(java.sql.Date.valueOf("2019-02-13"));
	userVO1.setMember_point(new Integer (1000));
	userVO1.setMember_status("1");
	
	dao.insert(userVO1);
	

	
	
	
	//修改
	
		UserVO userVO2 = new UserVO();
		userVO2.setMember_no("M000012");
		userVO2.setMember_account("sfdgfhg");
		userVO2.setMember_password("7546");
		userVO2.setMember_name("Beaut");
		userVO2.setMember_nick("s");
		userVO2.setMember_sex(new Integer (1));
		userVO2.setMember_birthday(java.sql.Date.valueOf("2004-01-01"));
		userVO2.setMember_address("桃園市中壢區中央路7號");
		userVO2.setMember_email("a753@gmail.com");
		userVO2.setMember_picture(null);
		userVO2.setMember_credit_number("490536461256");
		userVO2.setMember_back_verification(new Integer(123));
		userVO2.setMember_buildday(java.sql.Date.valueOf("2019-02-12"));
		userVO2.setMember_point(new Integer (2000));
		userVO2.setMember_status("1");
		
		dao.update(userVO2);
		
	
	
	//刪除
		
		dao.delete("M000016");
		
	
		
	//查詢
		
		UserVO userVO3 = dao.findByPrimaryKey("M000001");
		System.out.print(userVO3.getMember_no () + ",");
		System.out.print(userVO3.getMember_account() + ",");
		System.out.print(userVO3.getMember_password() + ",");
		System.out.print(userVO3.getMember_name() + ",");
		System.out.print(userVO3.getMember_nick() + ",");
		System.out.print(userVO3.getMember_sex() + ",");
		System.out.print(userVO3.getMember_birthday() + ",");
		System.out.print(userVO3.getMember_address() + ",");
		System.out.print(userVO3.getMember_email() + ",");
		System.out.print(userVO3.getMember_picture() + ",");
		System.out.print(userVO3.getMember_credit_number() + ",");
		System.out.print(userVO3.getMember_back_verification() + ",");
		System.out.print(userVO3.getMember_buildday() + ",");
		System.out.print(userVO3.getMember_point() + ",");
		System.out.print(userVO3.getMember_status());
		
		System.out.println("----------------------");
		
	
	
	 List<UserVO> list = dao.getAll();
	for (UserVO aUser : list) {
		System.out.print(aUser.getMember_no  () + ",");
		System.out.print(aUser.getMember_account() + ",");
		System.out.print(aUser.getMember_password() + ",");
		System.out.print(aUser.getMember_name() + ",");
		System.out.print(aUser.getMember_nick() + ",");
		System.out.print(aUser.getMember_sex() + ",");
		System.out.print(aUser.getMember_birthday() + ",");
		System.out.print(aUser.getMember_address() + ",");
		System.out.print(aUser.getMember_email() + ",");
		System.out.print(aUser.getMember_picture() + ",");
		System.out.print(aUser.getMember_credit_number() + ",");
		System.out.print(aUser.getMember_back_verification() + ",");
		System.out.print(aUser.getMember_buildday() + ",");
		System.out.print(aUser.getMember_point() + ",");
		System.out.print(aUser.getMember_status());
		System.out.println();
		
		
		
		
	 }

  }

}