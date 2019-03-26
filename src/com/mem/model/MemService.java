package com.mem.model;

import java.util.List;



public class MemService {
	
	private MemDAO_interface dao;
	
	public MemService() {
		dao = new MemDAOImpl();
	}
	
	public MemVO addMem( String  member_account,String  member_password,String  member_name,String  member_nick,Integer member_sex,java.sql.Date  member_birthday,String  member_address,String  member_telephone,String  member_email,byte[]  member_picture,String  member_credit_number,Integer member_back_verification,java.sql.Date member_buildday,Integer member_point,String member_status) {
		
		
		MemVO memVO = new MemVO();
		memVO.setMember_account(member_account);
		memVO.setMember_password(member_password);
		memVO.setMember_name(member_name);
		memVO.setMember_nick(member_nick);
		memVO.setMember_sex(member_sex);
		memVO.setMember_birthday(member_birthday);
		memVO.setMember_address(member_address);
		memVO.setMember_telephone(member_telephone);
		memVO.setMember_email(member_email);
		memVO.setMember_picture(member_picture);
		memVO.setMember_credit_number(member_credit_number);
		memVO.setMember_back_verification(member_back_verification);
		memVO.setMember_buildday(member_buildday);
		memVO.setMember_point(member_point);
		memVO.setMember_status(member_status);
		dao.insert(memVO);
		
		return memVO;
		
	}
	
	public MemVO updateMem(String member_no,String  member_account,String  member_password,String  member_name,String  member_nick,Integer member_sex,java.sql.Date  member_birthday,String  member_address,String member_telephone,String  member_email,byte[]  member_picture,String  member_credit_number,Integer member_back_verification,java.sql.Date member_buildday,Integer member_point,String member_status) {
		
		MemVO memVO = new MemVO();
		memVO.setMember_no(member_no);
		memVO.setMember_account(member_account);
		memVO.setMember_password(member_password);
		memVO.setMember_name(member_name);
		memVO.setMember_nick(member_nick);
		memVO.setMember_sex(member_sex);
		memVO.setMember_birthday(member_birthday);
		memVO.setMember_address(member_address);
		memVO.setMember_telephone(member_telephone);
		memVO.setMember_email(member_email);
		memVO.setMember_picture(member_picture);
		memVO.setMember_credit_number(member_credit_number);
		memVO.setMember_back_verification(member_back_verification);
		memVO.setMember_buildday(member_buildday);
		memVO.setMember_point(member_point);
		memVO.setMember_status(member_status);
		dao.update(memVO);
		
		return memVO;
		
	}
	
	public void deleteMem(String member_no) {
		dao.delete(member_no);
	}
	
	public MemVO getoneMem(String member_no) {
		
		return dao.findByPrimaryKey(member_no);
		
	}
	
	public MemVO getoneByAccountMem(String member_account) {
		
		return dao.findByMember_account(member_account);
		
	}
	
	public List<MemVO> getAll(){
		
		return dao.getAll();
		
	}
	
	
	
	

	public void update_member_status(String member_no) {
		// TODO Auto-generated method stub
		
		dao.update_member_status(member_no);
	}
	
	
	public void update_member_password(String member_account , String member_password) {
		// TODO Auto-generated method stub
		
		dao.updateMember_password(member_account,member_password);
	}
	

}
