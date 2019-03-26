package com.reportrecord.model;

import java.util.List;



public interface Reportrecord_interface {
	
	public void insert (ReportrecordVO repcoVO);
	public void update(ReportrecordVO repcoVO);
	public  ReportrecordVO findByPrimaryKey(String group_ID);
	public List<ReportrecordVO> getAll();
	

}
