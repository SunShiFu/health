package com.shiyanlou.lesson10.mapper;

import java.util.List;

import com.shiyanlou.lesson10.domain.EnergyDate;
import com.shiyanlou.lesson10.domain.UserSportHistory;

public interface UserSportHistoryMapper {

	public int insert(UserSportHistory userSportHistory);
	
	public List<UserSportHistory> getAll(int userId);
	
	public List<EnergyDate> getSumConsumeEnergy(int userId);
}
