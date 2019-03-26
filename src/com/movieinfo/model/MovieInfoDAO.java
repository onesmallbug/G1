package com.movieinfo.model;

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

public class MovieInfoDAO implements MovieInfoDAO_interface {

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
			"INSERT INTO movieinfo (movie_no, genre_no, movie_name, movie_score, movie_level, movie_director, movie_cast, movie_intro, movie_length, movie_trailer, movie_pic, movie_in, movie_out, movie_count, movie_exp, movie_noexp, movie_touch, movie_ticket) VALUES ('MI'||LPAD(to_char(movieinfo_seq.NEXTVAL),8,'0'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT * FROM movieinfo order by movie_in desc";
	private static final String GET_ALL_STMT_BY_NO = 
			"SELECT * FROM movieinfo order by movie_no";
	private static final String GET_ALL_STMT_SCORE = 
			"SELECT * FROM movieinfo where rownum < 11 and movie_in BETWEEN ? AND ? order by movie_score desc ";
	private static final String GET_ONE_STMT = 
			"SELECT * FROM movieinfo where movie_no = ?";
	private static final String DELETE = 
			"DELETE FROM movieinfo where movie_no = ?";
	private static final String UPDATE = 
			"UPDATE movieinfo set genre_no=?, movie_name=?, movie_score=?, movie_level=?, movie_director=?, movie_cast=?, movie_intro=?, movie_length=?, movie_trailer=?, movie_pic=?, movie_in=?, movie_out=?, movie_count=?, movie_exp=?, movie_noexp=?, movie_touch=?, movie_ticket=? where movie_no = ?";
	private static final String UPDATE_EXP =
			"UPDATE movieinfo set movie_exp=? where movie_no = ?";
	private static final String UPDATE_NOEXP =
			"UPDATE movieinfo set movie_noexp=? where movie_no = ?";
	
