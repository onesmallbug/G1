package com.empper._det.model;

import java.util.List;

public interface Empper_detDAO_interface {

	
	public void insert(Empper_detVO empper_detVO);
	public void update (Empper_detVO empper_detVOold,Empper_detVO empper_detVOnew);
	public void delete (String employee_no , String permission_no);
	public Empper_detVO findByPrimaryKey(String employee_no , String permission_no);
	public List<Empper_detVO> getAll();
}
