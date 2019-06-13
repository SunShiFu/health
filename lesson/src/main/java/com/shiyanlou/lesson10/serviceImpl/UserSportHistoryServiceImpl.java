package com.shiyanlou.lesson10.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiyanlou.lesson10.domain.UserEverydaySportSituation;
import com.shiyanlou.lesson10.domain.UserSportHistory;
import com.shiyanlou.lesson10.mapper.UserSportHistoryMapper;
import com.shiyanlou.lesson10.service.UserSportHistoryService;

@Service
public class UserSportHistoryServiceImpl implements UserSportHistoryService{

	@Autowired
	private UserSportHistoryMapper userSportHistoryMapper;
	
	public int insertUserSportHistory(UserSportHistory userSportHistory) {
		int modifyId = userSportHistoryMapper.insert(userSportHistory);
		return modifyId;
	}
	
	public List<UserEverydaySportSituation> getAllUserSportHistory(int userId) {
		List<UserSportHistory> userSportHistories = userSportHistoryMapper.getAll(userId);
		Map<Date, List<UserSportHistory>> userSportMap = new TreeMap<Date, List<UserSportHistory>>();
		for(UserSportHistory userSportHistory: userSportHistories) {
			Date cDate = userSportHistory.getCollectDate();
			if (userSportMap.containsKey(cDate)) {
				List<UserSportHistory> userSportList = userSportMap.get(cDate);
				userSportList.add(userSportHistory);
			} else {
				List<UserSportHistory> userSportList = new ArrayList<UserSportHistory>();
				userSportList.add(userSportHistory);
				userSportMap.put(cDate, userSportList);
			}
		}
		
		List<UserEverydaySportSituation> userEverydaySportSituations = new ArrayList<UserEverydaySportSituation>();
		
		for(Entry<Date, List<UserSportHistory>> entry: userSportMap.entrySet()) {
			UserEverydaySportSituation userEverydaySportSituation = new UserEverydaySportSituation();
			userEverydaySportSituation.setUserId(userId);
			userEverydaySportSituation.setCollectDate(entry.getKey());
			userEverydaySportSituation.setUserSportHistories(entry.getValue());
			
			int sumConsumeEnergy = 0;
			for(UserSportHistory userSportHistory: entry.getValue()) {
				sumConsumeEnergy += userSportHistory.getSportTime() * userSportHistory.getSport().getConsumeEnergy();
			}
			userEverydaySportSituation.setSumConsumeEnergy(sumConsumeEnergy);
			
			userEverydaySportSituations.add(userEverydaySportSituation);
		}
		
		return userEverydaySportSituations;
	}
}
