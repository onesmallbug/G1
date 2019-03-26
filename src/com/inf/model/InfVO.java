package com.inf.model;

import java.sql.Date;

public class InfVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2119514172596368708L;

	
	
	private String info_no;			
	private String info_content;
	private String info_desc;		
	private Date info_date;
	
	
	public String getInfo_no() {
		return info_no;
	}
	public void setInfo_no(String info_no) {
		this.info_no = info_no;
	}
	public String getInfo_content() {
		return info_content;
	}
	public void setInfo_content(String info_content) {
		this.info_content = info_content;
	}
	public String getInfo_desc() {
		return info_desc;
	}
	public void setInfo_desc(String info_desc) {
		this.info_desc = info_desc;
	}
	public Date getInfo_date() {
		return info_date;
	}
	public void setInfo_date(Date info_date) {
		this.info_date = info_date;
	}

	
	
	
	
}
