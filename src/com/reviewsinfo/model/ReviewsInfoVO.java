package com.reviewsinfo.model;
import java.sql.Date;

public class ReviewsInfoVO implements java.io.Serializable {
	private Integer reviews_no;
	private String movie_no;
	private String reviews_title;
	private String reviews_auther;
	private Date reviews_times;
	private String reviews_con;
	private byte[] reviews_pic;
	
	
	public Integer getReviews_no() {
		return reviews_no;
	}
	public void setReviews_no(Integer reviews_no) {
		this.reviews_no = reviews_no;
	}
	public String getMovie_no() {
		return movie_no;
	}
	public void setMovie_no(String movie_no) {
		this.movie_no = movie_no;
	}
	public String getReviews_title() {
		return reviews_title;
	}
	public void setReviews_title(String reviews_title) {
		this.reviews_title = reviews_title;
	}
	public String getReviews_auther() {
		return reviews_auther;
	}
	public void setReviews_auther(String reviews_auther) {
		this.reviews_auther = reviews_auther;
	}
	public Date getReviews_times() {
		return reviews_times;
	}
	public void setReviews_times(Date reviews_times) {
		this.reviews_times = reviews_times;
	}
	public String getReviews_con() {
		return reviews_con;
	}
	public void setReviews_con(String reviews_con) {
		this.reviews_con = reviews_con;
	}
	public byte[] getReviews_pic() {
		return reviews_pic;
	}
	public void setReviews_pic(byte[] reviews_pic) {
		this.reviews_pic = reviews_pic;
	}
	
}
