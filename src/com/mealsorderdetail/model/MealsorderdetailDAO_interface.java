package com.mealsorderdetail.model;

import java.util.*;

public interface MealsorderdetailDAO_interface {
	public void insert(MealsorderdetailVO mealsorderdetailVO);
	public void update(MealsorderdetailVO mealsorderdetailVO);
	public void delete(String order_no);
	public MealsorderdetailVO findByPrimaryKey(String order_no);
	public List<MealsorderdetailVO> getAll();
	
	
	

}
