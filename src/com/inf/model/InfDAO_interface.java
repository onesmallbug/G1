package com.inf.model;

import java.util.List;

public interface InfDAO_interface {
	
	public void insert(InfVO infVO);
	public void update(InfVO infVO);
	public void delete(String info_no);
	public InfVO findByPrimaryKey(String info_no);
	public List<InfVO> getAll();

}
