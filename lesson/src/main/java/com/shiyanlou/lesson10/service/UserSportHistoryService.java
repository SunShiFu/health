package com.shiyanlou.lesson10.service;

import java.util.List;

import com.shiyanlou.lesson10.domain.UserEverydaySportSituation;
import com.shiyanlou.lesson10.domain.UserSportHistory;

public interface UserSportHistoryService {

	public int insertUserSportHistory(UserSportHistory userSportHistory);
	
	public List<UserEverydaySportSituation> getAllUserSportHistory(int userId);
}
