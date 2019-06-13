package com.shiyanlou.lesson10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shiyanlou.lesson10.domain.ResultObject;
import com.shiyanlou.lesson10.domain.UserRelationship;
import com.shiyanlou.lesson10.service.UserRelationshipService;

@RestController
@RequestMapping("api/v1/relationship")
public class UserRelationshipController {

	@Autowired
	private UserRelationshipService relationshipService;
	
	@RequestMapping("get")
	public ResultObject getRelationship(@RequestParam int id) {
		UserRelationship relationship = relationshipService.getRelationship(id);
		ResultObject resultObject = new ResultObject(0, "success", relationship);
		return resultObject;
	}
}
