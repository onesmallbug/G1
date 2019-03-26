package com.moviegenre.model;

import java.util.List;


public class MovieGenreService {
	
	private MovieGenreDAO_interface dao;

	public MovieGenreService() {
		dao = new MovieGenreDAO();
	}

	public MovieGenreVO addMovieGenre(String genre_name) {

		MovieGenreVO moviegenreVO = new MovieGenreVO();

		moviegenreVO.setGenre_name(genre_name);
		dao.insert(moviegenreVO);

		return moviegenreVO;
	}

	public MovieGenreVO updateMovieGenre(Integer genre_no, String genre_name) {

		MovieGenreVO moviegenreVO = new MovieGenreVO();

		moviegenreVO.setGenre_no(genre_no);
		moviegenreVO.setGenre_name(genre_name);
		dao.update(moviegenreVO);

		return moviegenreVO;
	}

	public void deleteMovieGenre(Integer genre_no) {
		dao.delete(genre_no);
	}

	public MovieGenreVO getOneGenre(Integer genre_no) {
		return dao.findByPrimaryKey(genre_no);
	}

	public List<MovieGenreVO> getAll() {
		return dao.getAll();
	}
	
}
