package com.shiyanlou.lesson10.domain;

import java.sql.Date;
import java.util.List;

public class UserEverydaySportSituation {

	private int userId;
	private Date collectDate;
	private int sumConsumeEnergy;
	private List<UserSportHistory> userSportHistories;
	
	public UserEverydaySportSituation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserEverydaySportSituation(int userId, Date collectDate, int sumConsumeEnergy,
			List<UserSportHistory> userSportHistories) {
		super();
		this.userId = userId;
		this.collectDate = collectDate;
		this.sumConsumeEnergy = sumConsumeEnergy;
		this.userSportHistories = userSportHistories;
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

	public int getSumConsumeEnergy() {
		return sumConsumeEnergy;
	}

	public void setSumConsumeEnergy(int sumConsumeEnergy) {
		this.sumConsumeEnergy = sumConsumeEnergy;
	}

	public List<UserSportHistory> getUserSportHistories() {
		return userSportHistories;
	}

	public void setUserSportHistories(List<UserSportHistory> userSportHistories) {
		this.userSportHistories = userSportHistories;
	}

	@Override
	public String toString() {
		return "UserEverydaySportSituation [userId=" + userId + ", collectDate=" + collectDate + ", sumConsumeEnergy="
				+ sumConsumeEnergy + ", userSportHistories=" + userSportHistories + "]";
	}
}
