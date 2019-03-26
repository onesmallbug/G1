package com.movieticke.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import com.dep.model.DepVO;
import com.mem.model.MemVO;
import com.sessions.model.SessionsVO;
import com.ticketorder.model.TicketorderVO;

public interface MovieticketDAO_interface {
	public void insert(MovieticketVO movieticketVO);
	public void update(MovieticketVO movieticketVO);
	public void delete(String mt_no);
	public MovieticketVO findByPrimaryKey(String mt_no);
	public List<MovieticketVO> getAll();
	
	// 20190317更新
	public List<MovieticketVO> findByOrder_no(String order_no);
	
	public void insertByTicketorder(TicketorderVO ticketorderVO, MemVO memVO, DepVO depVO, SessionsVO sessionsVO, List<MovieticketVO> ListOfMovieticketVO, Connection con)throws SQLException;
}
