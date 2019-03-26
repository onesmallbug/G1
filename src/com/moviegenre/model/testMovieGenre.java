package com.moviegenre.model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testMovieGenre")
public class testMovieGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MovieGenreService service = new MovieGenreService();
		MovieGenreVO vo = new MovieGenreVO();
		MovieGenreDAO dao = new MovieGenreDAO();
		
		vo.setGenre_name("Test");
		dao.insert(vo);
		
	
	
	
	}


}
