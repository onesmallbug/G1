package com.ticketorder.model;

import java.util.*;

import com.dep.model.DepVO;
import com.mem.model.MemVO;
import com.movieticke.model.MovieticketVO;
import com.sessions.model.SessionsVO;



public interface TicketorderDAO_interface {
	public void insert(TicketorderVO ticketorderVO);
    public void update(TicketorderVO ticketorderVO);
    public void delete(String order_no);
    public TicketorderVO findByPrimaryKey(String order_no);
    public List<TicketorderVO> getAll();
    
    //20190317 新增
    public List<TicketorderVO> findByMem_no1(String member_no);
    
    public void insertTicketorderMain(TicketorderVO ticketorderVO, MemVO memVO, DepVO depVO, SessionsVO sessionsVO, List<MovieticketVO> ListOfMovieticketVO);
	/* MemVO          member_no         會員編號
	 * TicketorderVO  order_amount      訂單金額
	 * SessionsVO     sessions_no       場次編號
	 * SessionsVO     sessions_status   座位狀態
	 * TicketorderVO  fd_no             優惠編號
	 * MovieticketVO  mt_no             座位編號
	 * MovieticketVO  ti_no             票種編號 
	 * DepVO          deposit_change_money 
	 * */

}
