package com.groupall.modal;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;




public class GroupAllJDNIDAO implements GroupAllDAO_interface{ 

	
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
			"INSERT INTO GROUPALL VALUES (GROUPALL_seq.NEXTVAL ,?,?,?,?,?,?,?)";
		private static final String GET_ALL_GROUP = 
			"SELECT group_id,member_no,session_no,to_char(group_addtime,'yyyy-mm-dd') ,to_char(group_endtime,'yyyy-mm-dd'),group_name,group_upper,group_lower, group_id from groupall order by group_upper";
		private static final String GET_ONE_GROUP = 
			"SELECT group_id,member_no,session_no,to_char(group_addtime,'yyyy-mm-dd') ,to_char(group_endtime,'yyyy-mm-dd'),group_name,group_upper,group_lower,group_id FROM groupall where group_id = ?";
		private static final String DELETE_MEMBER = 
			"DELETE FROM groupall where group_id = ? and member_no = ?";
		private static final String DELETE_GROUP =
		    "DELETE FROM groupall where group_id = ?";
	
	
	

	@Override
	public void insert(GroupAllVO groupallVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1,groupallVO.getMember_no());
			pstmt.setString(2,groupallVO.getSessions_no());
			pstmt.setTimestamp(3,groupallVO.getGroup_addTime());
			pstmt.setTimestamp(4,groupallVO.getGroup_endTime());
			pstmt.setString(5,groupallVO.getGroupName());
			pstmt.setInt(6,groupallVO.getGroup_upper());
			pstmt.setInt(7,groupallVO.getGroup_lower());

			pstmt.executeUpdate();

		
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
	public void deleteMember(String group_ID,String member_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
	
		
		try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_MEMBER);
				
				
				pstmt.setString(1, group_ID);
				pstmt.setString(2, member_no);
				pstmt.executeUpdate();
				
		
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			
			throw new RuntimeException ("A database error occured"
					+ se.getMessage());
			
		}finally {
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
				}catch(SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		
	}
	

	@Override
	public void deleteGroup(String group_Id) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
	
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE_GROUP);

				pstmt.setString(1, group_Id);
			

				pstmt.executeUpdate();

				
				// Handle any SQL errors
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
	public GroupAllVO findByPrimaryKey(String groupId) {
		// TODO Auto-generated method stub
		GroupAllVO groupallVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_GROUP);
			
			pstmt.setString(1,groupId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				groupallVO = new GroupAllVO();
				groupallVO.setGroup_ID(rs.getString("group_ID"));
				groupallVO.setMember_no(rs.getString("member_no"));
				groupallVO.setSessions_no(rs.getString("sessions_no"));
				groupallVO.setGroup_addTime(rs.getTimestamp("group_addTime"));
				groupallVO.setGroup_endTime(rs.getTimestamp("group_endTime"));
				groupallVO.setGroupName(rs.getString("groupName"));
				groupallVO.setGroup_upper(rs.getInt("group_upper"));
				groupallVO.setGroup_lower(rs.getInt("group_lower"));
				
				
			}
			
			
		
		
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
		
		
		
		
		
		
		return groupallVO;
	}

	@Override
	public List<GroupAllVO> getAll() {
		// TODO Auto-generated method stub
		List<GroupAllVO> list = new ArrayList<GroupAllVO>();
		GroupAllVO groupallVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null ;
		
		
		try {
			con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_GROUP);
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			groupallVO = new GroupAllVO();
			groupallVO.setGroup_ID(rs.getString("group_ID"));
			groupallVO.setMember_no(rs.getString("member_no"));
			groupallVO.setSessions_no(rs.getString("sessions_no"));
			groupallVO.setGroup_addTime(rs.getTimestamp("group_addTime"));
			groupallVO.setGroup_endTime(rs.getTimestamp("group_endTime"));
			groupallVO.setGroupName(rs.getString("groupName"));
			groupallVO.setGroup_upper(rs.getInt("group_upper"));
			groupallVO.setGroup_lower(rs.getInt("group_lower"));
			list.add(groupallVO);
		}
		
		
	
			
			
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

		return list;
		
		
	}
	
	
}
