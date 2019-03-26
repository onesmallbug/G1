package com.perm.model;

import java.util.List;

public class PermService {
	
	private PermDAO_interface dao ;
	
	public PermService() {
		dao = new PermDAOImpl();
	}
	
	public PermVO addPerm(String permission_name) {
		
		PermVO permVO = new PermVO();
		permVO.setPermission_name(permission_name);
		dao.insert(permVO);
		
		return permVO;
	}
	
	public PermVO updatePerm(String permission_no, String permission_name) {
		
		PermVO permVO = new PermVO();
		permVO.setPermission_no(permission_no);
		permVO.setPermission_name(permission_name);
		dao.update(permVO);
		
		return permVO;
		
	}

	public void deletePerm(String permission_no) {
		dao.delete(permission_no);
	}
	
	public PermVO getonePerm(String permission_no) {
		
		return dao.findByPrimaryKey(permission_no);
	}
	
	public List<PermVO> getAll(){
		return dao.getAll();
		
	}
}
