package com.shiyanlou.lesson10.service;

import com.shiyanlou.lesson10.domain.Food;
import com.shiyanlou.lesson10.domain.PaginationObject;

public interface FoodService {
	
	public Food getFoodById(int id);
	
	public int insertFood(Food food);
	
	public PaginationObject getAllFood(int pageNum, int pageSize);
	
	public int updateFood(Food food); 
	
	public int deleteFood(int id);
}
