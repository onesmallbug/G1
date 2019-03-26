package com.cus_ser.model;

import java.sql.Date;

public class Cus_serVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String customer_service_event_no;
	private String member_no;
	private Date   customer_service_start_date;
	private Integer customer_service_status;
	
	
	public String getCustomer_service_event_no() {
		return customer_service_event_no;
	}
	public void setCustomer_service_event_no(String customer_service_event_no) {
		this.customer_service_event_no = customer_service_event_no;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public Date getCustomer_service_start_date() {
		return customer_service_start_date;
	}
	public void setCustomer_service_start_date(Date customer_service_start_date) {
		this.customer_service_start_date = customer_service_start_date;
	}
	public Integer getCustomer_service_status() {
		return customer_service_status;
	}
	public void setCustomer_service_status(Integer customer_service_status) {
		this.customer_service_status = customer_service_status;
	}
	
	
	
	
}
