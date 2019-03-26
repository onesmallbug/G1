package com.groupall.modal;
import java.util.*;

public interface GroupAllDAO_interface {

   public void insert(GroupAllVO groupallVO);
   public void deleteMember(String group_ID,String member_no);
   public void deleteGroup(String group_Id);
   public GroupAllVO findByPrimaryKey(String group_Id);
   public List<GroupAllVO> getAll();
	
}

	

