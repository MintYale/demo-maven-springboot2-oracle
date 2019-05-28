package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.DemoUser;

@Mapper
public interface DemoUserDao {
	/**
	 * 用户数据新增
	 */
	@Insert("insert into demo_user(id,name,age) values (#{id},#{name},#{age})")
	void addUser(DemoUser user);

	/**
	 * 用户数据修改
	 */
	@Update("update demo_user set name=#{name},age=#{age} where id=#{id}")
	void updateUser(DemoUser user);

	/**
	 * 用户数据删除
	 */
	@Delete("delete from demo_user where id=#{id}")
	void deleteUser(String id);

	/**
	 * 根据用户名称查询用户信息
	 *
	 */
	@Select("SELECT id,name,age FROM demo_user where name=#{userName}")
	DemoUser findByName(@Param("userName") String userName);

	/**
	 * 查询所有
	 */
	@Select("SELECT id,name,age FROM demo_user")
	List<DemoUser> findAll();

}
