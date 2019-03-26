package com.farediscount.model;

import java.util.List;


public interface FarediscountDAO_interface {

	public void insert(FarediscountVO farediscountVO);
    public void update(FarediscountVO farediscountVO);
    public void delete(String fd_no);
    public FarediscountVO findByPrimaryKey(String fd_no);
    public List<FarediscountVO> getAll();
	
}
