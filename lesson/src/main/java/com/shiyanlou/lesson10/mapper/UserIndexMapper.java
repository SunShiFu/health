package com.shiyanlou.lesson10.mapper;

import java.util.List;

import com.shiyanlou.lesson10.domain.UserIndex;

public interface UserIndexMapper {

	List<UserIndex> getById(int userId);
}
