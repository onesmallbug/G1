package com.reportrecord.model;

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

public class ReportrecordJNDIDAO implements Reportrecord_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/JOIN");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO REPORTRECORD VALUES (?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE REPORTRECORD SET report_sta = ? where group_id=?";
	private static final String GET_ONE_RECORD = "SELECT group_ID , member_no , to_char(report_time,'yyyy-mm-dd') , report_sta, employee_no from REPORTRECORD where group_ID = ? ";
	private static final String GET_ALL_RECORD = "SELECT group_ID , member_no , to_char(report_time,'yyyy-mm-dd') , report_sta, employee_no from REPORTRECORD order by timestamp";

	@Override
	public void insert(ReportrecordVO repcoVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, repcoVO.getGroup_ID());
			pstmt.setString(2, repcoVO.getMember_no());
			pstmt.setTimestamp(3, repcoVO.getReport_time());
			pstmt.setString(4, repcoVO.getReport_Con());
			pstmt.setInt(5, repcoVO.getReport_sta());
			pstmt.setString(6, repcoVO.getEmployee_no());

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
	public void update(ReportrecordVO repcoVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, repcoVO.getReport_sta());
			pstmt.setString(2, repcoVO.getGroup_ID());

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
	public ReportrecordVO findByPrimaryKey(String group_ID) {
		// TODO Auto-generated method stub

		ReportrecordVO reportrecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_RECORD);

			pstmt.setString(1, group_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportrecordVO = new ReportrecordVO();
				reportrecordVO.setGroup_ID(rs.getString("group_ID"));
				reportrecordVO.setMember_no(rs.getString("member_no"));
				reportrecordVO.setReport_time(rs.getTimestamp("report_time"));
				reportrecordVO.setReport_Con(rs.getString("report_Con"));
				reportrecordVO.setReport_sta(rs.getInt("report_sta"));
				reportrecordVO.setEmployee_no(rs.getString("employee_no"));

			}

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

	
		return reportrecordVO;
	}

	@Override
	public List<ReportrecordVO> getAll() {
		// TODO Auto-generated method stub
		List<ReportrecordVO> list = new ArrayList<ReportrecordVO>();

		ReportrecordVO reportrecordVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_RECORD);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				reportrecordVO = new ReportrecordVO();
				reportrecordVO.setGroup_ID(rs.getString("group_ID"));
				reportrecordVO.setMember_no(rs.getString("member_no"));
				reportrecordVO.setReport_time(rs.getTimestamp("report_time"));
				reportrecordVO.setReport_Con(rs.getString("report_Con"));
				reportrecordVO.setReport_sta(rs.getInt("report_sta"));
				reportrecordVO.setEmployee_no(rs.getString("employee_no"));
				list.add(reportrecordVO);
			}

		
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured" + se.getMessage());

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
