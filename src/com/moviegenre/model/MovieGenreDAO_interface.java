package com.moviegenre.model;

import java.util.*;

public interface MovieGenreDAO_interface {
	public void insert(MovieGenreVO moviegenreVO);
	public void update(MovieGenreVO moviegenreVO);
    public void delete(Integer genre_no);
    public MovieGenreVO findByPrimaryKey(Integer genre_no);
    public List<MovieGenreVO> getAll();

}
