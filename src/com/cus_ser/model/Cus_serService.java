package com.cus_ser.model;

import java.util.List;

public class Cus_serService {
	
	private Cus_serDAO_interface dao;
	
	public Cus_serService() {
		dao = new Cus_serDAOImpl();	
		
	}
	
	public Cus_serVO addCus_ser(String member_no,java.sql.Date   customer_service_start_date,Integer customer_service_status) {
		
		Cus_serVO cus_serVO = new Cus_serVO();
		
		cus_serVO.setMember_no(member_no);
		cus_serVO.setCustomer_service_start_date(customer_service_start_date);
		cus_serVO.setCustomer_service_status(customer_service_status);
		dao.insert(cus_serVO);
		
		return cus_serVO;
		
	}
	
	public Cus_serVO updatecus_ser(String customer_service_event_no,String member_no,java.sql.Date   customer_service_start_date,Integer customer_service_status) {
		
		Cus_serVO cus_serVO = new Cus_serVO();
		
		cus_serVO.setCustomer_service_event_no(customer_service_event_no);
		cus_serVO.setMember_no(member_no);
		cus_serVO.setCustomer_service_start_date(customer_service_start_date);
		cus_serVO.setCustomer_service_status(customer_service_status);
		dao.update(cus_serVO);
		
		return cus_serVO;
		
	}
	
	public void deletecus_ser(String customer_service_event_no) {
		dao.delete(customer_service_event_no);
	}

	public Cus_serVO getonecus_ser(String customer_service_event_no) {
		return dao.findByPrimaryKey(customer_service_event_no);
	}
	
	public List<Cus_serVO> getAll(){
		return dao.getAll();
	}
}
