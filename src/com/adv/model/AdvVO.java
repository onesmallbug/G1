package com.adv.model;

import java.sql.Timestamp;

public class AdvVO implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -484289920823447586L;
	private String ad_no;			
	private String ad_name;			
	private byte[] ad_pic;
	private String ad_cont;	
	private Timestamp  ad_start;				
	private Timestamp  ad_end;
	private Integer ad_type;
	
	
	
	public String getAd_no() {
		return ad_no;
	}
	public void setAd_no(String ad_no) {
		this.ad_no = ad_no;
	}
	public String getAd_name() {
		return ad_name;
	}
	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}
	public byte[] getAd_pic() {
		return ad_pic;
	}
	public void setAd_pic(byte[] ad_pic) {
		this.ad_pic = ad_pic;
	}
	public String getAd_cont() {
		return ad_cont;
	}
	public void setAd_cont(String ad_cont) {
		this.ad_cont = ad_cont;
	}
	public Timestamp getAd_start() {
		return ad_start;
	}
	public void setAd_start(Timestamp ad_start) {
		this.ad_start = ad_start;
	}
	public Timestamp getAd_end() {
		return ad_end;
	}
	public void setAd_end(Timestamp ad_end) {
		this.ad_end = ad_end;
	}
	public Integer getAd_type() {
		return ad_type;
	}
	public void setAd_type(Integer ad_type) {
		this.ad_type = ad_type;
	}

	

}
