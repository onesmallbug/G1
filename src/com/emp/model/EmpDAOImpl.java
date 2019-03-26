package com.emp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Emp2;

public class EmpDAOImpl implements EmpDAO_interface {
	
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
	
//	 String driver = "oracle.jdbc.driver.OracleDriver";
//	 String url = "jdbc:oracle:thin:@localhost:1521:XE";
//	 String username = "JOIN";
//	 String password = "123456";
	 
	 private static final String INSERT_EMP =
			 "INSERT INTO EMPLOYEE (employee_no,employee_name,employee_sex,employee_builddate,employee_quitdate,employee_ability,employee_status,employee_password) VALUES (EMPLOYEE_seq.NEXTVAL,?,?,?,?,?,?,?)";

	 private static final String GET_ALL_EMP = 
			 "SELECT employee_no,employee_name,employee_sex,employee_builddate,employee_quitdate,employee_ability,employee_status,employee_password FROM EMPLOYEE order by employee_no DESC";
	 
	 private static final String GET_ONE_EMP = 
			 "SELECT employee_no,employee_name,employee_sex,employee_builddate,employee_quitdate,employee_ability,employee_status,employee_password FROM EMPLOYEE where employee_no = ?";
	 
	 private static final String GET_ONE_BY_EMPLOYEE_NAME = 
			 "SELECT employee_no,employee_name,employee_sex,employee_builddate,employee_quitdate,employee_ability,employee_status,employee_password FROM EMPLOYEE where employee_name = ?";
	 
	 private static final String DELETE =
			 "DELETE from EMPLOYEE where employee_no = ?";
	 private static final String UPDATE = 
			 "UPDATE EMPLOYEE set employee_name=?,employee_sex=?,employee_builddate=?,employee_quitdate=?,employee_ability=?,employee_status=?,employee_password=? where employee_no = ?";
	 
	@Override
	public void insert(EmpVO empVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_EMP);
			
