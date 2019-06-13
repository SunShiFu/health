package com.shiyanlou.lesson10.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiyanlou.lesson10.domain.EnergyDate;
import com.shiyanlou.lesson10.domain.UserIndex;
import com.shiyanlou.lesson10.domain.UserRelationship;
import com.shiyanlou.lesson10.mapper.UserFoodHistoryMapper;
import com.shiyanlou.lesson10.mapper.UserIndexMapper;
import com.shiyanlou.lesson10.mapper.UserSportHistoryMapper;
import com.shiyanlou.lesson10.service.UserRelationshipService;

@Service
public class UserRelationshipServiceImpl implements UserRelationshipService {
	
	@Autowired
	private UserFoodHistoryMapper userFoodHistoryMapper;
	
	@Autowired
	private UserSportHistoryMapper userSportHistoryMapper;
	
	@Autowired
	private UserIndexMapper userIndexMapper;
	
	public UserRelationship getRelationship(int userId) {
		UserRelationship relationship = new UserRelationship();
		List<EnergyDate> foodEnergies = userFoodHistoryMapper.getSumFoodEnergy(userId);
		List<EnergyDate> sportEnergyDates = userSportHistoryMapper.getSumConsumeEnergy(userId);				
		List<UserIndex> userIndexs = userIndexMapper.getById(userId);
		Map<Integer, List<UserIndex>> userIndexes = new HashMap<Integer, List<UserIndex>>();
		
		for(UserIndex userIndex: userIndexs) {
			int indexType = userIndex.getIndexType();
			if (userIndexes.containsKey(indexType)) {
				List<UserIndex> userIndexs2 = userIndexes.get(indexType);
				userIndexs2.add(userIndex);
			} else {
				List<UserIndex> userIndexs2 = new ArrayList<UserIndex>();
				userIndexs2.add(userIndex);
				userIndexes.put(indexType, userIndexs2);
			}
		}
		
		relationship.setUserId(userId);
		relationship.setFoodEnergies(foodEnergies);
		relationship.setSportEnergies(sportEnergyDates);
		relationship.setUserIndexes(userIndexes);
		return relationship;
	}

}
