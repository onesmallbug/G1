package com.meals.model;

import java.util.List;


public interface MealsDAO_interface {
	
	public void insert(MealsVO mealsVO);
    public void update(MealsVO mealsVO);
    public void delete(String meals_no);
    public MealsVO findByPrimaryKey(String meals_no);
    public List<MealsVO> getAll();

}
