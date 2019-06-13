package com.shiyanlou.lesson10.domain;

import java.util.List;
import java.util.Map;

public class UserRelationship {

	private int userId;
	private List<EnergyDate> foodEnergies;
	private List<EnergyDate> sportEnergies;
	private Map<Integer, List<UserIndex>> userIndexes;
	public UserRelationship() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRelationship(int userId, List<EnergyDate> foodEnergies, List<EnergyDate> sportEnergies,
			Map<Integer, List<UserIndex>> userIndexes) {
		super();
		this.userId = userId;
		this.foodEnergies = foodEnergies;
		this.sportEnergies = sportEnergies;
		this.userIndexes = userIndexes;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<EnergyDate> getFoodEnergies() {
		return foodEnergies;
	}
	public void setFoodEnergies(List<EnergyDate> foodEnergies) {
		this.foodEnergies = foodEnergies;
	}
	public List<EnergyDate> getSportEnergies() {
		return sportEnergies;
	}
	public void setSportEnergies(List<EnergyDate> sportEnergies) {
		this.sportEnergies = sportEnergies;
	}
	public Map<Integer, List<UserIndex>> getUserIndexes() {
		return userIndexes;
	}
	public void setUserIndexes(Map<Integer, List<UserIndex>> userIndexes) {
		this.userIndexes = userIndexes;
	}
	@Override
	public String toString() {
		return "UserRelationship [userId=" + userId + ", foodEnergies=" + foodEnergies + ", sportEnergies="
				+ sportEnergies + ", userIndexes=" + userIndexes + "]";
	}
}
