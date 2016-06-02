package com.bageframework.model;

import com.bageframework.dao.annotation.PrimaryKey;

public class User {

	@PrimaryKey
	private String userId;

	private String username;

	private String nickname;

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

}
