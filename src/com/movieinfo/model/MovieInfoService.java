package com.movieinfo.model;

import java.sql.Date;
import java.util.List;

public class MovieInfoService {
	private MovieInfoDAO_interface dao;

	public MovieInfoService() {
		dao = new MovieInfoDAO();
	}

	public MovieInfoVO addMovieInfo(
			Integer genre_no,
			String movie_name,
			Double movie_score,
			byte[] movie_level,
			String movie_director,
			String movie_cast,
			String movie_intro,
			String movie_length,
			String movie_trailer,
			byte[] movie_pic,
			Date movie_in,
			Date movie_out,
			Integer movie_count,
			Integer movie_exp,
			Integer movie_noexp,
			Integer movie_touch,
			Integer movie_ticket) {

		MovieInfoVO movieinfoVO = new MovieInfoVO();

		movieinfoVO.setGenre_no(genre_no);
		movieinfoVO.setMovie_name(movie_name);
		movieinfoVO.setMovie_score(movie_score);
		movieinfoVO.setMovie_level(movie_level);
		movieinfoVO.setMovie_director(movie_director);
		movieinfoVO.setMovie_cast(movie_cast);
		movieinfoVO.setMovie_intro(movie_intro);
		movieinfoVO.setMovie_length(movie_length);
		movieinfoVO.setMovie_trailer(movie_trailer);
		movieinfoVO.setMovie_pic(movie_pic);
		movieinfoVO.setMovie_in(movie_in);
		movieinfoVO.setMovie_out(movie_out);
		movieinfoVO.setMovie_count(movie_count);
		movieinfoVO.setMovie_exp(movie_exp);
		movieinfoVO.setMovie_noexp(movie_noexp);
		movieinfoVO.setMovie_touch(movie_touch);
		movieinfoVO.setMovie_ticket(movie_ticket);
		dao.insert(movieinfoVO);

		return movieinfoVO;
	}

	public MovieInfoVO updateMovieInfo(
			String movie_no,
			Integer genre_no,
			String movie_name,
			Double movie_score,
			byte[] movie_level,
			String movie_director,
			String movie_cast,
			String movie_intro,
			String movie_length,
			String movie_trailer,
			byte[] movie_pic,
			Date movie_in,
			Date movie_out,
			Integer movie_count,
			Integer movie_exp,
			Integer movie_noexp,
			Integer movie_touch,
			Integer movie_ticket) {

		MovieInfoVO movieinfoVO = new MovieInfoVO();

		movieinfoVO.setMovie_no(movie_no);
		movieinfoVO.setGenre_no(genre_no);
		movieinfoVO.setMovie_name(movie_name);
		movieinfoVO.setMovie_score(movie_score);
		movieinfoVO.setMovie_level(movie_level);
		movieinfoVO.setMovie_director(movie_director);
		movieinfoVO.setMovie_cast(movie_cast);
		movieinfoVO.setMovie_intro(movie_intro);
		movieinfoVO.setMovie_length(movie_length);
		movieinfoVO.setMovie_trailer(movie_trailer);
		movieinfoVO.setMovie_pic(movie_pic);
		movieinfoVO.setMovie_in(movie_in);
		movieinfoVO.setMovie_out(movie_out);
		movieinfoVO.setMovie_count(movie_count);
		movieinfoVO.setMovie_exp(movie_exp);
		movieinfoVO.setMovie_noexp(movie_noexp);
		movieinfoVO.setMovie_touch(movie_touch);
		movieinfoVO.setMovie_ticket(movie_ticket);
		dao.update(movieinfoVO);

		return movieinfoVO;
	}

	public void deleteMovieInfo(String movie_no) {
		dao.delete(movie_no);
	}

	public MovieInfoVO getOneMovieInfo(String movie_no) {
		return dao.findByPrimaryKey(movie_no);
	}

	public List<MovieInfoVO> getAll() {
		return dao.getAll();
	}
	
	public List<MovieInfoVO> getAllByScore(java.sql.Date stdate, java.sql.Date enddate) {
		return dao.getAllByScore(stdate, enddate);
	}
	
	public void updateExp(String movie_no, Integer movie_exp) {
		dao.changeExp(movie_no, movie_exp);
	}
	
	public void updateNoExp(String movie_no, Integer movie_noexp) {
		dao.changeNoExp(movie_no, movie_noexp);
	}
}
