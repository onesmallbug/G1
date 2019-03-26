package com.moviegenre.model;

public class MovieGenreVO implements java.io.Serializable {
	private Integer genre_no;
	private String genre_name;
	
	
	public Integer getGenre_no() {
		return genre_no;
	}
	public void setGenre_no(Integer genre_no) {
		this.genre_no = genre_no;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}

}
