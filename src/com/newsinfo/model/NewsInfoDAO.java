package com.newsinfo.model;

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

public class NewsInfoDAO implements NewsInfoDAO_interface {

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
		"INSERT INTO newsinfo (news_no, movie_no, news_title, news_auther, news_times, news_con, news_pic) VALUES (newsinfo_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT news_no, movie_no, news_title, news_auther, to_char(news_times,'yyyy-mm-dd') news_times, news_con, news_pic FROM newsinfo order by news_no";
	private static final String GET_ONE_STMT = 
		"SELECT news_no, movie_no, news_title, news_auther, to_char(news_times,'yyyy-mm-dd') news_times, news_con, news_pic FROM newsinfo where news_no = ?";
	private static final String DELETE = 
		"DELETE FROM newsinfo where news_no = ?";
	private static final String UPDATE = 
		"UPDATE newsinfo set movie_no=?, news_title=?, news_auther=?, news_times=?, news_con=?, news_pic=? where news_no = ?";

	
	@Override
	public void insert(NewsInfoVO newsinfoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, newsinfoVO.getMovie_no());
			pstmt.setString(2, newsinfoVO.getNews_title());
			pstmt.setString(3, newsinfoVO.getNews_auther());
			pstmt.setDate(4, newsinfoVO.getNews_times());
			pstmt.setString(5, newsinfoVO.getNews_con());
			pstmt.setBytes(6, newsinfoVO.getNews_pic());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(NewsInfoVO newsinfoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, newsinfoVO.getMovie_no());
			pstmt.setString(2, newsinfoVO.getNews_title());
			pstmt.setString(3, newsinfoVO.getNews_auther());
			pstmt.setDate(4, newsinfoVO.getNews_times());
			pstmt.setString(5, newsinfoVO.getNews_con());
			pstmt.setBytes(6, newsinfoVO.getNews_pic());
			pstmt.setInt(7, newsinfoVO.getNews_no());

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
	public void delete(Integer news_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, news_no);

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
	public NewsInfoVO findByPrimaryKey(Integer news_no) {

		NewsInfoVO newsinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, news_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				newsinfoVO = new NewsInfoVO();
				
				newsinfoVO.setNews_no(rs.getInt("news_no"));
				newsinfoVO.setMovie_no(rs.getString("movie_no"));
				newsinfoVO.setNews_title(rs.getString("news_title"));
				newsinfoVO.setNews_auther(rs.getString("news_auther"));
				newsinfoVO.setNews_times(rs.getDate("news_times"));
				newsinfoVO.setNews_con(rs.getString("news_con"));
				newsinfoVO.setNews_pic(rs.getBytes("news_pic"));
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
		return newsinfoVO;
	}
	
	@Override
	public List<NewsInfoVO> getAll() {
		List<NewsInfoVO> list = new ArrayList<NewsInfoVO>();
		NewsInfoVO newsinfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				newsinfoVO = new NewsInfoVO();
				
				newsinfoVO.setNews_no(rs.getInt("news_no"));
				newsinfoVO.setMovie_no(rs.getString("movie_no"));
				newsinfoVO.setNews_title(rs.getString("news_title"));
				newsinfoVO.setNews_auther(rs.getString("news_auther"));
				newsinfoVO.setNews_times(rs.getDate("news_times"));
				newsinfoVO.setNews_con(rs.getString("news_con"));
				newsinfoVO.setNews_pic(rs.getBytes("news_pic"));
				list.add(newsinfoVO); 
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
