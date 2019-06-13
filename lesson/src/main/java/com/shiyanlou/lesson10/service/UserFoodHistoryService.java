package com.shiyanlou.lesson10.service;

import java.util.List;

import com.shiyanlou.lesson10.domain.UserEverydayFoodSituation;
import com.shiyanlou.lesson10.domain.UserFoodHistory;


public interface UserFoodHistoryService {

	public int insertUserFoodHistory(UserFoodHistory userFoodHistory);
	
	public List<UserEverydayFoodSituation> getAllUserFoodHistory(int userId);
}
