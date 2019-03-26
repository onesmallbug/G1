package com.cinema.model;

import java.io.Serializable;

public class CinemaVO implements Serializable{
	private static final long serialVersionUID = -3572772473273025747L;
	
	private String cinema_no;
	private String cinema_type;
	private Integer cinema_size;
	private String cinema_name;
	private Integer cinema_correct;
	private String cinema_status;
	
	public String getCinema_no() {
		return cinema_no;
	}
	public void setCinema_no(String cinema_no) {
		this.cinema_no = cinema_no;
	}
	public String getCinema_type() {
		return cinema_type;
	}
	public void setCinema_type(String cinema_type) {
		this.cinema_type = cinema_type;
	}
	public Integer getCinema_size() {
		return cinema_size;
	}
	public void setCinema_size(Integer cinema_size) {
		this.cinema_size = cinema_size;
	}
	public String getCinema_name() {
		return cinema_name;
	}
	public void setCinema_name(String cinema_name) {
		this.cinema_name = cinema_name;
	}
	public Integer getCinema_correct() {
		return cinema_correct;
	}
	public void setCinema_correct(Integer cinema_correct) {
		this.cinema_correct = cinema_correct;
	}
	public String getCinema_status() {
		return cinema_status;
	}
	public void setCinema_status(String cinema_status) {
		this.cinema_status = cinema_status;
	}
	

}
