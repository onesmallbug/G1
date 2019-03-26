package com.newsinfo.model;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moviegenre.model.MovieGenreDAO;
import com.moviegenre.model.MovieGenreService;
import com.moviegenre.model.MovieGenreVO;
import com.movieinfo.model.MovieInfoVO;

/**
 * Servlet implementation class testNewsInfo
 */
@WebServlet("/testNewsInfo")
public class testNewsInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		NewsInfoService service = new NewsInfoService();
		NewsInfoVO vo = new NewsInfoVO();
		NewsInfoDAO dao = new NewsInfoDAO();
		
		
//		private Integer news_no;
//		private Integer movie_no;
//		private String news_title;
//		private String news_auther;
//		private Date news_times;
//		private String news_con;
//		private byte[] news_pic;
		
//		vo.setNews_no(16);
//		vo.setMovie_no(2);
//		vo.setNews_title("這是update測試");
//		vo.setNews_auther("作者測試");
//		vo.setNews_times(java.sql.Date.valueOf("1991-10-09"));
//		vo.setNews_con("con測試");
//		vo.setNews_pic(null);
		
		dao.findByPrimaryKey(16);
		
		System.out.println("1111");
		
		List<NewsInfoVO> list = service.getAll();
		for(NewsInfoVO a:list) {
			System.out.println(a.getNews_no());
		}
		
		
		
	}
    


}
