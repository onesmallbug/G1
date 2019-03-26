package com.cinema.model;

import java.util.List;

public class CinemaService {

	private CinemaDAO_interface dao;

	public CinemaService() {
		dao = new CinemaDAOImpl();
	}

	public CinemaVO addCin(String cinema_type, Integer cinema_size, String cinema_name, Integer cinema_correct, String cinema_status) {

		CinemaVO cinemaVO = new CinemaVO();

		cinemaVO.setCinema_type(cinema_type);
		cinemaVO.setCinema_size(cinema_size);
		cinemaVO.setCinema_name(cinema_name);
		cinemaVO.setCinema_correct(cinema_correct);
		cinemaVO.setCinema_status(cinema_status);
		dao.insert(cinemaVO);

		return cinemaVO;
	}

	public CinemaVO updateCin(String cinema_no, String cinema_type, Integer cinema_size, String cinema_name, Integer cinema_correct, String cinema_status) {

		CinemaVO cinemaVO = new CinemaVO();
		cinemaVO.setCinema_no(cinema_no);
		cinemaVO.setCinema_type(cinema_type);
		cinemaVO.setCinema_size(cinema_size);
		cinemaVO.setCinema_name(cinema_name);
		cinemaVO.setCinema_correct(cinema_correct);
		cinemaVO.setCinema_status(cinema_status);
		dao.update(cinemaVO);

		return cinemaVO;
	}

	public void deleteCin(String cinema_no) {
		dao.delete(cinema_no);
	}

	public CinemaVO getOneCin(String cinema_no) {
		return dao.findByPrimaryKey(cinema_no);
	}

	public List<CinemaVO> getAll() {
		return dao.getAll();
	}

}
