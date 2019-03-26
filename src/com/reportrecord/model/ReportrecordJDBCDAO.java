package com.reportrecord.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import oracle.sql.BLOB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.DriverManager;

public class ReportrecordJDBCDAO implements  Reportrecord_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA106G1";
	String passwd = "123456";
	
	private static final String INSERT_STMT  = 
			"INSERT INTO REPORTRECORD VALUES (?,?,?,?,?,?)";
	private static final String  UPDATE = 
			"UPDATE REPORTRECORD SET report_sta = ? where group_id=?";
	private static final String GET_ONE_RECORD = 
			"SELECT group_ID , member_no , to_char(report_time,'yyyy-mm-dd') , report_sta, employee_no from REPORTRECORD where group_ID = ? "; 
	private static final String GET_ALL_RECORD =
			"SELECT group_ID , member_no , to_char(report_time,'yyyy-mm-dd') , report_sta, employee_no from REPORTRECORD order by timestamp";
	
	
	@Override
	public void insert(ReportrecordVO repcoVO) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1,repcoVO.getGroup_ID());
			pstmt.setString(2,repcoVO.getMember_no());
			pstmt.setTimestamp(3,repcoVO.getReport_time());
			pstmt.setString(4,repcoVO.getReport_Con());
			pstmt.setInt(5,repcoVO. getReport_sta());
			pstmt.setString(6,repcoVO.getEmployee_no());
			
			
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+e.getMessage());
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		
	}finally {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException se) {
				se.printStackTrace(System.err);
				throw new RuntimeException("Could't load database driver."
					+se.getMessage());
			}
		}
		
		if(con != null) {
			try {
				con.close();
			}catch(Exception e) {
				e.printStackTrace(System.err);
				throw new RuntimeException("Could't load database driver."
						+e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setInt(1,repcoVO.getReport_sta());
			pstmt.setString(2,repcoVO.getGroup_ID());
		
			pstmt.executeUpdate();
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			throw new RuntimeException ("Could't load datebase driver "
					+e.getMessage());
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			
			throw new RuntimeException ("A database error occured"
					+se.getMessage());
			
		}finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					throw new RuntimeException ("Could't load datebase driver "
							+se.getMessage());
				}
			}
			
			if (con != null) {
				try {
					con.close();
				}catch (SQLException se) {
					throw new RuntimeException ("Could't load datebase driver "
							+se.getMessage());
				}
			}
			
		}
		
	
	
	
	
	
	}


	@Override
	public ReportrecordVO findByPrimaryKey(String group_ID) {
		// TODO Auto-generated method stub
		
		ReportrecordVO reportrecordVO = null;
		Connection con = null ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_RECORD);
			
			pstmt.setString(1,group_ID);
			
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
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
					throw new RuntimeException("Could't load database driver."
							+se.getMessage());
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
					throw new RuntimeException("Could't load database driver."
							+se.getMessage());
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch(Exception e) {
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Could't load database driver"
					+ e.getMessage());
			
			
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			throw new RuntimeException("A database error occured"
					+ se.getMessage()); 
			
		}finally {
			if (rs != null) {
				try {
					rs.close();
				}catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				}catch(Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		
		return null;
	}
	
	public static void main(String[]args) { 
		ReportrecordJDBCDAO dao = new ReportrecordJDBCDAO();
	
		//新增
		ReportrecordVO repVO1 = new ReportrecordVO();
		repVO1.setGroup_ID("21");
		repVO1.setMember_no("M000011");
		repVO1.setReport_time(java.sql.Timestamp.valueOf("2019-02-28 13:01:27"));
		repVO1.setReport_Con("好多色狼");
		repVO1.setReport_sta(1);
		repVO1.setEmployee_no("1");
		
	    dao.insert(repVO1);
		
	    //修改
	    ReportrecordVO repVO2 = new ReportrecordVO();
	    repVO2.setReport_sta(1);
	    repVO2.setGroup_ID("12");
	    
	    dao.update(repVO2);
	
	    
	    //查詢
	    ReportrecordVO repVO3 = dao.findByPrimaryKey("13");
	    System.out.print(repVO3.getMember_no() + ",");
		System.out.print(repVO3.getReport_time() + ",");
		System.out.print(repVO3.getReport_Con() + ",");
		System.out.print(repVO3.getReport_sta() + ",");
		System.out.print(repVO3.getEmployee_no());
		
		System.out.println("----------------------");
	
		
		List<ReportrecordVO> list = dao.getAll();
		for(ReportrecordVO arep : list) {
			System.out.print(arep.getGroup_ID() + ",");
			System.out.print(arep.getMember_no() + ",");
			System.out.print(arep.getReport_time() + ",");
			System.out.print(arep.getReport_Con() + ",");
			System.out.print(arep.getReport_sta() + ",");
			System.out.print(arep.getEmployee_no());
			System.out.println();
			
		}
		
		
	
	}
}
