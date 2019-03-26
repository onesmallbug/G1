package com.ticketinformation.model;

import java.util.List; 


public class TicketinformationService {
	
	private TicketinformationDAO_interface dao;
	
	public TicketinformationService() {
		dao = new TicketinformationDAO();
	}
	
	public TicketinformationVO addTicketinformation(String ti_name,Integer ti_price) {

		TicketinformationVO TicketinformationVO = new TicketinformationVO();
		
		
		TicketinformationVO.setTi_name(ti_name);
		TicketinformationVO.setTi_price(ti_price);
		
		dao.insert(TicketinformationVO);

		return TicketinformationVO;
	}
	
	public TicketinformationVO updateTicketinformation(String ti_no,String ti_name,Integer ti_price) {

		TicketinformationVO TicketinformationVO = new TicketinformationVO();
		
		TicketinformationVO.setTi_no(ti_no);
		TicketinformationVO.setTi_name(ti_name);
		TicketinformationVO.setTi_price(ti_price);
		
		dao.update(TicketinformationVO);

		return TicketinformationVO;
	}
	
	public void deleteTicketinformation(String ti_no) {
		dao.delete(ti_no);
	}

	public TicketinformationVO getOneTicketinformation(String ti_no) {
		return dao.findByPrimaryKey(ti_no);
	}

	public List<TicketinformationVO> getAll() {
		return dao.getAll();
	}
	
	
	

}
