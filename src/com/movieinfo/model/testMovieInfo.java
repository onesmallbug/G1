package com.movieinfo.model;

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

@WebServlet("/testMovieInfo")
public class testMovieInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		MovieInfoService service = new MovieInfoService();
		MovieInfoVO vo = new MovieInfoVO();
		MovieInfoDAO dao = new MovieInfoDAO();
		
		
//		private Integer movie_no;
//		private Integer genre_no;
//		private String movie_name;
//		private byte[] movie_level;
//		private String director;
//		private String movie_cast;
//		private String movie_intro;
//		private String movie_length;
//		private byte[] movie_trailer;
//		private byte[] movie_pic;
//		private Date movie_in;
//		private Date movie_out;
//		private Integer movie_count;
//		private Integer movie_exp;
//		private Integer movie_noexp;
//		private Integer movie_touch;
//		private Integer movie_ticket;
		
		vo.setGenre_no(3);
		vo.setMovie_name("testttt");
		vo.setMovie_score(1.8);
		vo.setMovie_level(null);
		vo.setMovie_director("me");
		vo.setMovie_cast("123");
		vo.setMovie_intro("111");
		vo.setMovie_length("111");
		vo.setMovie_trailer(null);
		vo.setMovie_pic(null);
		vo.setMovie_in(java.sql.Date.valueOf("1991-10-01"));
		vo.setMovie_out(java.sql.Date.valueOf("1991-10-02"));
		vo.setMovie_count(2);
		vo.setMovie_exp(2);
		vo.setMovie_noexp(3);
		vo.setMovie_touch(200);
		vo.setMovie_ticket(50);
		dao.insert(vo);
		
//		dao.update(vo);
//		vo = dao.findByPrimaryKey(2);
//		System.out.println(vo.getMovie_no());
//		System.out.println(vo.getGenre_no());
//		System.out.println(vo.getMovie_name());
//		System.out.println(vo.getMovie_level());
//		System.out.println(vo.getMovie_director());
//		System.out.println(vo.getMovie_cast());
//		System.out.println(vo.getMovie_intro());
//		System.out.println(vo.getMovie_length());
//		System.out.println(vo.getMovie_pic());
//		System.out.println(vo.getMovie_trailer());
//		System.out.println(vo.getMovie_in());
//		System.out.println(vo.getMovie_out());
//		System.out.println(vo.getMovie_count());
//		System.out.println(vo.getMovie_exp());
//		System.out.println(vo.getMovie_noexp());
//		System.out.println(vo.getMovie_touch());
//		System.out.println(vo.getMovie_ticket());
		
		
//		Date aa = java.sql.Date.valueOf("2011-09-08");
//		Date bb = java.sql.Date.valueOf("2019-09-08");
//		System.out.println(aa);
//		System.out.println(bb);
//		List<MovieInfoVO> list = service.getAllByScore(aa, bb);
//		for(MovieInfoVO a:list) {
//			System.out.println("電影名稱:"+a.getMovie_name());
//			System.out.println(a.getMovie_score());
//		}
	}
}
