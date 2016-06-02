package com.bageframework.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bageframework.model.User;

@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class JdbcTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void testAdd() {
		User user = new User();
		user.setUserId("123");
		user.setUsername("username123");
		user.setNickname("nickname123");
		userDao.add(user);
	}

	@Test
	public void testGet() {
		User user = userDao.get("123");
		System.out.println(user.getUsername() + ":" + user.getNickname());
	}

	@Test
	public void testUpdate() {
		User user = userDao.get("123");
		String nickname = "nickname:" + System.currentTimeMillis();
		user.setNickname(nickname);
		userDao.update(user);
		user = userDao.get("123");
		Assert.assertEquals(user.getNickname(), nickname);
		System.out.println("nickname:" + user.getNickname());
	}
}
