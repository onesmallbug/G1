package com.perm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PermDAOImpl implements PermDAO_interface{
	
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String username = "JOIN";
	String password = "123456";
	
	private static final String INSERT_PERM =
			"INSERT INTO PERMISSION (permission_no,permission_name) VALUES (PERMISSION_seq.NEXTVAL,?)";
	private static final String GET_ALL_PERM =
			"SELECT permission_no,permission_name FROM PERMISSION order by permission_no ";
	
	private static final String GET_ONE_PERM = 
			"SELECT permission_no,permission_name FROM PERMISSION where permission_no = ?";
	
	private static final String DELETE = 
			"DELETE FROM PERMISSION where permission_no = ? ";
	private static final String UPDATE = 
			"UPDATE PERMISSION set permission_name = ? where permission_no=?";
	
	
	

	@Override
	public void insert(PermVO permVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,username,password);
			pstmt = con.prepareStatement(INSERT_PERM);
			
			pstmt.setString(1,permVO.getPermission_name());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver."
					+e.getMessage());	
					
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured."
					+se.getMessage());
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
	public void update(PermVO permVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null; 
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1,permVO.getPermission_name());
			pstmt.setString(2,permVO.getPermission_no());
			
			pstmt.executeUpdate();
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException ("Couldn't load database driver."
					+e.getMessage());
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException ("A database error occured."
					+se.getMessage());
			
		}if(pstmt != null) {
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

	@Override
	public void delete(String permission_no) {
		// TODO Auto-generated method stub
		
		Connection con  = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(url,username,password);
			pstmt=con.prepareStatement(DELETE);
			
			pstmt.setString(1,permission_no);
			System.out.println(pstmt.executeUpdate());
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException ("couldn't load database driver."
					+ e.getMessage());
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occuired."
					+ se.getMessage());
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
	public PermVO findByPrimaryKey(String permission_no) {
		// TODO Auto-generated method stub
		
		PermVO permVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(GET_ONE_PERM);
			
			pstmt.setString(1,permission_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				permVO = new PermVO();
				permVO.setPermission_no(rs.getString("permission_no"));
				permVO.setPermission_name(rs.getString("permission_name"));
				
				
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("couldn't load database driver."
					+e.getMessage());
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occuired."
					+se.getMessage());
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
		
		
		
		return permVO;
	}

	@Override
	public List<PermVO> getAll() {
		// TODO Auto-generated method stub
		
		List<PermVO> list = new ArrayList<PermVO>();
		PermVO permVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(GET_ALL_PERM);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				permVO = new PermVO();
				permVO.setPermission_no	(rs.getString("Permission_no"));
				permVO.setPermission_name(rs.getString("Permission_name"));
				list.add(permVO);
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("couldn't load database driver."
					+ e.getMessage());
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured."
					+se.getMessage());
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
			if(con!= null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		
		
		return list;
	}
	
	
	
	
	
	public static void main(String[]args) {
		
		PermDAOImpl dao = new PermDAOImpl();
		
		//新增
		
		PermVO permVO1 = new PermVO();
		permVO1.setPermission_name("雅筑");
		dao.insert(permVO1);
		
		//修改
		
		PermVO permVO2 = new PermVO();
		permVO2.setPermission_no("1");
		permVO2.setPermission_name("戀愛");
		dao.update(permVO2);
		
		//刪除
		dao.delete("3");
		
		//查詢
		
		PermVO permVO3 = new PermVO();
		System.out.print(permVO3.getPermission_no()+",");
		System.out.print(permVO3.getPermission_name());
		System.out.println();
		
		
		//查詢
		
		List<PermVO> list = dao.getAll();
		for (PermVO aPerm : list) {
			System.out.print(aPerm.getPermission_no()+",");
			System.out.print(aPerm.getPermission_name());
			System.out.println();
		}
		
		
		
	}
	

	
}
