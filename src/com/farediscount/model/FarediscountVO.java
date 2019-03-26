package com.farediscount.model;

import java.io.Serializable;
import java.sql.Date;

public class FarediscountVO implements Serializable, Comparable<FarediscountVO>{
	private static final long serialVersionUID = -4550863884645708798L;
	
	private String fd_no;
	private String fd_narrative;
	private String fd_name;
	private Integer fd_offer;
	private Date fd_start;
	private Date fd_end;
	private Integer fd_upper;
	private Integer fd_lower;
	private byte[] fd_blob;
	
	
	public String getFd_no() {
		return fd_no;
	}
	public void setFd_no(String fd_no) {
		this.fd_no = fd_no;
	}
	public String getFd_narrative() {
		return fd_narrative;
	}
	public void setFd_narrative(String fd_narrative) {
		this.fd_narrative = fd_narrative;
	}
	public String getFd_name() {
		return fd_name;
	}
	public void setFd_name(String fd_name) {
		this.fd_name = fd_name;
	}
	public Integer getFd_offer() {
		return fd_offer;
	}
	public void setFd_offer(Integer fd_offer) {
		this.fd_offer = fd_offer;
	}
	public Date getFd_start() {
		return fd_start;
	}
	public void setFd_start(Date fd_start) {
		this.fd_start = fd_start;
	}
	public Date getFd_end() {
		return fd_end;
	}
	public void setFd_end(Date fd_end) {
		this.fd_end = fd_end;
	}
	public Integer getFd_upper() {
		return fd_upper;
	}
	public void setFd_upper(Integer fd_upper) {
		this.fd_upper = fd_upper;
	}
	public Integer getFd_lower() {
		return fd_lower;
	}
	public void setFd_lower(Integer fd_lower) {
		this.fd_lower = fd_lower;
	}
	public byte[] getFd_blob() {
		return fd_blob;
	}
	public void setFd_blob(byte[] fd_blob) {
		this.fd_blob = fd_blob;
	}
	@Override
	public int compareTo(FarediscountVO farediscountVO) {		
		
			return this.fd_offer - farediscountVO.getFd_offer();		
		
	}
	
	public int compareTo_bystart(FarediscountVO farediscountVO) {		
		
		return this.fd_start.compareTo(farediscountVO.getFd_start());		
	
	}
		
}
