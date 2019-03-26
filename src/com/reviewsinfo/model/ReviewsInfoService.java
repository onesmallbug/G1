package com.reviewsinfo.model;

import java.sql.Date;
import java.util.List;

public class ReviewsInfoService {
	private ReviewsInfoDAO_interface dao;

	public ReviewsInfoService() {
		dao = new ReviewsInfoDAO();
	}

	public ReviewsInfoVO addReviewsInfo(
			Integer reviews_no,
			String movie_no,
			String reviews_title,
			String reviews_auther,
			java.sql.Date reviews_times,
			String reviews_con,
			byte[] reviews_pic) {

		ReviewsInfoVO reviewsinfoVO = new ReviewsInfoVO();

		reviewsinfoVO.setReviews_no(reviews_no);
		reviewsinfoVO.setMovie_no(movie_no);
		reviewsinfoVO.setReviews_title(reviews_title);
		reviewsinfoVO.setReviews_auther(reviews_auther);
		reviewsinfoVO.setReviews_times(reviews_times);
		reviewsinfoVO.setReviews_con(reviews_con);
		reviewsinfoVO.setReviews_pic(reviews_pic);
		dao.insert(reviewsinfoVO);

		return reviewsinfoVO;
	}

	public ReviewsInfoVO updateReviewsInfo(
			String movie_no,
			String reviews_title,
			String reviews_auther,
			java.sql.Date reviews_times,
			String reviews_con,
			byte[] reviews_pic) {

		ReviewsInfoVO reviewsinfoVO = new ReviewsInfoVO();

		reviewsinfoVO.setMovie_no(movie_no);
		reviewsinfoVO.setReviews_title(reviews_title);
		reviewsinfoVO.setReviews_auther(reviews_auther);
		reviewsinfoVO.setReviews_times(reviews_times);
		reviewsinfoVO.setReviews_con(reviews_con);
		reviewsinfoVO.setReviews_pic(reviews_pic);
		dao.update(reviewsinfoVO);

		return reviewsinfoVO;
	}

	public void deleteReviewsInfo(Integer reviews_no) {
		dao.delete(reviews_no);
	}

	public ReviewsInfoVO getOneReviewsInfo(Integer reviews_no) {
		return dao.findByPrimaryKey(reviews_no);
	}

	public List<ReviewsInfoVO> getAll() {
		return dao.getAll();
	}

}
