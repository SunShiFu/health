package com.shiyanlou.lesson10.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiyanlou.lesson10.domain.Food;
import com.shiyanlou.lesson10.domain.PaginationObject;
import com.shiyanlou.lesson10.domain.ResultObject;
import com.shiyanlou.lesson10.service.FoodService;

@RestController
@RequestMapping("api/v1/food")
public class FoodController {

	@Autowired
	private FoodService foodService;
	
	@GetMapping("get")
	public ResultObject getFoodById(@RequestParam int id) {
		Food food = foodService.getFoodById(id);
		ResultObject resultObject = new ResultObject(0, "success", food);
		return resultObject;
	}
	
	@PostMapping("add")
	public ResultObject insertFood(Food food) {
		int modifyId = foodService.insertFood(food);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject(0, "success", map);
		return resultObject;
	}
	
	@GetMapping("list")
	public ResultObject getAllFood(@RequestParam int pageNum, @RequestParam int pageSize) {
		PaginationObject paginationObj = foodService.getAllFood(pageNum, pageSize);
		ResultObject resultObject = new ResultObject(0, "success", paginationObj);

		return resultObject;
	}
	
	@DeleteMapping("delete")
	public ResultObject deleteUser(@RequestParam int id) {
		int modifyId = foodService.deleteFood(id);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject(0, "success", map);
		return resultObject;
	}
	
	@PutMapping("edit")
	public ResultObject updateUser(Food food) {
		int modifyId = foodService.updateFood(food);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("modifyId", modifyId);
		ResultObject resultObject = new ResultObject(0, "success", map);
		return resultObject;
	}
}
