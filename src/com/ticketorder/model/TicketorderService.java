package com.ticketorder.model;


import java.util.List;

import com.dep.model.DepVO;
import com.mem.model.MemVO;
import com.movieticke.model.MovieticketVO;
import com.sessions.model.SessionsVO;



public class TicketorderService {
	
	private TicketorderDAO_interface dao;
	
	public TicketorderService() {
		dao = new TicketorderDAO();
	}
	
	public TicketorderVO addTicketorder(String member_no, String fd_no,
			String session_no, String employee_no, Integer order_group, 
			Integer order_takemeals, java.sql.Timestamp order_time, Integer order_amount) {
		
		TicketorderVO ticketorderVO = new TicketorderVO();
		
		ticketorderVO.setMember_no(member_no);
		ticketorderVO.setFd_no(fd_no);
		ticketorderVO.setSession_no(session_no);
		ticketorderVO.setEmployee_no(employee_no);
		ticketorderVO.setOrder_group(order_group);
		ticketorderVO.setOrder_takemeals(order_takemeals);
		ticketorderVO.setOrder_time(order_time);
		ticketorderVO.setOrder_amount(order_amount);
		dao.insert(ticketorderVO);
		
		
		return ticketorderVO;
	}
	
	public TicketorderVO updateTicketorder(String order_no,String member_no, String fd_no,
			String session_no, String employee_no, Integer order_group, 
			Integer order_takemeals, java.sql.Timestamp order_time, Integer order_amount) {
		
		TicketorderVO ticketorderVO = new TicketorderVO();
		
		ticketorderVO.setOrder_no(order_no);
		ticketorderVO.setMember_no(member_no);
		ticketorderVO.setFd_no(fd_no);
		ticketorderVO.setSession_no(session_no);
		ticketorderVO.setEmployee_no(employee_no);
		ticketorderVO.setOrder_group(order_group);
		ticketorderVO.setOrder_takemeals(order_takemeals);
		ticketorderVO.setOrder_time(order_time);
		ticketorderVO.setOrder_amount(order_amount);
		dao.update(ticketorderVO);
		
		
		
		return ticketorderVO;
		
	}
	
	public void deleteTicketorder(String order_no) {
		dao.delete(order_no);
	}
	
	public TicketorderVO getOneTicketorder(String order_no) {
		return dao.findByPrimaryKey(order_no);
	}
	
	public List<TicketorderVO> getAll() {
		return dao.getAll();
	}
	
	public void insertTicketorderMain(TicketorderVO ticketorderVO, MemVO memVO, DepVO depVO, SessionsVO sessionsVO, List<MovieticketVO> ListOfMovieticketVO) {
		dao.insertTicketorderMain(ticketorderVO, memVO, depVO, sessionsVO, ListOfMovieticketVO);
		ticketorderVO.setOrder_time(new TicketorderDAO().findByPrimaryKey(ticketorderVO.getOrder_no()).getOrder_time());
	}
		

	//20190317更新
	
	public List<TicketorderVO> findByMem_no1(String member_no){
		return dao.findByMem_no1(member_no);
	}
	
}
