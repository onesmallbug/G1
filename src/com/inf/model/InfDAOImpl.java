package com.inf.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfDAOImpl implements InfDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String username = "JOIN";
	String password = "123456";

	private static final String INSERT_DEP = "INSERT INTO INFORMATION VALUES (INFORMATION_seq.NEXTVAL ,?,?,?)";
	private static final String UPDATE = "UPDATE INFORMATION set INFO_CONTENT = ?, INFO_DESC =?, INFO_DATE =? WHERE INFO_NO = ?";
	private static final String DELETE = "DELETE FROM INFORMATION WHERE INFO_NO = ?";
	private static final String GET_ALL_DEP = "SELECT * FROM INFORMATION";
	private static final String GET_ONE_DEP = "SELECT * FROM INFORMATION WHERE INFO_NO = ?";

	@Override
	public void insert(InfVO infVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(INSERT_DEP);

			//UPDATE ADVERTISEMENT set INFO_CONTENT = ?, INFO_DESC =?, INFO_DATE =? WHERE INFO_NO = ?

			pstmt.setString(1, infVO.getInfo_content());
			pstmt.setString(2, infVO.getInfo_desc());
			pstmt.setDate(3, infVO.getInfo_date());

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public void update(InfVO infVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, infVO.getInfo_content());
			pstmt.setString(2, infVO.getInfo_desc());
			pstmt.setDate(3, infVO.getInfo_date());
			pstmt.setString(4, infVO.getInfo_no());
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

		if (con != null) {
			try {
				con.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}

	}

	@Override
	public void delete(String info_no) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, info_no);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}

	}

	@Override
	public InfVO findByPrimaryKey(String info_no) {
		// TODO Auto-generated method stub

		InfVO infVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(GET_ONE_DEP);

			pstmt.setString(1, info_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				infVO = new InfVO();
				infVO.setInfo_no(info_no);
				infVO.setInfo_content(rs.getString("INFO_CONTENT"));
				infVO.setInfo_desc(rs.getString("INFO_DESC"));
				infVO.setInfo_date(rs.getDate("INFO_DATE"));
				//UPDATE ADVERTISEMENT set INFO_CONTENT = ?, INFO_DESC =?, INFO_DATE =? WHERE INFO_NO = ?

			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

		return infVO;
	}

	@Override
	public List<InfVO> getAll() {
		// TODO Auto-generated method stub

		List<InfVO> list = new ArrayList<InfVO>();
		InfVO infVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(GET_ALL_DEP);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				infVO = new InfVO();
				infVO.setInfo_no(rs.getString("INFO_NO"));
				infVO.setInfo_content(rs.getString("INFO_CONTENT"));
				infVO.setInfo_desc(rs.getString("INFO_DESC"));
				infVO.setInfo_date(rs.getDate("INFO_DATE"));
				list.add(infVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}

		}

		return list;
	}

	public static void main(String[] args) {

		InfDAOImpl dao = new InfDAOImpl();

		// �s�W
		InfVO infVO1 = new InfVO();
		infVO1.setInfo_content("INFO_CONTENTinsert");
		infVO1.setInfo_desc("INFO_DESC");
		infVO1.setInfo_date(new java.sql.Date(System.currentTimeMillis()));
		dao.insert(infVO1);

		// �ק�
		InfVO infVO2 = new InfVO();
		infVO2.setInfo_no(("1"));
		infVO2.setInfo_content("INFO_CONTENT");
		infVO2.setInfo_desc("INFO_DESC");
		infVO2.setInfo_date(new java.sql.Date(System.currentTimeMillis()));
		dao.update(infVO2);

		// �R��

		dao.delete("16");

		// �d��
		InfVO infVO3 = dao.findByPrimaryKey("1");
		System.out.print(infVO3.getInfo_no() + ",");
		System.out.print(infVO3.getInfo_content() + ",");
		System.out.print(infVO3.getInfo_desc() + ",");
		System.out.print(infVO3.getInfo_date());
		System.out.println("-----------------------");

		// �d��
		List<InfVO> list = dao.getAll();
		for (InfVO infVO4 : list) {

			System.out.print(infVO4.getInfo_no() + ",");
			System.out.print(infVO4.getInfo_content() + ",");
			System.out.print(infVO4.getInfo_desc() + ",");
			System.out.print(infVO4.getInfo_date());
			System.out.println();
		}

	}
}