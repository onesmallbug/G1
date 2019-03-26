package com.dep.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mem.model.MemVO;
import com.sessions.model.SessionsVO;

public interface DepDAO_interface {
	
	public String insert(DepVO depVO);
	public void update(DepVO depVO);
	public void delete(String deposit_change_no);
	public DepVO findByPrimaryKey(String deposit_change_no);
	public DepVO findByMem_no(String deposit_member_no);
	public List<DepVO> getAll();
	public List<DepVO> findByMem_no1(String deposit_member_no);

	public void insertByTicketorder(DepVO depVO, SessionsVO sessionsVO, Connection con) throws SQLException;
	//訂票交易區間用，新增儲值異動
	
	public void insertDepositMain (DepVO depVO , MemVO memVO);
	
}
