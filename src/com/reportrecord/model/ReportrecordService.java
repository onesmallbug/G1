package com.reportrecord.model;

import java.sql.Timestamp;
import java.util.*;



public class ReportrecordService {
	
	private Reportrecord_interface dao;
	
	public ReportrecordService(){
		dao = new ReportrecordJNDIDAO();
	}
	
	public ReportrecordVO addRep(String group_ID,String member_no,Timestamp report_time,String report_Con,Integer report_sta,String employee_no) {
		
	
		ReportrecordVO reportrecordVO = new ReportrecordVO();
		
		reportrecordVO.setGroup_ID(group_ID);
		reportrecordVO.setMember_no(member_no);
		reportrecordVO.setReport_time(report_time);
		reportrecordVO.setReport_Con(report_Con);
		reportrecordVO.setReport_sta(report_sta);
		reportrecordVO.setEmployee_no(employee_no);
		dao.insert(reportrecordVO);
	
		
		return reportrecordVO;
	}
	
	public ReportrecordVO updateRep(Integer report_sta) {
		
		ReportrecordVO reportrecordVO = new ReportrecordVO();
		reportrecordVO.setReport_sta(report_sta);
		dao.update(reportrecordVO);
		
		return reportrecordVO;
	}
	
	public ReportrecordVO getoneRep(String group_ID) {
		
		return dao.findByPrimaryKey(group_ID);
			
	}
	
	public List<ReportrecordVO> getAll(){
		
		return dao.getAll();
		
	}
	
}
