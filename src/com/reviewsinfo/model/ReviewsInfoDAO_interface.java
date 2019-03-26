package com.reviewsinfo.model;

import java.util.*; 

public interface ReviewsInfoDAO_interface {
	public void insert(ReviewsInfoVO reviewsinfoVO);
	public void update(ReviewsInfoVO reviewsinfoVO);
	public void delete(Integer reviews_no);
	public ReviewsInfoVO findByPrimaryKey(Integer reviews_no);
	public List<ReviewsInfoVO> getAll();
	

}
