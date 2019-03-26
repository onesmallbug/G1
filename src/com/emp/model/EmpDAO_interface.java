package com.emp.model;

import java.util.List;
import java.util.Map;

public interface EmpDAO_interface {
	
	public void insert (EmpVO manVO);
	public void update (EmpVO manVO);
	public void delete (String employee_no);
	public EmpVO findByPrimaryKey(String employee_no);
	public EmpVO findByEmployee_name(String employee_name);
	public List<EmpVO> getAll();
	public List<EmpVO> getAll(Map<String,String[]> map);

}
