package com.ticketorder.model;
import java.sql.Timestamp;

public class TicketorderVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 3007436197472905842L;
	
	
	private String order_no;
	private String member_no;
	private String fd_no;
	private String session_no;
	private String employee_no;
	private Integer order_group;
	private Integer order_takemeals;
	private Timestamp order_time;
	private Integer order_amount;
	
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getFd_no() {
		return fd_no;
	}
	public void setFd_no(String fd_no) {
		this.fd_no = fd_no;
	}
	public String getSession_no() {
		return session_no;
	}
	public void setSession_no(String session_no) {
		this.session_no = session_no;
	}
	public String getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}
	public Integer getOrder_group() {
		return order_group;
	}
	public void setOrder_group(Integer order_group) {
		this.order_group = order_group;
	}
	public Integer getOrder_takemeals() {
		return order_takemeals;
	}
	public void setOrder_takemeals(Integer order_takemeals) {
		this.order_takemeals = order_takemeals;
	}
	public Timestamp getOrder_time() {
		return order_time;
	}
	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}
	public Integer getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(Integer order_amount) {
		this.order_amount = order_amount;
	}
	
	
	
	

}
