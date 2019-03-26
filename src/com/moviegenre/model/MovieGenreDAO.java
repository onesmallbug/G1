package com.moviegenre.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MovieGenreDAO implements MovieGenreDAO_interface {

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
			"INSERT INTO moviegenre(genre_no, genre_name) VALUES (moviegenre_seq.NEXTVAL,? )";
		private static final String GET_ALL_STMT = 
			"SELECT genre_no, genre_name FROM moviegenre order by genre_no";
		private static final String GET_ONE_STMT = 
			"SELECT genre_no, genre_name FROM moviegenre where genre_no = ?";
		private static final String DELETE = 
			"DELETE FROM moviegenre where genre_no = ? ";
		private static final String UPDATE = 
			"UPDATE moviegenre set genre_name= ? where genre_no = ? ";

	@Override
	public void insert(MovieGenreVO moviegenreVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, moviegenreVO.getGenre_name());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A Database error occured.");
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
	public void update(MovieGenreVO moviegenreVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, moviegenreVO.getGenre_name());
			pstmt.setInt(2, moviegenreVO.getGenre_no());

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
	public void delete(Integer genre_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, genre_no);

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
	public MovieGenreVO findByPrimaryKey(Integer genre_no) {

		MovieGenreVO moviegenreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, genre_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				moviegenreVO = new MovieGenreVO();
				moviegenreVO.setGenre_no(rs.getInt("genre_no"));
				moviegenreVO.setGenre_name(rs.getString("genre_name"));
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
		return moviegenreVO;
	}
	
	@Override
	public List<MovieGenreVO> getAll() {
		List<MovieGenreVO> list = new ArrayList<MovieGenreVO>();
		MovieGenreVO moviegenreVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				moviegenreVO = new MovieGenreVO();
				moviegenreVO.setGenre_no(rs.getInt("genre_no"));
				moviegenreVO.setGenre_name(rs.getString("genre_name"));
				
				list.add(moviegenreVO); 
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
