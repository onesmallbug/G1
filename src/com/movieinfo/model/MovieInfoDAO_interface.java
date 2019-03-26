package com.movieinfo.model;

import java.util.*;

public interface MovieInfoDAO_interface {
	public void insert(MovieInfoVO movieinfoVO);
	public void update(MovieInfoVO movieinfoVO);
    public void delete(String movie_no);
    public MovieInfoVO findByPrimaryKey(String movie_no);
    public List<MovieInfoVO> getAll();
    public List<MovieInfoVO> getAllByScore(java.sql.Date stdate, java.sql.Date enddate);
    public void changeExp(String movie_no, Integer movie_exp);
    public void changeNoExp(String movie_no, Integer movie_noexp);
}
