package com.perm.model;

public class PermVO implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2934148391678829801L;
	
	
	private String permission_no;			
	private String permission_name;
	
	
	public String getPermission_no() {
		return permission_no;
	}
	public void setPermission_no(String permission_no) {
		this.permission_no = permission_no;
	}
	public String getPermission_name() {
		return permission_name;
	}
	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}			
	
	
	
	
}
