package com.emp.model;

import java.sql.Date;

public class EmpVO implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1543257877541047833L;
	
	
	private String  employee_no;		
	private String  employee_name;	
	private Integer employee_sex;
	private Date 	employee_builddate;
	private Date 	employee_quitdate;			
	private String  employee_ability;			
	private String  employee_status;
	private String  employee_password;
	
	public String getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public Integer getEmployee_sex() {
		return employee_sex;
	}
	public void setEmployee_sex(Integer employee_sex) {
		this.employee_sex = employee_sex;
	}
	public Date getEmployee_builddate() {
		return employee_builddate;
	}
	public void setEmployee_builddate(Date employee_builddate) {
		this.employee_builddate = employee_builddate;
	}
	public Date getEmployee_quitdate() {
		return employee_quitdate;
	}
	public void setEmployee_quitdate(Date employee_quitdate) {
		this.employee_quitdate = employee_quitdate;
	}
	public String getEmployee_ability() {
		return employee_ability;
	}
	public void setEmployee_ability(String employee_ability) {
		this.employee_ability = employee_ability;
	}
	public String getEmployee_status() {
		return employee_status;
	}
	public void setEmployee_status(String employee_status) {
		this.employee_status = employee_status;
	}
	public String getEmployee_password() {
		return employee_password;
	}
	public void setEmployee_password(String employee_password) {
		this.employee_password = employee_password;
	}
	
	
	
	
	
	
	
	
	
}
