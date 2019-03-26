package com.newsinfo.model;

import java.util.*;

public interface NewsInfoDAO_interface {
	public void insert(NewsInfoVO newsinfoVO);
	public void update(NewsInfoVO newsinfoVO);
	public void delete(Integer news_no);
	public NewsInfoVO findByPrimaryKey(Integer news_no);
	public List<NewsInfoVO> getAll();
}
