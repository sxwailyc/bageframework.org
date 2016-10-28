package com.bageframework.youzhi.web.model;

import com.bageframework.dao.annotation.OrderAsc;
import com.bageframework.dao.annotation.OrderDesc;
import com.bageframework.dao.annotation.ParentID;
import com.bageframework.dao.annotation.PrimaryKey;

public class User {

	private int id;

	@PrimaryKey
	private String userId;

	private String username;

	private String nickname;

	@OrderAsc(index = 2)
	private int age;

	@OrderDesc(index = 3)
	private int money;

	@ParentID
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

}
