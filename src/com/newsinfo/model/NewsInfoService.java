package com.newsinfo.model;

import java.sql.Date;
import java.util.List;

public class NewsInfoService {
	private NewsInfoDAO_interface dao;

	public NewsInfoService() {
		dao = new NewsInfoDAO();
	}

	public NewsInfoVO addNewsInfo(
			String movie_no,
			String news_title,
			String news_auther,
			java.sql.Date news_times,
			String news_con,
			byte[] news_pic
			) {

		NewsInfoVO newsinfoVO = new NewsInfoVO();

		newsinfoVO.setMovie_no(movie_no);
		newsinfoVO.setNews_title(news_title);
		newsinfoVO.setNews_auther(news_auther);
		newsinfoVO.setNews_times(news_times);
		newsinfoVO.setNews_con(news_con);
		newsinfoVO.setNews_pic(news_pic);
		dao.insert(newsinfoVO);

		return newsinfoVO;
	}

	public NewsInfoVO updateNewsInfo(
			Integer news_no,
			String movie_no,
			String news_title,
			String news_auther,
			java.sql.Date news_times,
			String news_con,
			byte[] news_pic
			) {

		NewsInfoVO newsinfoVO = new NewsInfoVO();

		newsinfoVO.setNews_no(news_no);
		newsinfoVO.setMovie_no(movie_no);
		newsinfoVO.setNews_title(news_title);
		newsinfoVO.setNews_auther(news_auther);
		newsinfoVO.setNews_times(news_times);
		newsinfoVO.setNews_con(news_con);
		newsinfoVO.setNews_pic(news_pic);
		dao.update(newsinfoVO);

		return newsinfoVO;
	}

	public void deleteNewsInfo(Integer news_no) {
		dao.delete(news_no);
	}

	public NewsInfoVO getOneNewsInfo(Integer news_no) {
		return dao.findByPrimaryKey(news_no);
	}

	public List<NewsInfoVO> getAll() {
		return dao.getAll();
	}

}
