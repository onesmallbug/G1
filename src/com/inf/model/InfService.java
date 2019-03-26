package com.inf.model;

import java.sql.Date;
import java.util.List;

public class InfService {

	private InfDAO_interface dao;

	public InfService() {
		dao = new InfDAOImpl();
	}

	public InfVO addInf(String info_content, String info_desc, Date info_date) {

		InfVO infVO = new InfVO();

		infVO.setInfo_content(info_content);
		infVO.setInfo_desc(info_desc);
		infVO.setInfo_date(info_date);
		dao.insert(infVO);

		return infVO;
	}

	public InfVO updateInf(String info_no, String info_content, String info_desc, Date info_date) {

		InfVO infVO = new InfVO();
		infVO.setInfo_no((info_no));
		infVO.setInfo_content(info_content);
		infVO.setInfo_desc(info_desc);
		infVO.setInfo_date(info_date);
		dao.update(infVO);

		return infVO;
	}

	public void deleteInf(String info_no) {
		dao.delete(info_no);
	}

	public InfVO getOneInf(String info_no) {
		return dao.findByPrimaryKey(info_no);
	}

	public List<InfVO> getAll() {
		return dao.getAll();
	}

}
