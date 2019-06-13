package com.shiyanlou.lesson10.mapper;

import java.util.List;

import com.shiyanlou.lesson10.domain.Sport;

public interface SportMapper {
	int insert(Sport drug);
	
	Sport getById(int id);
	
	List<Sport> getAll();
	
	int update(Sport drug);
	
	int delete(int id);
}
