package com.empper._det.model;

import java.util.List;

public class Empper_detService {
	
	private Empper_detDAO_interface dao ;
	
	public Empper_detService() {
		dao = new Empper_detDAOImpl();
	}
	
	public Empper_detVO addEmpper_det(String employee_no,String permission_no) {
		
		Empper_detVO empper_detVO = new Empper_detVO();
		
		empper_detVO.setEmployee_no(employee_no);
		empper_detVO.setPermission_no(permission_no);
		dao.insert(empper_detVO);
		
		return empper_detVO;
		
	}
	
	public Empper_detVO updateEmpper_det(String employee_no_old,String permission_no_old,String employee_no_new,String permission_no_new) {
		
		Empper_detVO empper_detVOold = new Empper_detVO();
		
		empper_detVOold.setEmployee_no(employee_no_old);
		empper_detVOold.setPermission_no(permission_no_old);
		
		Empper_detVO empper_detVOnew = new Empper_detVO();
		
		empper_detVOnew.setEmployee_no(employee_no_new);
		empper_detVOnew.setPermission_no(permission_no_new);
		
		dao.update(empper_detVOold, empper_detVOnew);
		
		
		return empper_detVOnew;
		
	}
	
	public void deleteEmpper_det(String employee_no,String permission_no) {
		dao.delete(employee_no, permission_no);
	}
	
	public Empper_detVO getoneEmpper_det(String employee_no,String permission_no) {
		
		return dao.findByPrimaryKey(employee_no, permission_no);
		
	}
	
	public List<Empper_detVO> getAll(){
		
		return dao.getAll();
		
	}

}
