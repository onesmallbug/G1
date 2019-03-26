package com.mem.model;

import java.util.List;

public interface UserDAO_interface {
	
	public void insert (UserVO userVO);
	public void update(UserVO userVO);
	public void delete (String member_no);
	public  UserVO findByPrimaryKey(String member_no);
	public List<UserVO> getAll();
	

}
