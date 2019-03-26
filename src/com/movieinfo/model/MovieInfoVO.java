package com.movieinfo.model;

import java.sql.Date;

public class MovieInfoVO implements java.io.Serializable {
	private String movie_no;
	private Integer genre_no;
	private String movie_name;
	private Double movie_score;
	private byte[] movie_level;
	private String movie_director;
	private String movie_cast;
	private String movie_intro;
	private String movie_length;
	private String movie_trailer;
	private byte[] movie_pic;
	private Date movie_in;
	private Date movie_out;
	private Integer movie_count;
	private Integer movie_exp;
	private Integer movie_noexp;
	private Integer movie_touch;
	private Integer movie_ticket;
	
	
	public String getMovie_no() {
		return movie_no;
	}
	public void setMovie_no(String movie_no) {
		this.movie_no = movie_no;
	}
	public Integer getGenre_no() {
		return genre_no;
	}
	public void setGenre_no(Integer genre_no) {
		this.genre_no = genre_no;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public Double getMovie_score() {
		return movie_score;
	}
	public void setMovie_score(Double movie_score) {
		this.movie_score = movie_score;
	}
	public byte[] getMovie_level() {
		return movie_level;
	}
	public void setMovie_level(byte[] movie_level) {
		this.movie_level = movie_level;
	}
	public String getMovie_director() {
		return movie_director;
	}
	public void setMovie_director(String movie_director) {
		this.movie_director = movie_director;
	}
	public String getMovie_cast() {
		return movie_cast;
	}
	public void setMovie_cast(String movie_cast) {
		this.movie_cast = movie_cast;
	}
	public String getMovie_intro() {
		return movie_intro;
	}
	public void setMovie_intro(String movie_intro) {
		this.movie_intro = movie_intro;
	}
	public String getMovie_length() {
		return movie_length;
	}
	public void setMovie_length(String movie_length) {
		this.movie_length = movie_length;
	}
	public String getMovie_trailer() {
		return movie_trailer;
	}
	public void setMovie_trailer(String movie_trailer) {
		this.movie_trailer = movie_trailer;
	}
	public byte[] getMovie_pic() {
		return movie_pic;
	}
	public void setMovie_pic(byte[] movie_pic) {
		this.movie_pic = movie_pic;
	}
	public Date getMovie_in() {
		return movie_in;
	}
	public void setMovie_in(Date movie_in) {
		this.movie_in = movie_in;
	}
	public Date getMovie_out() {
		return movie_out;
	}
	public void setMovie_out(Date movie_out) {
		this.movie_out = movie_out;
	}
	public Integer getMovie_count() {
		return movie_count;
	}
	public void setMovie_count(Integer movie_count) {
		this.movie_count = movie_count;
	}
	public Integer getMovie_exp() {
		return movie_exp;
	}
	public void setMovie_exp(Integer movie_exp) {
		this.movie_exp = movie_exp;
	}
	public Integer getMovie_noexp() {
		return movie_noexp;
	}
	public void setMovie_noexp(Integer movie_noexp) {
		this.movie_noexp = movie_noexp;
	}
	public Integer getMovie_touch() {
		return movie_touch;
	}
	public void setMovie_touch(Integer movie_touch) {
		this.movie_touch = movie_touch;
	}
	public Integer getMovie_ticket() {
		return movie_ticket;
	}
	public void setMovie_ticket(Integer movie_ticket) {
		this.movie_ticket = movie_ticket;
	}
	
	

}
