package com.movieticke.model;

import java.util.List;


public class MovieticketService {
	
	private MovieticketDAO_interface dao;
	
	public MovieticketService() {
		dao = new MovieticketDAO();
	}
	
	public MovieticketVO addMovieticket(String order_no, String ti_no, byte[] mt_qr,
			Integer mt_admission,String mt_share) {
		
		MovieticketVO movieticketVO = new MovieticketVO();
		
		movieticketVO.setOrder_no(order_no);
		movieticketVO.setTi_no(ti_no);
		movieticketVO.setMt_qr(mt_qr);
		movieticketVO.setMt_admission(mt_admission);
		movieticketVO.setMt_share(mt_share);
		dao.insert(movieticketVO);
		
		
		
		return movieticketVO;
	}
	
	public MovieticketVO updateMovieticket(String mt_no, String order_no, String ti_no, byte[] mt_qr,
			Integer mt_admission,String mt_share) {
		
		MovieticketVO movieticketVO = new MovieticketVO();
		
		movieticketVO.setMt_no(mt_no);
		movieticketVO.setOrder_no(order_no);
		movieticketVO.setTi_no(ti_no);
		movieticketVO.setMt_qr(mt_qr);
		movieticketVO.setMt_admission(mt_admission);
		movieticketVO.setMt_share(mt_share);
		dao.update(movieticketVO);
		
		
		
		return movieticketVO;
	}
	
	public void deleteMovieticket(String mt_no) {
		dao.delete(mt_no);
	}
	
	public MovieticketVO getOneMovieticket(String mt_no) {
		return dao.findByPrimaryKey(mt_no);
	}

	public List<MovieticketVO> getAll() {
		return dao.getAll();
	}
	
	public List<MovieticketVO> findByOrder_no(String order_no){
		return dao.findByOrder_no(order_no);
		
	}
	
	
	

}
