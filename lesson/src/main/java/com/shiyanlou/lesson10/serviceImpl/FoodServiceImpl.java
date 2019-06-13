package com.shiyanlou.lesson10.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiyanlou.lesson10.domain.Food;
import com.shiyanlou.lesson10.domain.PaginationObject;
import com.shiyanlou.lesson10.mapper.FoodMapper;
import com.shiyanlou.lesson10.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService{

	@Autowired
	private FoodMapper foodMapper;
	
	public Food getFoodById(int id) {
		Food food = foodMapper.getById(id);
		return food;
	}
	
	public int insertFood(Food food) {
		int modifyId = foodMapper.insert(food);
		return modifyId;
	}
	
	public PaginationObject getAllFood(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Food> foods = foodMapper.getAll();
		PageInfo<Food> appsPageInfo = new PageInfo<Food>(foods);
		long total = appsPageInfo.getTotal();
		PaginationObject paginationObject = new PaginationObject(foods, pageNum, pageSize, total);
		return paginationObject;
	}
	
	public int updateFood(Food food) {
		int modifyId = foodMapper.update(food);
		return modifyId;
	} 
	
	public int deleteFood(int id) {
		int modifyId = foodMapper.delete(id);
		return modifyId;
	}
}
