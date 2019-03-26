package com.mem.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.dep.model.DepVO;
import com.sessions.model.SessionsVO;
import com.ticketorder.model.TicketorderVO;

public interface MemDAO_interface {
	
	public void insert (MemVO memVO);
	public void update(MemVO memVO);
	public void delete (String member_no);
	public  MemVO findByPrimaryKey(String member_no);
	public  MemVO findByMember_account(String member_account);
	public List<MemVO> getAll();
	public void update_member_status(String member_no);

	public void updateMember_point(TicketorderVO ticketorderVO, MemVO memVO, DepVO depVO, SessionsVO sessionsVO, Connection con) throws SQLException;
	//訂票交易區間用，更新點數
	
	public void updateDeposit_point(DepVO depVO, MemVO memVO, Connection con) throws SQLException;
	//訂票交易區間用，從儲值更新會員點數
	
	
	//20190318更新
	public void updateMember_password(String member_account ,String member_password);
}
