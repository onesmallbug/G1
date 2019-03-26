package com.empper._det.model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





public class Empper_detDAOImpl implements Empper_detDAO_interface{

	 String driver = "oracle.jdbc.driver.OracleDriver";
	 String url = "jdbc:oracle:thin:@localhost:1521:XE";
	 String username = "JOIN";
	 String password = "123456";
	 
	 private static final String INSERT_EMPPER_DET = 
			 "INSERT INTO EMPPERMISSIONDETAIL (employee_no,permission_no) VALUES (?,?)";
	 private static final String GET_ALL_EMPPER_DET =
			 "SELECT employee_no,permission_no FROM EMPPERMISSIONDETAIL order by employee_no  ";
	 private static final String GET_ONE_EMPPER_DET =
			 "SELECT employee_no,permission_no FROM EMPPERMISSIONDETAIL where employee_no=? AND permission_no=? ";
	 private static final String DELETE =
			 "DELETE FROM EMPPERMISSIONDETAIL where (employee_no=? AND permission_no=?)";
	 private static final String UPDATE =
			 "UPDATE EMPPERMISSIONDETAIL SET employee_no=?,permission_no=? where employee_no=? AND permission_no=?";
	 
	
	
	
	
	@Override
	public void insert(Empper_detVO empper_detVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,username,password);
			pstmt = con.prepareStatement(INSERT_EMPPER_DET);
			
			
			
			
			pstmt.setString(1,empper_detVO.getEmployee_no());
			pstmt.setString(2,empper_detVO.getPermission_no());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("couldn't load database driver."
					+e.getMessage());
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error orrcued."
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
					}catch(SQLException se) {
						se.printStackTrace(System.err);
					}
				}
			}
		
	}

	@Override
	public void update(Empper_detVO empper_detVOold,Empper_detVO empper_detVOnew) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(UPDATE);
			
		
			
			
			pstmt.setString(1,empper_detVOnew.getEmployee_no());
			pstmt.setString(2,empper_detVOnew.getPermission_no());
			pstmt.setString(3,empper_detVOold.getEmployee_no());
			pstmt.setString(4,empper_detVOold.getPermission_no());
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver."
					+e.getMessage());
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException ("A database error occured."
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
	public void delete(String employee_no, String permission_no) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1,employee_no);
			pstmt.setString(2,permission_no);
			
			pstmt.executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("couldn't load database driver."
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
	public Empper_detVO findByPrimaryKey(String employee_no , String permission_no) {
		// TODO Auto-generated method stub
		Empper_detVO empper_detVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(GET_ONE_EMPPER_DET);
			
			pstmt.setString(1,employee_no);
			pstmt.setString(2,permission_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				empper_detVO = new Empper_detVO();
				empper_detVO.setEmployee_no(rs.getString("employee_no"));
				empper_detVO.setPermission_no(rs.getString("permission_no"));
				
				
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
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
		
		
		
		return empper_detVO;
	}

	@Override
	public List<Empper_detVO> getAll() {
		// TODO Auto-generated method stub
		
		List<Empper_detVO> list = new ArrayList<Empper_detVO>();
		Empper_detVO empper_detVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(GET_ALL_EMPPER_DET);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				empper_detVO = new Empper_detVO();
				empper_detVO.setEmployee_no(rs.getString("employee_no"));
				empper_detVO.setPermission_no(rs.getString("permission_no"));
				list.add(empper_detVO);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	public static void main(String[]args) {
		
		Empper_detDAOImpl dao = new Empper_detDAOImpl();
		
		//新增
		
	Empper_detVO empper_detVO1 = new Empper_detVO();
	empper_detVO1.setEmployee_no("8");
	empper_detVO1.setPermission_no("15");
		dao.insert(empper_detVO1);
		
		//修改
		
		Empper_detVO empper_detVO2 = new Empper_detVO();
		
		empper_detVO2.setEmployee_no("1");
		empper_detVO2.setPermission_no("1");
		
		Empper_detVO empper_detVO4 = new Empper_detVO();
		
		empper_detVO4.setEmployee_no("1");
		empper_detVO4.setPermission_no("15");
		
		dao.update(empper_detVO2, empper_detVO4);
		
		//刪除
		dao.delete("3","3");
		
		//查詢
		
		Empper_detVO empper_detVO3 = new Empper_detVO();
		System.out.print(empper_detVO3.getEmployee_no()+",");
		System.out.print(empper_detVO3.getPermission_no());
		System.out.println();
		
		
		//查詢
		
		List<Empper_detVO> list = dao.getAll();
		for (Empper_detVO aEmpper_det : list) {
			System.out.print(aEmpper_det.getEmployee_no()+",");
			System.out.print(aEmpper_det.getPermission_no());
			System.out.println();
		}
		
		
	}

}
