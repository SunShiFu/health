package com.shiyanlou.lesson10.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiyanlou.lesson10.domain.ResultObject;
import com.shiyanlou.lesson10.domain.UserEverydaySportSituation;
import com.shiyanlou.lesson10.domain.UserSportHistory;
import com.shiyanlou.lesson10.service.UserSportHistoryService;

@RestController
@RequestMapping("/api/v1/user_sport_history")
public class UserSportHistoryController {

	@Autowired
	private UserSportHistoryService userSportHistoryService;
	
	@PostMapping("add")
	public ResultObject insertUserSportHistory(@RequestBody UserSportHistory userSportHistory) {
		int modifyId = userSportHistoryService.insertUserSportHistory(userSportHistory);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject();
		resultObject.setResult(map);
		if (modifyId == 1) {
			resultObject.setCode(0);
			resultObject.setMsg("success");
		} else {
			resultObject.setCode(-1);
			resultObject.setMsg("fail");
		}
		
		return resultObject;
	}
	
	@GetMapping("get")
	public ResultObject getAllUserDrugHistory(@RequestParam int userId) {
		List<UserEverydaySportSituation> userEverydaySportSituations = userSportHistoryService.getAllUserSportHistory(userId);
		ResultObject resultObject = new ResultObject(0, "success", userEverydaySportSituations);

		return resultObject;
	}
	
}
