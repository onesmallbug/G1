package com.groupall.modal;
import java.sql.Date;
import java.sql.Timestamp;

public class GroupAllVO implements java.io.Serializable{
	private String group_ID;
	private String member_no;
	private String sessions_no;
	private Timestamp group_addTime;
	private Timestamp group_endTime;
	private String groupName;
	private Integer group_upper;
	private Integer group_lower;
	
	
	
	
	public String getGroup_ID() {
		return group_ID;
	}
	public void setGroup_ID(String group_ID) {
		this.group_ID = group_ID;
	}
	public String getMember_no() {
		return member_no;
	}
	public void setMember_no(String member_no) {
		this.member_no = member_no;
	}
	public String getSessions_no() {
		return sessions_no;
	}
	public void setSessions_no(String sessions_no) {
		this.sessions_no = sessions_no;
	}
	public Timestamp getGroup_addTime() {
		return group_addTime;
	}
	public void setGroup_addTime(Timestamp date) {
		this.group_addTime = date;
	}
	public Timestamp getGroup_endTime() {
		return group_endTime;
	}
	public void setGroup_endTime(Timestamp group_endTime) {
		this.group_endTime = group_endTime;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getGroup_upper() {
		return group_upper;
	}
	public void setGroup_upper(Integer group_upper) {
		this.group_upper = group_upper;
	}
	public Integer getGroup_lower() {
		return group_lower;
	}
	public void setGroup_lower(Integer group_lower) {
		this.group_lower = group_lower;
	}

}