			pstmt.setString(1, empVO.getEmployee_name());
			pstmt.setInt(2, empVO.getEmployee_sex());
			pstmt.setDate(3, empVO.getEmployee_builddate());
			pstmt.setDate(4, empVO.getEmployee_quitdate());
			pstmt.setString(5, empVO.getEmployee_ability());
			pstmt.setString(6, empVO.getEmployee_status());
			pstmt.setString(7, empVO.getEmployee_password());
			
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error ocurred."
					+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
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
	public void update(EmpVO empVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, empVO.getEmployee_name());
			pstmt.setInt(2, empVO.getEmployee_sex());
			pstmt.setDate(3, empVO.getEmployee_builddate());
			pstmt.setDate(4, empVO.getEmployee_quitdate());
			pstmt.setString(5, empVO.getEmployee_ability());
			pstmt.setString(6, empVO.getEmployee_status());
			pstmt.setString(7, empVO.getEmployee_password());
			pstmt.setString(8,empVO.getEmployee_no());
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error ocurried"
					+se.getMessage());
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch (SQLException se) {
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
	public void delete(String employee_no) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, employee_no);
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error ocurried."
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
	public EmpVO findByPrimaryKey(String employee_no) {
		// TODO Auto-generated method stub
		
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_EMP);
			
			pstmt.setString(1,employee_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				empVO = new EmpVO();
				empVO.setEmployee_no(rs.getString("employee_no"));
				empVO.setEmployee_name	(rs.getString("employee_name"));
				empVO.setEmployee_sex(rs.getInt("employee_sex"));
				empVO.setEmployee_builddate(rs.getDate("employee_builddate"));
				empVO.setEmployee_quitdate(rs.getDate("employee_quitdate"));
				empVO.setEmployee_ability(rs.getString("employee_ability"));
				empVO.setEmployee_status(rs.getString("employee_status"));
				empVO.setEmployee_password(rs.getString("employee_password"));
				
			}
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException ("A database error ocurrid."
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
			if (con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		
		return empVO;
	}
	
	
	
	@Override
	public EmpVO findByEmployee_name(String employee_name) {
		// TODO Auto-generated method stub
		
		
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_EMPLOYEE_NAME);
			
			pstmt.setString(1,employee_name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				empVO = new EmpVO();
				empVO.setEmployee_no(rs.getString("employee_no"));
				empVO.setEmployee_name	(rs.getString("employee_name"));
				empVO.setEmployee_sex(rs.getInt("employee_sex"));
				empVO.setEmployee_builddate(rs.getDate("employee_builddate"));
				empVO.setEmployee_quitdate(rs.getDate("employee_quitdate"));
				empVO.setEmployee_ability(rs.getString("employee_ability"));
				empVO.setEmployee_status(rs.getString("employee_status"));
				empVO.setEmployee_password(rs.getString("employee_password"));
				
			}
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException ("A database error ocurrid."
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
			if (con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		
		
		
		
		
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		// TODO Auto-generated method stub
		
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_EMP);
			
			rs =pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				empVO = new EmpVO();
				empVO.setEmployee_no(rs.getString("employee_no"));
				empVO.setEmployee_name(rs.getString("employee_name"));
				empVO.setEmployee_sex(rs.getInt("employee_sex"));
				empVO.setEmployee_builddate(rs.getDate("employee_builddate"));
				empVO.setEmployee_quitdate(rs.getDate("employee_quitdate"));
				empVO.setEmployee_ability(rs.getString("employee_ability"));
				empVO.setEmployee_status(rs.getString("employee_status"));
				empVO.setEmployee_password(rs.getString("employee_password"));
				list.add(empVO);
				
			}
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error ocurried."
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
		
		return list;
	}
	
	
	@Override
	public List<EmpVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from EMPLOYEE "
			          + jdbcUtil_CompositeQuery_Emp2.get_WhereCondition(map)
			          + "order by employee_no";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			
			
			rs =pstmt.executeQuery();
			
			
			while(rs.next()) {
				
				empVO = new EmpVO();
				empVO.setEmployee_no(rs.getString("employee_no"));
				empVO.setEmployee_name(rs.getString("employee_name"));
				empVO.setEmployee_sex(rs.getInt("employee_sex"));
				empVO.setEmployee_builddate(rs.getDate("employee_builddate"));
				empVO.setEmployee_quitdate(rs.getDate("employee_quitdate"));
				empVO.setEmployee_ability(rs.getString("employee_ability"));
				empVO.setEmployee_status(rs.getString("employee_status"));
				empVO.setEmployee_password(rs.getString("employee_password"));
				list.add(empVO);
				
			}
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error ocurried."
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
		
		return list;
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		
		EmpDAOImpl dao = new EmpDAOImpl();
		
		//�憓�
		
		EmpVO empVO1 = new EmpVO();
		empVO1.setEmployee_name("Android");
		empVO1.setEmployee_sex(new Integer(0));
		empVO1.setEmployee_builddate(java.sql.Date.valueOf("2018-09-14"));
		empVO1.setEmployee_quitdate(java.sql.Date.valueOf("2019-11-04"));
		empVO1.setEmployee_ability("敺瑟��");
		empVO1.setEmployee_status("1");
		empVO1.setEmployee_password("123456");
		dao.insert(empVO1);
		
		
		//靽格
		
		EmpVO empVO2 = new EmpVO();
		empVO2.setEmployee_no("15");
		empVO2.setEmployee_name("Sue");
		empVO2.setEmployee_sex(new Integer(1));
		empVO2.setEmployee_builddate(java.sql.Date.valueOf("2018-09-14"));
		empVO2.setEmployee_quitdate(java.sql.Date.valueOf("2019-11-04"));
		empVO2.setEmployee_ability("����");
		empVO2.setEmployee_status("1");
		empVO2.setEmployee_password("123456");
		dao.update(empVO2);
		
		//��
		
		dao.delete("16");
		
		//�閰�
		EmpVO empVO3 = dao.findByPrimaryKey("10");
		System.out.print(empVO3.getEmployee_no()+",");
		System.out.print(empVO3.getEmployee_name()+",");
		System.out.print(empVO3.getEmployee_sex()+",");
		System.out.print(empVO3.getEmployee_builddate()+",");
		System.out.print(empVO3.getEmployee_quitdate()+",");
		System.out.print(empVO3.getEmployee_ability()+",");
		System.out.print(empVO3.getEmployee_status()+",");
		System.out.print(empVO3.getEmployee_password());
		System.out.println("-------------------------");
		
		
		//�閰�
		List<EmpVO> list = dao.getAll();
		for (EmpVO aEmp : list ) {
			System.out.print(aEmp.getEmployee_no()+",");
			System.out.print(aEmp.getEmployee_name()+",");
			System.out.print(aEmp.getEmployee_sex()+",");
			System.out.print(aEmp.getEmployee_builddate()+",");
			System.out.print(aEmp.getEmployee_quitdate()+",");
			System.out.print(aEmp.getEmployee_ability()+",");
			System.out.print(aEmp.getEmployee_status()+",");
			System.out.print(aEmp.getEmployee_password());
			System.out.println();
			
			
		}
		
	}

	

	
	
	
	

}
