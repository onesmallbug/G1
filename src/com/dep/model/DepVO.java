package com.dep.model;


import java.sql.Timestamp;

public class DepVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String 	deposit_change_no;
	private String 	deposit_member_no;
	private Integer deposit_change_money;
	private Timestamp 	deposit_change_date;
	public String getDeposit_change_no() {
		return deposit_change_no;
	}
	public void setDeposit_change_no(String deposit_change_no) {
		this.deposit_change_no = deposit_change_no;
	}
	public String getDeposit_member_no() {
		return deposit_member_no;
	}
	public void setDeposit_member_no(String deposit_member_no) {
		this.deposit_member_no = deposit_member_no;
	}
	public Integer getDeposit_change_money() {
		return deposit_change_money;
	}
	public void setDeposit_change_money(Integer deposit_change_money) {
		this.deposit_change_money = deposit_change_money;
	}
	public Timestamp getDeposit_change_date() {
		return deposit_change_date;
	}
	public void setDeposit_change_date(Timestamp deposit_change_date) {
		this.deposit_change_date = deposit_change_date;
	}
	@Override
	public String toString() {
		return "DepVO [deposit_change_no=" + deposit_change_no + ", deposit_member_no=" + deposit_member_no
				+ ", deposit_change_money=" + deposit_change_money + ", deposit_change_date=" + deposit_change_date
				+ "]";
	}
	
		
	
}
