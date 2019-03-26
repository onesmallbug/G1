package com.groupall.modal;

import java.util.*;





public class GroupAllSerice {
	
	private GroupAllDAO_interface dao;
	
	public GroupAllSerice() {
		dao = new GroupAllJDBCDAO();
	}

	public GroupAllVO addGroup(String group_ID , String member_no , String sessions_no , java.sql.Timestamp group_addTime , java.sql.Timestamp group_endTime ,  String groupName ,Integer group_upper , Integer group_lower) {
		
		GroupAllVO groupallVO = new GroupAllVO();
		
		groupallVO.setGroup_ID(group_ID);
		groupallVO.setMember_no(member_no);
		groupallVO.setSessions_no(sessions_no);
		groupallVO.setGroup_addTime(group_addTime);
		groupallVO.setGroup_endTime(group_endTime);
		groupallVO.setGroupName(groupName);
		groupallVO.setGroup_upper(group_upper);
		groupallVO.setGroup_lower(group_lower);
		dao.insert(groupallVO);
		
		return groupallVO;
		
	} 
	public void deleteMember(String group_ID,String member_no) {
		dao.deleteMember(group_ID,member_no);
	}
	public void deleteGroup(String group_ID) {
		dao.deleteGroup(group_ID);
		
	}
	public GroupAllVO getoneMem(String group_ID) {
			
		return dao.findByPrimaryKey(group_ID);
			
	}
	
	public List<GroupAllVO> getAll(){
		
		return dao.getAll();
		
	}
	
}
