package com.meals.model;

import java.util.List;

public class MealsService {
	
	private MealsDAO_interface dao;
	
	public MealsService() {
		dao = new MealsDAOImpl();
	}
	
	public MealsVO addMea(String meals_name, Integer meals_price_pre, Integer meals_price_gen,
			byte[] meals_blob, String meals_status) {

		MealsVO mealsVO = new MealsVO();

		mealsVO.setMeals_name(meals_name);
		mealsVO.setMeals_price_pre(meals_price_pre);
		mealsVO.setMeals_price_gen(meals_price_gen);
		mealsVO.setMeals_blob(meals_blob);
		mealsVO.setMeals_status(meals_status);
		dao.insert(mealsVO);

		return mealsVO;
	}

	public MealsVO updateMea(String meals_no, String meals_name, Integer meals_price_pre, Integer meals_price_gen,
			byte[] meals_blob, String meals_status) {

		MealsVO mealsVO = new MealsVO();
		mealsVO.setMeals_no(meals_no);
		mealsVO.setMeals_name(meals_name);
		mealsVO.setMeals_price_pre(meals_price_pre);
		mealsVO.setMeals_price_gen(meals_price_gen);
		mealsVO.setMeals_blob(meals_blob);
		mealsVO.setMeals_status(meals_status);
		dao.update(mealsVO);

		return mealsVO;
	}

	public void deleteMea(String meals_no) {
		dao.delete(meals_no);
	}

	public MealsVO getOneMea(String meals_no) {
		return dao.findByPrimaryKey(meals_no);
	}

	public List<MealsVO> getAll() {
		return dao.getAll();
	} 

}
