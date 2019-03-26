package com.meals.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.meals.model.MealsDAOImpl;
import com.meals.model.MealsVO;


@WebServlet("/MealsTest")
public class MealsTest extends HttpServlet{
	

	private static final long serialVersionUID = -1921776397555789457L;
	private MealsDAOImpl mealsDAOImpl;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MealsVO mealsVO = new MealsVO();
		mealsVO.setMeals_name("MEALS_NAMEinsert");
		mealsVO.setMeals_price_pre(90);
		mealsVO.setMeals_price_gen(80);
		mealsVO.setMeals_blob(null);
		mealsVO.setMeals_status("MEALS_STATUSinsert");
		
		mealsDAOImpl.insert(mealsVO);
		
		MealsVO mealsVO2 = new MealsVO();
		mealsVO2.setMeals_no("MEALS001");
		mealsVO2.setMeals_name("MEALS_NAMEupdate");
		mealsVO2.setMeals_price_pre(90);
		mealsVO2.setMeals_price_gen(80);
		mealsVO2.setMeals_blob(null);
		mealsVO2.setMeals_status("MEALS_STATUSupdate");
		
		mealsDAOImpl.update(mealsVO2);
		
		mealsDAOImpl.delete("MEALS017");
		
		System.out.println(mealsDAOImpl.findByPrimaryKey("MEALS001"));
		System.out.println(mealsDAOImpl.getAll());
		
	}

	@Override
	public void init() throws ServletException {
		mealsDAOImpl = new MealsDAOImpl();
	}

}
