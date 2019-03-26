package com.mealsorderdetail.model;

import java.util.List;


public class MealsorderdetailService {
	
	private MealsorderdetailDAO_interface dao;
	
	public MealsorderdetailService() {
		dao = new MealsorderdetailDAO();
	}
	
	public MealsorderdetailVO addMealsorderdetail(String order_no,String meals_no,Integer mo_count) {

		MealsorderdetailVO mealsorderdetailVO = new MealsorderdetailVO();
		
		mealsorderdetailVO.setOrder_no(order_no);
		mealsorderdetailVO.setMeals_no(meals_no);
		mealsorderdetailVO.setMo_count(mo_count);
		dao.insert(mealsorderdetailVO);


		return mealsorderdetailVO;
	}
	
	
	public MealsorderdetailVO updateMealsorderdetail(String order_no,String meals_no,Integer mo_count) {

		MealsorderdetailVO mealsorderdetailVO = new MealsorderdetailVO();
		
		mealsorderdetailVO.setOrder_no(order_no);
		mealsorderdetailVO.setMeals_no(meals_no);
		mealsorderdetailVO.setMo_count(mo_count);
		dao.update(mealsorderdetailVO);


		return mealsorderdetailVO;
	}
	
	public void deleteMealsorderdetail(String order_no) {
		dao.delete(order_no);
	}

	public MealsorderdetailVO getOneMealsorderdetail(String order_no) {
		return dao.findByPrimaryKey(order_no);
	}

	public List<MealsorderdetailVO> getAll() {
		return dao.getAll();
	}
	
	

}
