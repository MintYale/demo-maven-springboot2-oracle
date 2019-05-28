package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.DemoUser;

public interface DemoUserService {
	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	boolean addUser(DemoUser user);

	/**
	 * 修改用户
	 * 
	 * @param user
	 * @return
	 */
	boolean updateUser(DemoUser user);

	/**
	 * 删除用户
	 * 
	 * @param userId
	 * @return
	 */
	boolean deleteUser(String userId);

	/**
	 * 根据用户名字查询用户信息
	 * 
	 * @param userName
	 */
	DemoUser findUserByName(String userName);

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	List<DemoUser> findAll();
}
