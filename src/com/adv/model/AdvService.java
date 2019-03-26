package com.adv.model;

import java.sql.Timestamp;
import java.util.List;

public class AdvService {

	private AdvDAO_interface dao;

	public AdvService() {
		dao = new AdvDAOImpl();
	}

	public AdvVO addAdv(String ad_name, byte[] ad_pic, String ad_cont, Timestamp ad_start, Timestamp ad_end, Integer ad_type) {

		AdvVO advVO = new AdvVO();

		advVO.setAd_name(ad_name);
		advVO.setAd_pic(ad_pic);
		advVO.setAd_cont(ad_cont);
		advVO.setAd_start(ad_start);
		advVO.setAd_end(ad_end);
		advVO.setAd_type(ad_type);
		dao.insert(advVO);

		return advVO;
	}

	public AdvVO updateAdv(String ad_no, String ad_name, byte[] ad_pic, String ad_cont, Timestamp ad_start, Timestamp ad_end, Integer ad_type) {

		AdvVO advVO = new AdvVO();
		advVO.setAd_no(ad_no);
		advVO.setAd_name(ad_name);
		advVO.setAd_pic(ad_pic);
		advVO.setAd_cont(ad_cont);
		advVO.setAd_start(ad_start);
		advVO.setAd_end(ad_end);
		advVO.setAd_type(ad_type);
		dao.update(advVO);

		return advVO;
	}

	public void deleteAdv(String cinema_no) {
		dao.delete(cinema_no);
	}

	public AdvVO getOneAdv(String cinema_no) {
		return dao.findByPrimaryKey(cinema_no);
	}

	public List<AdvVO> getAll() {
		return dao.getAll();
	}

}
