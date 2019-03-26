package com.reviewsinfo.model;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newsinfo.model.NewsInfoDAO;
import com.newsinfo.model.NewsInfoService;
import com.newsinfo.model.NewsInfoVO;

@WebServlet("/testReviewsInfo")
public class testReviewsInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewsInfoService service = new ReviewsInfoService();
		ReviewsInfoVO vo = new ReviewsInfoVO();
		ReviewsInfoDAO dao = new ReviewsInfoDAO();
		
//		private Integer reviews_no;
//		private Integer movie_no;
//		private String reviews_title;
//		private String reviews_auther;
//		private Date reviews_times;
//		private String reviews_con;
//		private byte[] reviews_pic;
		
		
//		vo.setMovie_no(2);
//		vo.setReviews_title("2222222222222");
//		vo.setReviews_auther("auther2222");
//		vo.setReviews_times(java.sql.Date.valueOf("1991-10-09"));
//		vo.setReviews_con("111");
//		vo.setReviews_pic(null);
//		vo.setReviews_no(16);
		
		dao.findByPrimaryKey(16);
		System.out.println("111");
		
		List<ReviewsInfoVO> list = service.getAll();
		for(ReviewsInfoVO a:list) {
			System.out.println(a.getReviews_no());
		}
	}

}
