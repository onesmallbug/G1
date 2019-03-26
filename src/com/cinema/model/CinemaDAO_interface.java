package com.cinema.model;

import java.util.List;


public interface CinemaDAO_interface {
	public void insert(CinemaVO cinemaVO);
    public void update(CinemaVO cinemaVO);
    public void delete(String cinemaVO);
    public CinemaVO findByPrimaryKey(String cinema_no);
    public List<CinemaVO> getAll();
}
