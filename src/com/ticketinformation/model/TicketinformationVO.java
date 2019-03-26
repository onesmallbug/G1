package com.ticketinformation.model;

public class TicketinformationVO implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4340645744685221723L;
	
	private String ti_no;
	private String ti_name;
	private Integer ti_price;
	
	public String getTi_no() {
		return ti_no;
	}
	public void setTi_no(String ti_no) {
		this.ti_no = ti_no;
	}
	public String getTi_name() {
		return ti_name;
	}
	public void setTi_name(String ti_name) {
		this.ti_name = ti_name;
	}
	public Integer getTi_price() {
		return ti_price;
	}
	public void setTi_price(Integer ti_price) {
		this.ti_price = ti_price;
	}
	
	
	

}
