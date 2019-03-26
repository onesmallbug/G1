package com.adv.model;

import java.util.List;

public interface AdvDAO_interface {

	public void insert(AdvVO advVO);
	public void update(AdvVO advVO);
	public void delete(String ad_no);
	public AdvVO findByPrimaryKey(String ad_no);
	public List <AdvVO> getAll();
}
