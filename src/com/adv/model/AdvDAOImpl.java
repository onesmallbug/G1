package com.adv.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdvDAOImpl implements AdvDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JOIN");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_DEP = "INSERT INTO ADVERTISEMENT VALUES ('A'||LPAD(ADVERTISEMENT_seq.NEXTVAL,6,'0')  ,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE ADVERTISEMENT set AD_NAME = ?, AD_PIC =?, AD_CONT =?, AD_START =?, AD_END =?, AD_TYPE=? WHERE AD_NO = ?";
	private static final String DELETE = "DELETE FROM ADVERTISEMENT WHERE AD_NO = ?";
	private static final String GET_ALL_DEP = "SELECT * FROM ADVERTISEMENT";
	private static final String GET_ONE_DEP = "SELECT * FROM ADVERTISEMENT WHERE AD_NO = ?";

	@Override
	public void insert(AdvVO advVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_DEP);

			//INSERT INTO ADVERTISEMENT VALUES (ADVERTISEMENT_seq.NEXTVAL ,?,?,?,?,?)
			//UPDATE ADVERTISEMENT set AD_NAME = ?, AD_PIC =?, AD_CONT =?, AD_START =?, AD_END =? WHERE AD_NO = ?

			pstmt.setString(1, advVO.getAd_name());
			pstmt.setBytes(2, advVO.getAd_pic());
			pstmt.setString(3, advVO.getAd_cont());
			pstmt.setTimestamp(4, advVO.getAd_start());
			pstmt.setTimestamp(5, advVO.getAd_end());
			pstmt.setInt(6, advVO.getAd_type());

			pstmt.executeUpdate();

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
	public void update(AdvVO advVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, advVO.getAd_name());
			pstmt.setBytes(2, advVO.getAd_pic());
			pstmt.setString(3, advVO.getAd_cont());
			pstmt.setTimestamp(4, advVO.getAd_start());
			pstmt.setTimestamp(5, advVO.getAd_end());
			pstmt.setInt(6, advVO.getAd_type());
			pstmt.setString(7, advVO.getAd_no());

			pstmt.executeUpdate();

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
	public void delete(String ad_no) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, ad_no);
			pstmt.executeUpdate();

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
	public AdvVO findByPrimaryKey(String ad_no) {
		// TODO Auto-generated method stub

		AdvVO advVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_DEP);

			pstmt.setString(1, ad_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				advVO = new AdvVO();
				advVO.setAd_no(ad_no);
				advVO.setAd_name(rs.getString("AD_NAME"));
				advVO.setAd_pic(rs.getBytes("AD_PIC"));
				advVO.setAd_cont(rs.getString("AD_CONT"));
				advVO.setAd_start(rs.getTimestamp("AD_START"));
				advVO.setAd_end(rs.getTimestamp("AD_END"));
				advVO.setAd_type(rs.getInt("AD_TYPE"));
				//UPDATE ADVERTISEMENT set AD_NAME = ?, AD_PIC =?, AD_CONT =?, AD_START =?, AD_END =? WHERE AD_NO = ?

			}

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

		return advVO;
	}

	@Override
	public List<AdvVO> getAll() {
		// TODO Auto-generated method stub

		List<AdvVO> list = new ArrayList<AdvVO>();
		AdvVO advVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DEP);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				advVO = new AdvVO();
				advVO.setAd_no(rs.getString("AD_NO"));
				advVO.setAd_name(rs.getString("AD_NAME"));
				advVO.setAd_pic(rs.getBytes("AD_PIC"));
				advVO.setAd_cont(rs.getString("AD_CONT"));
				advVO.setAd_start(rs.getTimestamp("AD_START"));
				advVO.setAd_end(rs.getTimestamp("AD_END"));
				advVO.setAd_type(rs.getInt("AD_TYPE"));
				list.add(advVO);
			}

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

		AdvDAOImpl dao = new AdvDAOImpl();

		// 新增
		AdvVO advVO1 = new AdvVO();
		advVO1.setAd_name("AD_NAMEinsert");
		advVO1.setAd_pic(null);
		advVO1.setAd_cont("AD_CONTinsert");
		advVO1.setAd_start(new java.sql.Timestamp(System.currentTimeMillis()));
		advVO1.setAd_end(new java.sql.Timestamp(System.currentTimeMillis()+24*60*60*1000));
		advVO1.setAd_type(0);

		dao.insert(advVO1);

		// 修改
		AdvVO advVO2 = new AdvVO();
		advVO2.setAd_no("1");
		advVO2.setAd_name("AD_NAMEupdate");
		advVO2.setAd_pic(null);
		advVO2.setAd_cont("AD_CONTupdate");
		advVO2.setAd_start(new java.sql.Timestamp(System.currentTimeMillis()));
		advVO2.setAd_end(new java.sql.Timestamp(System.currentTimeMillis()+24*60*60*1000));
		advVO2.setAd_type(0);
		dao.update(advVO2);

		// 刪除

		dao.delete("16");

		// 查詢
		AdvVO advVO3 = dao.findByPrimaryKey("1");
		System.out.print(advVO3.getAd_no() + ",");
		System.out.print(advVO3.getAd_name() + ",");
		System.out.print(advVO3.getAd_pic() + ",");
		System.out.print(advVO3.getAd_cont());
		System.out.print(advVO3.getAd_start());
		System.out.print(advVO3.getAd_end());
		System.out.print(advVO3.getAd_type());
		System.out.println("-----------------------");

		// 查詢
		List<AdvVO> list = dao.getAll();
		for (AdvVO aDep : list) {

			System.out.print(aDep.getAd_no() + ",");
			System.out.print(aDep.getAd_name() + ",");
			System.out.print(aDep.getAd_pic() + ",");
			System.out.print(aDep.getAd_cont());
			System.out.print(aDep.getAd_start());
			System.out.print(aDep.getAd_end());
			System.out.print(aDep.getAd_type());
			System.out.println();
		}

	}
}