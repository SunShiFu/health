package com.shiyanlou.lesson10.domain;

import java.sql.Date;
import java.util.List;

public class UserEverydayFoodSituation {

	private int userId;
	private Date collectDate;
	private int sumFoodEnergy;
	private List<UserFoodHistory> userFoodHistories;
	
	public UserEverydayFoodSituation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEverydayFoodSituation(int userId, Date collectDate, int sumFoodEnergy,
			List<UserFoodHistory> userFoodHistories) {
		super();
		this.userId = userId;
		this.collectDate = collectDate;
		this.sumFoodEnergy = sumFoodEnergy;
		this.userFoodHistories = userFoodHistories;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	public int getSumFoodEnergy() {
		return sumFoodEnergy;
	}

	public void setSumFoodEnergy(int sumFoodEnergy) {
		this.sumFoodEnergy = sumFoodEnergy;
	}

	public List<UserFoodHistory> getUserFoodHistories() {
		return userFoodHistories;
	}

	public void setUserFoodHistories(List<UserFoodHistory> userFoodHistories) {
		this.userFoodHistories = userFoodHistories;
	}

	@Override
	public String toString() {
		return "UserEverydayFoodSituation [userId=" + userId + ", collectDate=" + collectDate + ", sumFoodEnergy="
				+ sumFoodEnergy + ", userFoodHistories=" + userFoodHistories + "]";
	}
}
