package com.reportrecord.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ReportrecordVO implements java.io.Serializable {

		
		private String group_ID;
		private String member_no;
		private Timestamp report_time;
		private String report_Con;
		private Integer report_sta;
		private String employee_no;
		
		
		
		
		
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
		public Timestamp getReport_time() {
			return report_time;
		}
		public void setReport_time(Timestamp report_time) {
			this.report_time = report_time;
		}
		public String getReport_Con() {
			return report_Con;
		}
		public void setReport_Con(String report_Con) {
			this.report_Con = report_Con;
		}
		public Integer getReport_sta() {
			return report_sta;
		}
		public void setReport_sta(Integer report_sta) {
			this.report_sta = report_sta;
		}
		public String getEmployee_no() {
			return employee_no;
		}
		public void setEmployee_no(String employee_no) {
			this.employee_no = employee_no;
		}
		
		
}
