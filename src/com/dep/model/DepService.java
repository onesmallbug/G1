package com.dep.model;

import java.sql.Timestamp;
import java.util.List;

import com.mem.model.MemVO;

public class DepService {
	
	private DepDAO_interface dao;
	
	public DepService() {
		dao = new DepDAOImpl();
	}
	
	public DepVO addDep( String deposit_member_no,Integer deposit_change_money,Timestamp deposit_change_date) {
		
		DepVO depVO = new DepVO();
		String pk = null;
		
		depVO.setDeposit_member_no(deposit_member_no);
		depVO.setDeposit_change_money(deposit_change_money);
		depVO.setDeposit_change_date(deposit_change_date);
		
		pk = dao.insert(depVO);
		
		depVO.setDeposit_change_no(pk);
		
		
		return depVO;
		
	}
	
	public DepVO updateEmp( String deposit_change_no,String deposit_member_no,Integer deposit_change_money,Timestamp deposit_change_date) {
		
		DepVO depVO = new DepVO();
		
		depVO.setDeposit_change_no(deposit_change_no);
		depVO.setDeposit_member_no(deposit_member_no);
		depVO.setDeposit_change_money(deposit_change_money);
		depVO.setDeposit_change_date(deposit_change_date);
		
		dao.update(depVO);
		
		return depVO;
		
	}
	
	public void deleteDep(String deposit_change_no) {
		dao.delete(deposit_change_no);
	}
	
	public DepVO getoneDep(String deposit_change_no) {
		
		return dao.findByPrimaryKey(deposit_change_no);
		
	}
	
public DepVO getoneDep_mem_no(String deposit_member_no) {
		
		return dao.findByMem_no(deposit_member_no);
		
	}
	
	public List<DepVO> getAll(){
		
		return dao.getAll();
		
	}
	
	
	public List<DepVO> findByMem_no1(String deposit_member_no){
		return dao.findByMem_no1(deposit_member_no);
		
	}
	
	
	public void insertDepositMain(DepVO depVO , MemVO memVO) {
		dao.insertDepositMain(depVO,memVO);
	}

}
