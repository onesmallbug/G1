package com.meals.model;

import java.io.Serializable;

public class MealsVO implements Serializable {
	private static final long serialVersionUID = -1484965596933457098L;
	
	private String meals_no;
	private String meals_name;
	private Integer meals_price_pre;
	private Integer meals_price_gen;
	private byte[] meals_blob;
	private String meals_status;
	public String getMeals_no() {
		return meals_no;
	}
	public void setMeals_no(String meals_no) {
		this.meals_no = meals_no;
	}
	public String getMeals_name() {
		return meals_name;
	}
	public void setMeals_name(String meals_name) {
		this.meals_name = meals_name;
	}
	public Integer getMeals_price_pre() {
		return meals_price_pre;
	}
	public void setMeals_price_pre(Integer meals_price_pre) {
		this.meals_price_pre = meals_price_pre;
	}
	public Integer getMeals_price_gen() {
		return meals_price_gen;
	}
	public void setMeals_price_gen(Integer meals_price_gen) {
		this.meals_price_gen = meals_price_gen;
	}
	public byte[] getMeals_blob() {
		return meals_blob;
	}
	public void setMeals_blob(byte[] meals_blob) {
		this.meals_blob = meals_blob;
	}
	public String getMeals_status() {
		return meals_status;
	}
	public void setMeals_status(String meals_status) {
		this.meals_status = meals_status;
	}
	

}
