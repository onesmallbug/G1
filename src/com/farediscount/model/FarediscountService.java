package com.farediscount.model;

import java.sql.Date;
import java.util.List;

public class FarediscountService {

	private FarediscountDAO_interface dao;

	public FarediscountService() {
		dao = new FarediscountDAOImpl();
	}

	public FarediscountVO addFar(String fd_narrative, String fd_name, Integer fd_offer, Date fd_start, Date fd_end,
			Integer fd_upper, Integer fd_lower, byte[] fd_blob) {

		FarediscountVO farediscountVO = new FarediscountVO();

		farediscountVO.setFd_narrative(fd_narrative);
		farediscountVO.setFd_name(fd_name);
		farediscountVO.setFd_offer(fd_offer);
		farediscountVO.setFd_start(fd_start);
		farediscountVO.setFd_end(fd_end);
		farediscountVO.setFd_upper(fd_upper);
		farediscountVO.setFd_lower(fd_lower);
		farediscountVO.setFd_blob(fd_blob);
		dao.insert(farediscountVO);

		return farediscountVO;
	}

	public FarediscountVO updateFar(String fd_no, String fd_narrative, String fd_name, Integer fd_offer, Date fd_start, Date fd_end,
			Integer fd_upper, Integer fd_lower, byte[] fd_blob) {

		FarediscountVO farediscountVO = new FarediscountVO();
		farediscountVO.setFd_no(fd_no);
		farediscountVO.setFd_narrative(fd_narrative);
		farediscountVO.setFd_name(fd_name);
		farediscountVO.setFd_offer(fd_offer);
		farediscountVO.setFd_start(fd_start);
		farediscountVO.setFd_end(fd_end);
		farediscountVO.setFd_upper(fd_upper);
		farediscountVO.setFd_lower(fd_lower);
		farediscountVO.setFd_blob(fd_blob);
		dao.update(farediscountVO);

		return farediscountVO;
	}

	public void deleteFar(String fd_no) {
		dao.delete(fd_no);
	}

	public FarediscountVO getOneFar(String fd_no) {
		return dao.findByPrimaryKey(fd_no);
	}

	public List<FarediscountVO> getAll() {
		return dao.getAll();
	}

}
