package com.mealsorderdetail.model;

public class MealsorderdetailVO implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8200002412050696257L;
	
	private String order_no;
	private String meals_no;
	private Integer mo_count;
	
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getMeals_no() {
		return meals_no;
	}
	public void setMeals_no(String meals_no) {
		this.meals_no = meals_no;
	}
	public Integer getMo_count() {
		return mo_count;
	}
	public void setMo_count(Integer mo_count) {
		this.mo_count = mo_count;
	}
	
	
	

}
