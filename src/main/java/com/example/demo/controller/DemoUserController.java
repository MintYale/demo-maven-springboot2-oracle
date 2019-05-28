package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.DemoUser;
import com.example.demo.service.DemoUserService;

@RestController
@RequestMapping(value = "/api/user")
public class DemoUserController {
	
	@Autowired
	private DemoUserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public boolean addUser(DemoUser user) {
		System.out.println("开始新增...");
		return userService.addUser(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public boolean updateUser(DemoUser user) {
		System.out.println("开始更新...");
		return userService.updateUser(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public boolean delete(@RequestParam(value = "userId", required = true) String userId) {
		System.out.println("开始删除...");
		return userService.deleteUser(userId);
	}

	/**
	 * http://localhost:9090/api/user/user?userName=张三
	 * @date May 28, 2019
	 * @description 
	 * @param userName
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public DemoUser findByUserName(@RequestParam(value = "userName", required = true) String userName) {
		System.out.println("开始查询...");
		return userService.findUserByName(userName);
	}

	/**
	 * http://localhost:9090/api/user/userAll
	 * @date May 28, 2019
	 * @description 
	 * @return
	 */
	@RequestMapping(value = "/userAll", method = RequestMethod.GET)
	public List<DemoUser> findByUserAge() {
		System.out.println("开始查询所有数据...");
		return userService.findAll();
	}
}
