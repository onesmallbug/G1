package com.meals.model;

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

public class MealsDAOImpl implements MealsDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JOIN");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "INSERT INTO MEALS VALUES ('MEALS'||LPAD(MEALS_SEQ.NEXTVAL, 3, '0'),?,?,?,?,?)";
	private static final String GET_ALL = "SELECT * FROM MEALS";
	private static final String GET_ONE = "SELECT * FROM MEALS WHERE MEALS_NO = ?";
	private static final String DELETE = "DELETE FROM MEALS WHERE MEALS_NO = ?";
	private static final String UPDATE = "UPDATE MEALS SET MEALS_NAME=?, MEALS_PRICE_PRE=?, MEALS_PRICE_GEN=?, MEALS_BLOB=?, MEALS_STATUS=? WHERE MEALS_NO = ?";

	@Override
	public void insert(MealsVO MealsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			// UPDATE MEALS SET MEALS_NAME=?, MEALS_PRICE_PRE=?, MEALS_PRICE_GEN=?,
			// MEALS_BLOB=?, MEALS_STATUS=? WHERE MEALS_NO = ?

			pstmt.setString(1, MealsVO.getMeals_name());
			pstmt.setInt(2, MealsVO.getMeals_price_pre());
			pstmt.setInt(3, MealsVO.getMeals_price_gen());
			pstmt.setBytes(4, MealsVO.getMeals_blob());
			pstmt.setString(5, MealsVO.getMeals_status());

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
	public void update(MealsVO MealsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			// UPDATE MEALS SET MEALS_NAME=?, MEALS_PRICE_PRE=?, MEALS_PRICE_GEN=?,
			// MEALS_BLOB=?, MEALS_STATUS=? WHERE MEALS_NO = ?
			pstmt.setString(1, MealsVO.getMeals_name());
			pstmt.setInt(2, MealsVO.getMeals_price_pre());
			pstmt.setInt(3, MealsVO.getMeals_price_gen());
			pstmt.setBytes(4, MealsVO.getMeals_blob());
			pstmt.setString(5, MealsVO.getMeals_status());
			pstmt.setString(6, MealsVO.getMeals_no());

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
	public void delete(String meals_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			// DELETE FROM MEALS WHERE MEALS_NO = ?
			pstmt.setString(1, meals_no);

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
	public MealsVO findByPrimaryKey(String meals_no) {
		MealsVO mealsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);

			pstmt.setString(1, meals_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealsVO = new MealsVO();
				mealsVO.setMeals_no(meals_no);
				mealsVO.setMeals_name(rs.getString("MEALS_NAME"));
				mealsVO.setMeals_price_pre(rs.getInt("MEALS_PRICE_PRE"));
				mealsVO.setMeals_price_gen(rs.getInt("MEALS_PRICE_GEN"));
				mealsVO.setMeals_blob(rs.getBytes("MEALS_BLOB"));
				mealsVO.setMeals_status(rs.getString("MEALS_STATUS"));
				// UPDATE MEALS SET MEALS_NAME=?, MEALS_PRICE_PRE=?, MEALS_PRICE_GEN=?,
				// MEALS_BLOB=?, MEALS_STATUS=? WHERE MEALS_NO = ?

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
		return mealsVO;
	}

	@Override
	public List<MealsVO> getAll() {
		List<MealsVO> list = new ArrayList<MealsVO>();
		MealsVO mealsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mealsVO = new MealsVO();
				mealsVO.setMeals_no(rs.getString("MEALS_NO"));
				mealsVO.setMeals_name(rs.getString("MEALS_NAME"));
				mealsVO.setMeals_price_pre(rs.getInt("MEALS_PRICE_PRE"));
				mealsVO.setMeals_price_gen(rs.getInt("MEALS_PRICE_GEN"));
				mealsVO.setMeals_blob(rs.getBytes("MEALS_BLOB"));
				mealsVO.setMeals_status(rs.getString("MEALS_STATUS"));
				// UPDATE MEALS SET MEALS_NAME=?, MEALS_PRICE_PRE=?, MEALS_PRICE_GEN=?,
				// MEALS_BLOB=?, MEALS_STATUS=? WHERE MEALS_NO = ?
				list.add(mealsVO);
			}
			System.out.println(list);
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
		return list;
	}
}
