package com.mem.model;


import java.sql.Date;





public class MemVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String  member_no ;
	private String  member_account ;
	private String  member_password ;
	private String  member_name ;
	private String  member_nick ;
	private Integer member_sex ;
	private Date    member_birthday ; 
	private String  member_address;
	private String  member_telephone;
	private String  member_email ;
	private byte[]  member_picture ;
	private String  member_credit_number;
	private Integer member_back_verification;
	private Date 	member_buildday;
	private Integer member_point;
	private String 	member_status;
	
	
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getMember_account() {
		return member_account;
	}
	public void setMember_account(String member_account) {
		this.member_account = member_account;
	}
	public String getMember_password() {
		return member_password;
	}
	public void setMember_password(String member_password) {
		this.member_password = member_password;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public Integer getMember_sex() {
		return member_sex;
	}
	public void setMember_sex(Integer member_sex) {
		this.member_sex = member_sex;
	}
	public Date getMember_birthday() {
		return member_birthday;
	}
	public void setMember_birthday(Date member_birthday) {
		this.member_birthday = member_birthday;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getMember_telephone() {
		return member_telephone;
	}
	public void setMember_telephone(String member_telephone) {
		this.member_telephone = member_telephone;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public byte[] getMember_picture() {
		return member_picture;
	}
	public void setMember_picture(byte[] member_picture) {
		this.member_picture = member_picture;
	}
	public String getMember_credit_number() {
		return member_credit_number;
	}
	public void setMember_credit_number(String member_credit_number) {
		this.member_credit_number = member_credit_number;
	}
	public Integer getMember_back_verification() {
		return member_back_verification;
	}
	public void setMember_back_verification(Integer member_back_verification) {
		this.member_back_verification = member_back_verification;
	}
	public Date getMember_buildday() {
		return member_buildday;
	}
	public void setMember_buildday(Date member_buildday) {
		this.member_buildday = member_buildday;
	}
	public Integer getMember_point() {
		return member_point;
	}
	public void setMember_point(Integer member_point) {
		this.member_point = member_point;
	}
	public String getMember_status() {
		return member_status;
	}
	public void setMember_status(String member_status) {
		this.member_status = member_status;
	}
	
	
	
	


	
	
	
	
	

}
