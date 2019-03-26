package com.movieticke.model;
import java.sql.Date;

public class MovieticketVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7980585282870133173L;
	
	private String mt_no;
	private String order_no;
	private String ti_no;
	private byte[] mt_qr;
	private Integer mt_admission;
	private String mt_share;
	
	public String getMt_no() {
		return mt_no;
	}
	public void setMt_no(String mt_no) {
		this.mt_no = mt_no;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getTi_no() {
		return ti_no;
	}
	public void setTi_no(String ti_no) {
		this.ti_no = ti_no;
	}
	public byte[] getMt_qr() {
		return mt_qr;
	}
	public void setMt_qr(byte[] mt_qr) {
		this.mt_qr = mt_qr;
	}
	public Integer getMt_admission() {
		return mt_admission;
	}
	public void setMt_admission(Integer mt_admission) {
		this.mt_admission = mt_admission;
	}
	public String getMt_share() {
		return mt_share;
	}
	public void setMt_share(String mt_share) {
		this.mt_share = mt_share;
	}
	
	

}
