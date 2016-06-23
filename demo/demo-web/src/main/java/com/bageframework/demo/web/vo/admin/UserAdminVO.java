package com.bageframework.demo.web.vo.admin;

import org.springframework.beans.BeanUtils;

import com.bageframework.demo.web.model.User;

public class UserAdminVO {

	private int id;

	private String userId;

	private String username;

	private String nickname;

	private int age;

	private int money;

	private int sex;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static UserAdminVO create(User user) {
		UserAdminVO vo = new UserAdminVO();
		BeanUtils.copyProperties(user, vo);
		return vo;
	}

}
