package com.shiyanlou.lesson10.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shiyanlou.lesson10.domain.Food;

@Mapper
public interface FoodMapper {
	int insert(Food food);
	
	Food getById(int id);
	
	List<Food> getAll();
		
	int update(Food food);
	
	int delete(int id);
}
