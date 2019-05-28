package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DemoUserDao;
import com.example.demo.entity.DemoUser;
import com.example.demo.service.DemoUserService;

@Service
public class DemoUserServiceImpl implements DemoUserService {

	@Autowired
	private DemoUserDao userDao;

	@Override
	public boolean addUser(DemoUser user) {
		boolean flag = false;
		try {
			userDao.addUser(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean updateUser(DemoUser user) {
		boolean flag = false;
		try {
			userDao.updateUser(user);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean deleteUser(String id) {
		boolean flag = false;
		try {
			userDao.deleteUser(id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public DemoUser findUserByName(String userName) {
		return userDao.findByName(userName);
	}

	@Override
	public List<DemoUser> findAll() {
		return userDao.findAll();
	}

}
