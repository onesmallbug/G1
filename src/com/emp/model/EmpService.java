package com.emp.model;

import java.util.List;
import java.util.Map;

public class EmpService {
	
	private EmpDAO_interface dao ;
	
	public EmpService() {
		dao= new EmpDAOImpl();
	}
	
	public EmpVO addEmp(String  employee_name,Integer employee_sex,java.sql.Date 	employee_builddate,java.sql.Date 	employee_quitdate,String  employee_ability,String  employee_status, String employee_password) {
		
		EmpVO empVO = new EmpVO();
		empVO.setEmployee_name(employee_name);
		empVO.setEmployee_sex(employee_sex);
		empVO.setEmployee_builddate(employee_builddate);
		empVO.setEmployee_quitdate(employee_quitdate);
		empVO.setEmployee_ability(employee_ability);
		empVO.setEmployee_status(employee_status);
		empVO.setEmployee_password(employee_password);
		dao.insert(empVO);
		
		
		return empVO;
		
	}
	
	public EmpVO updateEmp(String  employee_no,String  employee_name,Integer employee_sex,java.sql.Date 	employee_builddate,java.sql.Date 	employee_quitdate,String  employee_ability,String  employee_status,String employee_password) {
		
		EmpVO empVO = new EmpVO();
		empVO.setEmployee_no(employee_no);
		empVO.setEmployee_name(employee_name);
		empVO.setEmployee_sex(employee_sex);
		empVO.setEmployee_builddate(employee_builddate);
		empVO.setEmployee_quitdate(employee_quitdate);
		empVO.setEmployee_ability(employee_ability);
		empVO.setEmployee_status(employee_status);
		empVO.setEmployee_password(employee_password);
		dao.update(empVO);
		
		return empVO;
		
		
	}
	
	public void delete(String employee_no){
		dao.delete(employee_no);
	}
	
	public EmpVO getoneEmp(String  employee_no) {
		return dao.findByPrimaryKey(employee_no);
	}
	
	public EmpVO getoneByEmployee_name(String  employee_name) {
		return dao.findByEmployee_name(employee_name);
	}
	
	public List<EmpVO> getAll(){
		return dao.getAll();
	}
	
	
	public List<EmpVO> getAll(Map<String,String[]> map){
		return dao.getAll();
	}

}
