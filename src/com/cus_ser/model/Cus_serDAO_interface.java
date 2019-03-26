package com.cus_ser.model;

import java.util.List;

public interface Cus_serDAO_interface {
	
	public void insert(Cus_serVO cus_serVO);
	public void update(Cus_serVO cus_serVO);
	public void delete(String customer_service_event_no);
	public Cus_serVO findByPrimaryKey(String customer_service_event_no);
	public List<Cus_serVO> getAll();
	

}