	@Override
	public void insert(MovieInfoVO movieinfoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, movieinfoVO.getGenre_no());
			pstmt.setString(2, movieinfoVO.getMovie_name());
			pstmt.setDouble(3, movieinfoVO.getMovie_score());
			pstmt.setBytes(4, movieinfoVO.getMovie_level());
			pstmt.setString(5, movieinfoVO.getMovie_director());
			pstmt.setString(6, movieinfoVO.getMovie_cast());
			pstmt.setString(7, movieinfoVO.getMovie_intro());
			pstmt.setString(8, movieinfoVO.getMovie_length());
			pstmt.setString(9, movieinfoVO.getMovie_trailer());
			pstmt.setBytes(10, movieinfoVO.getMovie_pic());
			pstmt.setDate(11, movieinfoVO.getMovie_in());
			pstmt.setDate(12, movieinfoVO.getMovie_out());
			pstmt.setInt(13, movieinfoVO.getMovie_count());
			pstmt.setInt(14, movieinfoVO.getMovie_exp());
			pstmt.setInt(15, movieinfoVO.getMovie_noexp());
			pstmt.setInt(16, movieinfoVO.getMovie_touch());
			pstmt.setInt(17, movieinfoVO.getMovie_ticket());
			

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			se.printStackTrace();
			
//			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(MovieInfoVO movieinfoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, movieinfoVO.getGenre_no());
			pstmt.setString(2, movieinfoVO.getMovie_name());
			pstmt.setDouble(3, movieinfoVO.getMovie_score());
			pstmt.setBytes(4, movieinfoVO.getMovie_level());
			pstmt.setString(5, movieinfoVO.getMovie_director());
			pstmt.setString(6, movieinfoVO.getMovie_cast());
			pstmt.setString(7, movieinfoVO.getMovie_intro());
			pstmt.setString(8, movieinfoVO.getMovie_length());
			pstmt.setString(9, movieinfoVO.getMovie_trailer());
			pstmt.setBytes(10, movieinfoVO.getMovie_pic());
			pstmt.setDate(11, movieinfoVO.getMovie_in());
			pstmt.setDate(12, movieinfoVO.getMovie_out());
			pstmt.setInt(13, movieinfoVO.getMovie_count());
			pstmt.setInt(14, movieinfoVO.getMovie_exp());
			pstmt.setInt(15, movieinfoVO.getMovie_noexp());
			pstmt.setInt(16, movieinfoVO.getMovie_touch());
			pstmt.setInt(17, movieinfoVO.getMovie_ticket());
			pstmt.setString(18, movieinfoVO.getMovie_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
	public void delete(String movie_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, movie_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
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
	public MovieInfoVO findByPrimaryKey(String movie_no) {

		MovieInfoVO movieinfoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, movie_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				movieinfoVO = new MovieInfoVO();

				movieinfoVO.setMovie_no(rs.getString("movie_no"));
				movieinfoVO.setGenre_no(rs.getInt("genre_no"));
				movieinfoVO.setMovie_name(rs.getString("movie_name"));
				movieinfoVO.setMovie_score(rs.getDouble("movie_score"));
				movieinfoVO.setMovie_level(rs.getBytes("movie_level"));
				movieinfoVO.setMovie_director(rs.getString("movie_director"));
				movieinfoVO.setMovie_cast(rs.getString("movie_cast"));
				movieinfoVO.setMovie_intro(rs.getString("movie_intro"));
				movieinfoVO.setMovie_length(rs.getString("movie_length"));
				movieinfoVO.setMovie_trailer(rs.getString("movie_trailer"));
				movieinfoVO.setMovie_pic(rs.getBytes("movie_pic"));
				movieinfoVO.setMovie_in(rs.getDate("movie_in"));
				movieinfoVO.setMovie_out(rs.getDate("movie_out"));
				movieinfoVO.setMovie_count(rs.getInt("movie_count"));
				movieinfoVO.setMovie_exp(rs.getInt("movie_exp"));
				movieinfoVO.setMovie_noexp(rs.getInt("movie_noexp"));
				movieinfoVO.setMovie_touch(rs.getInt("movie_touch"));
				movieinfoVO.setMovie_ticket(rs.getInt("movie_ticket"));
				
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
		return movieinfoVO;
	}
	
	

	@Override
	public List<MovieInfoVO> getAll() {
		List<MovieInfoVO> list = new ArrayList<MovieInfoVO>();
		MovieInfoVO movieinfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				movieinfoVO = new MovieInfoVO();

				movieinfoVO.setMovie_no(rs.getString("movie_no"));
				movieinfoVO.setGenre_no(rs.getInt("genre_no"));
				movieinfoVO.setMovie_name(rs.getString("movie_name"));
				movieinfoVO.setMovie_score(rs.getDouble("movie_score"));
				movieinfoVO.setMovie_level(rs.getBytes("movie_level"));
				movieinfoVO.setMovie_director(rs.getString("movie_director"));
				movieinfoVO.setMovie_cast(rs.getString("movie_cast"));
				movieinfoVO.setMovie_intro(rs.getString("movie_intro"));
				movieinfoVO.setMovie_length(rs.getString("movie_length"));
				movieinfoVO.setMovie_trailer(rs.getString("movie_trailer"));
				movieinfoVO.setMovie_pic(rs.getBytes("movie_pic"));
				movieinfoVO.setMovie_in(rs.getDate("movie_in"));
				movieinfoVO.setMovie_out(rs.getDate("movie_out"));
				movieinfoVO.setMovie_count(rs.getInt("movie_count"));
				movieinfoVO.setMovie_exp(rs.getInt("movie_exp"));
				movieinfoVO.setMovie_noexp(rs.getInt("movie_noexp"));
				movieinfoVO.setMovie_touch(rs.getInt("movie_touch"));
				movieinfoVO.setMovie_ticket(rs.getInt("movie_ticket"));

				list.add(movieinfoVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	@Override
	public List<MovieInfoVO> getAllByScore(java.sql.Date stdate, java.sql.Date enddate) {
		List<MovieInfoVO> list = new ArrayList<MovieInfoVO>();
		MovieInfoVO movieinfoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT_SCORE);
			
			pstmt.setDate(1, stdate);
			pstmt.setDate(2, enddate);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				movieinfoVO = new MovieInfoVO();

				movieinfoVO.setMovie_no(rs.getString("movie_no"));
				movieinfoVO.setGenre_no(rs.getInt("genre_no"));
				movieinfoVO.setMovie_name(rs.getString("movie_name"));
				movieinfoVO.setMovie_score(rs.getDouble("movie_score"));
				movieinfoVO.setMovie_level(rs.getBytes("movie_level"));
				movieinfoVO.setMovie_director(rs.getString("movie_director"));
				movieinfoVO.setMovie_cast(rs.getString("movie_cast"));
				movieinfoVO.setMovie_intro(rs.getString("movie_intro"));
				movieinfoVO.setMovie_length(rs.getString("movie_length"));
				movieinfoVO.setMovie_trailer(rs.getString("movie_trailer"));
				movieinfoVO.setMovie_pic(rs.getBytes("movie_pic"));
				movieinfoVO.setMovie_in(rs.getDate("movie_in"));
				movieinfoVO.setMovie_out(rs.getDate("movie_out"));
				movieinfoVO.setMovie_count(rs.getInt("movie_count"));
				movieinfoVO.setMovie_exp(rs.getInt("movie_exp"));
				movieinfoVO.setMovie_noexp(rs.getInt("movie_noexp"));
				movieinfoVO.setMovie_touch(rs.getInt("movie_touch"));
				movieinfoVO.setMovie_ticket(rs.getInt("movie_ticket"));

				list.add(movieinfoVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
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

	@Override
	public void changeExp(String movie_no, Integer movie_exp) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_EXP);

			pstmt.setInt(1, movie_exp);
			pstmt.setString(2, movie_no);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
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
		public void changeNoExp(String movie_no, Integer movie_noexp) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE_NOEXP);

				pstmt.setInt(1, movie_noexp);
				pstmt.setString(2, movie_no);

				pstmt.executeUpdate();

			} catch (SQLException se) {
				se.printStackTrace();
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
	
}
