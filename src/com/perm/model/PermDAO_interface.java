package com.perm.model;

import java.util.List;

public interface PermDAO_interface {
	
	public void insert (PermVO permVO);
	public void update (PermVO permVO);
	public void delete (String permission_no);
	public PermVO findByPrimaryKey(String permission_no);
	public List<PermVO> getAll();

}
