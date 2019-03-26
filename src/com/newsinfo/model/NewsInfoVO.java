package com.newsinfo.model;
import java.sql.Date;

public class NewsInfoVO implements java.io.Serializable {
	private Integer news_no;
	private String movie_no;
	private String news_title;
	private String news_auther;
	private Date news_times;
	private String news_con;
	private byte[] news_pic;
	
	
	public Integer getNews_no() {
		return news_no;
	}
	public void setNews_no(Integer news_no) {
		this.news_no = news_no;
	}
	public String getMovie_no() {
		return movie_no;
	}
	public void setMovie_no(String movie_no) {
		this.movie_no = movie_no;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_auther() {
		return news_auther;
	}
	public void setNews_auther(String news_auther) {
		this.news_auther = news_auther;
	}
	public Date getNews_times() {
		return news_times;
	}
	public void setNews_times(Date news_times) {
		this.news_times = news_times;
	}
	public String getNews_con() {
		return news_con;
	}
	public void setNews_con(String news_con) {
		this.news_con = news_con;
	}
	public byte[] getNews_pic() {
		return news_pic;
	}
	public void setNews_pic(byte[] news_pic) {
		this.news_pic = news_pic;
	}
	

}
