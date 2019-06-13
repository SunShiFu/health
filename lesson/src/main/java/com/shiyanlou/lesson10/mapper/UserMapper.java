package com.shiyanlou.lesson10.mapper;

import java.util.List;

import com.shiyanlou.lesson10.domain.User;

public interface UserMapper {

	/**
	 * 
	 * @param id
	 * @return
	 */
	User getById(int id);
	
	/**
	 * 
	 * @return
	 */
	List<User> getAll();
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	int insert(User user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	int update(User user);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	int delete(int id);
	
}
