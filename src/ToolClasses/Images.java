package ToolClasses;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.movieinfo.model.*;

@WebServlet("/ToolClasses/Images")
public class Images extends HttpServlet {
	private static final long serialVersionUID = -5366057512615309761L;


	@Override
	public void init() throws ServletException {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		String action = req.getParameter("action");
		
		if("movie_pic".equals(action)) {
				
			MovieInfoService movieInfoService = new MovieInfoService();
			String movie_no = req.getParameter("movie_no");
			MovieInfoVO movieInfoVO = movieInfoService.getOneMovieInfo(movie_no);
			byte[] movie_pic = movieInfoVO.getMovie_pic();
			out.write(movie_pic);
			out.flush();
				
		}
	}	
}
