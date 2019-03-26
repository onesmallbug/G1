package com.mealsorderdetail.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class MealsorderdetailDAO implements MealsorderdetailDAO_interface {
	
	
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
			"INSERT INTO MEALSORDERDETAIL (order_no,meals_no,mo_count) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT order_no,meals_no,mo_count FROM MEALSORDERDETAIL order by order_no";
	private static final String GET_ONE_STMT = 
			"SELECT order_no,meals_no,mo_count FROM MEALSORDERDETAIL where order_no = ?";
	private static final String DELETE = 
			"DELETE FROM MEALSORDERDETAIL where order_no = ?";
	private static final String UPDATE = 
			"UPDATE MEALSORDERDETAIL set meals_no=?, mo_count=? where order_no = ?";


	@Override
	public void insert(MealsorderdetailVO mealsorderdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mealsorderdetailVO.getOrder_no());
			pstmt.setString(2, mealsorderdetailVO.getMeals_no());
			pstmt.setInt(3, mealsorderdetailVO.getMo_count());
			
			
			

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
	public void update(MealsorderdetailVO mealsorderdetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, mealsorderdetailVO.getMeals_no());
			pstmt.setInt(2, mealsorderdetailVO.getMo_count());
			pstmt.setString(3, mealsorderdetailVO.getOrder_no());
			
			

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
	public void delete(String order_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, order_no);

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
	public MealsorderdetailVO findByPrimaryKey(String order_no) {
		MealsorderdetailVO mealsorderdetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, order_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealsorderdetailVO = new MealsorderdetailVO();
				mealsorderdetailVO.setOrder_no(rs.getString("order_no"));
				mealsorderdetailVO.setMeals_no(rs.getString("meals_no"));
				mealsorderdetailVO.setMo_count(rs.getInt("mo_count"));
				
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
		
		return mealsorderdetailVO;
	}

	@Override
	public List<MealsorderdetailVO> getAll() {
		List<MealsorderdetailVO> list = new ArrayList<MealsorderdetailVO>();
		MealsorderdetailVO mealsorderdetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealsorderdetailVO = new MealsorderdetailVO();
				mealsorderdetailVO.setOrder_no(rs.getString("order_no"));
				mealsorderdetailVO.setMeals_no(rs.getString("meals_no"));
				mealsorderdetailVO.setMo_count(rs.getInt("mo_count"));
				
				list.add(mealsorderdetailVO); // Store the row in the list
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
