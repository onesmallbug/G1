package com.moviegenre.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MovieGenreJDBCDAO implements MovieGenreDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BOSS";
	String passwd = "123456";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, moviegenreVO.getGenre_name());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

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
	public void update(MovieGenreVO moviegenreVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, moviegenreVO.getGenre_name());
			pstmt.setInt(2, moviegenreVO.getGenre_no());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	public void delete(Integer genre_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, genre_no);

			pstmt.executeUpdate();


			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	public MovieGenreVO findByPrimaryKey(Integer genre_no) {

		MovieGenreVO moviegenreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, genre_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				moviegenreVO = new MovieGenreVO();
				moviegenreVO.setGenre_no(rs.getInt("genre_no"));
				moviegenreVO.setGenre_name(rs.getString("genre_name"));
			}

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				moviegenreVO = new MovieGenreVO();
				moviegenreVO.setGenre_no(rs.getInt("genre_no"));
				moviegenreVO.setGenre_name(rs.getString("genre_name"));
				
				list.add(moviegenreVO); 
			}

			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			
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
	
	public static void main(String[] args) {
		MovieGenreJDBCDAO dao = new MovieGenreJDBCDAO();
		
		
//		//測試
		MovieGenreVO moviegenreVO1 = new MovieGenreVO();
		
//		//新增
		moviegenreVO1.setGenre_name("這是測試2");
		dao.insert(moviegenreVO1);
		
		
//		//修改
//		//欲修改的欄位
//		moviegenreVO1.setGenre_no(20);		
//		moviegenreVO1.setGenre_name("修改後名字1");
//		dao.update(moviegenreVO1);
//		
//  	//刪除
//		dao.delete(5);
		
		
//		//單一查詢
//		MovieGenreVO moviegenreVO3 = dao.findByPrimaryKey(2);
//		System.out.print("電影種類編號:"+moviegenreVO3.getGenre_no() + ",");
//		System.out.println("電影種類名稱"+moviegenreVO3.getGenre_name());
//	
//		System.out.println("---------------------");
		
		
//		//全部查詢
//		List<MovieGenreVO> list = dao.getAll();
//		for (MovieGenreVO aMovieGenre : list) {
//			System.out.print("電影種類編號:"+aMovieGenre.getGenre_no() + ",");
//			System.out.println("電影種類名稱"+aMovieGenre.getGenre_name() + ",");
//			System.out.println();
//		}
		
	} 

}
