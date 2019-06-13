package com.shiyanlou.lesson10.service;

import com.shiyanlou.lesson10.domain.PaginationObject;
import com.shiyanlou.lesson10.domain.Sport;

public interface SportService {
	
	public Sport getSportById(int id);
	
	public int insertSport(Sport sport);
	
	public PaginationObject getAllSport(int pageNum, int pageSize);
	
	public int updateSport(Sport sport); 
	
	public int deleteSport(int id);
}
