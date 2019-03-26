package com.ticketinformation.model;

import java.util.*;

public interface TicketinformationDAO_interface {
	public void insert(TicketinformationVO ticketinformationVO);
	public void update(TicketinformationVO ticketinformationVO);
	public void delete(String ti_no);
	public TicketinformationVO findByPrimaryKey(String ti_no);
	public List<TicketinformationVO> getAll();

}
