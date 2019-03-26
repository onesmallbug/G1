package com.sessions.model;

import java.util.Collections;
import java.util.List;

public class SessionsService {
	
	private SessionsDAO_interface dao;
	
	public SessionsService() {
		dao = new SessionsDAOImpl();
	}
	
	public SessionsVO addSes(
			String movie_no,
			String cinema_no, 
			java.sql.Timestamp sessions_start,
			String sessions_status, 
			Integer sessions_remaining) 
	{
		SessionsVO sessionsVO = new SessionsVO();

		sessionsVO.setMovie_no(movie_no);
		sessionsVO.setCinema_no(cinema_no);
		sessionsVO.setSessions_start(sessions_start);
		sessionsVO.setSessions_status(sessions_status);
		sessionsVO.setSessions_remaining(sessions_remaining);
		dao.insert(sessionsVO);

		return sessionsVO;
	}

	public SessionsVO updateSes(
			String sessions_no, 
			String movie_no, 
			String cinema_no, 
			java.sql.Timestamp sessions_start,
			String sessions_status, 
			Integer sessions_remaining) 
	{
		SessionsVO sessionsVO = new SessionsVO();
		sessionsVO.setSessions_no(sessions_no);
		sessionsVO.setMovie_no(movie_no);
		sessionsVO.setCinema_no(cinema_no);
		sessionsVO.setSessions_start(sessions_start);
		sessionsVO.setSessions_status(sessions_status);
		sessionsVO.setSessions_remaining(sessions_remaining);
		dao.update(sessionsVO);

		return sessionsVO;
	}

	public void deleteSes(String sessions_no) {
		dao.delete(sessions_no);
	}

	public SessionsVO getOneSes(String sessions_no) {
		return dao.findByPrimaryKey(sessions_no);
	}

	public List<SessionsVO> getAll() {
		List<SessionsVO> list = dao.getAll();
		Collections.sort(list);
		return list;
	} 

}
