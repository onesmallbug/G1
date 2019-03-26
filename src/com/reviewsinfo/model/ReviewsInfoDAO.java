package com.reviewsinfo.model;

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

public class ReviewsInfoDAO implements ReviewsInfoDAO_interface {

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
			"INSERT INTO reviewsinfo (reviews_no, movie_no, reviews_title, reviews_auther, reviews_times, reviews_con, reviews_pic) VALUES (reviewsinfo_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT reviews_no, movie_no, reviews_title, reviews_auther, to_char(reviews_times,'yyyy-mm-dd') reviews_times, reviews_con, reviews_pic FROM reviewsinfo order by reviews_no";
	private static final String GET_ONE_STMT = 
			"SELECT reviews_no, movie_no, reviews_title, reviews_auther, to_char(reviews_times,'yyyy-mm-dd') reviews_times, reviews_con, reviews_pic FROM reviewsinfo where reviews_no = ?";
	private static final String DELETE = 
			"DELETE FROM reviewsinfo where reviews_no = ?";
	private static final String UPDATE = 
			"UPDATE reviewsinfo set movie_no=?, reviews_title=?, reviews_auther=?, reviews_times=?, reviews_con=?, reviews_pic=? where reviews_no = ?";
	
	@Override
	public void insert(ReviewsInfoVO reviewsinfoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, reviewsinfoVO.getMovie_no());
			pstmt.setString(2, reviewsinfoVO.getReviews_title());
			pstmt.setString(3, reviewsinfoVO.getReviews_auther());
			pstmt.setDate(4, reviewsinfoVO.getReviews_times());
			pstmt.setString(5, reviewsinfoVO.getReviews_con());
			pstmt.setBytes(6, reviewsinfoVO.getReviews_pic());

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
	public void update(ReviewsInfoVO reviewsinfoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, reviewsinfoVO.getMovie_no());
			pstmt.setString(2, reviewsinfoVO.getReviews_title());
			pstmt.setString(3, reviewsinfoVO.getReviews_auther());
			pstmt.setDate(4, reviewsinfoVO.getReviews_times());
			pstmt.setString(5, reviewsinfoVO.getReviews_con());
			pstmt.setBytes(6, reviewsinfoVO.getReviews_pic());
			pstmt.setInt(7, reviewsinfoVO.getReviews_no());

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
	public void delete(Integer reviews_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, reviews_no);

			pstmt.executeUpdate();

			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			
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
	public ReviewsInfoVO findByPrimaryKey(Integer reviews_no) {

		ReviewsInfoVO reviewsinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reviews_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				reviewsinfoVO = new ReviewsInfoVO();
				
				reviewsinfoVO.setReviews_no(rs.getInt("reviews_no"));
				reviewsinfoVO.setMovie_no(rs.getString("movie_no"));
				reviewsinfoVO.setReviews_title(rs.getString("reviews_title"));
				reviewsinfoVO.setReviews_auther(rs.getString("reviews_auther"));
				reviewsinfoVO.setReviews_times(rs.getDate("reviews_times"));
				reviewsinfoVO.setReviews_con(rs.getString("reviews_con"));
				reviewsinfoVO.setReviews_pic(rs.getBytes("reviews_pic"));
			}

			
		} catch (SQLException se) {
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
		return reviewsinfoVO;
	}
	
	@Override
	public List<ReviewsInfoVO> getAll() {
		List<ReviewsInfoVO> list = new ArrayList<ReviewsInfoVO>();
		ReviewsInfoVO reviewsinfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				reviewsinfoVO = new ReviewsInfoVO();
				
				reviewsinfoVO.setReviews_no(rs.getInt("reviews_no"));
				reviewsinfoVO.setMovie_no(rs.getString("movie_no"));
				reviewsinfoVO.setReviews_title(rs.getString("reviews_title"));
				reviewsinfoVO.setReviews_auther(rs.getString("reviews_auther"));
				reviewsinfoVO.setReviews_times(rs.getDate("reviews_times"));
				reviewsinfoVO.setReviews_con(rs.getString("reviews_con"));
				reviewsinfoVO.setReviews_pic(rs.getBytes("reviews_pic"));
				list.add(reviewsinfoVO); 
			}

			
		} catch (SQLException se) {
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

}
