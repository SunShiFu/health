package com.shiyanlou.lesson10.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiyanlou.lesson10.domain.PaginationObject;
import com.shiyanlou.lesson10.domain.Sport;
import com.shiyanlou.lesson10.mapper.SportMapper;
import com.shiyanlou.lesson10.service.SportService;

@Service
public class SportServiceImpl implements SportService{

	@Autowired
	private SportMapper sportMapper;
	
	public Sport getSportById(int id) {
		Sport sport = sportMapper.getById(id);
		return sport;
	}
	
	public int insertSport(Sport sport) {
		int modifyId = sportMapper.insert(sport);
		return modifyId;
	}
	
	public PaginationObject getAllSport(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Sport> sports = sportMapper.getAll();
		PageInfo<Sport> appsPageInfo = new PageInfo<Sport>(sports);
		long total = appsPageInfo.getTotal();
		PaginationObject paginationObject = new PaginationObject(sports, pageNum, pageSize, total);
		return paginationObject;	}
	
	public int updateSport(Sport sport) {
		int modifyId = sportMapper.update(sport);
		return modifyId;
	} 
	
	public int deleteSport(int id) {
		int modifyId = sportMapper.delete(id);
		return modifyId;
	}
}
