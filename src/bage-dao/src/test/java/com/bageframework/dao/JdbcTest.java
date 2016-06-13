package com.bageframework.dao;

import java.util.List;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.bageframework.model.User;
import com.bageframework.util.UUIDGenerator;

@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class JdbcTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void testAdd() {
		for (int i = 0; i < 100; i++) {
			User user = new User();
			user.setUserId(UUIDGenerator.getUUID());
			user.setUsername("username-" + i);
			user.setNickname("nickname-" + i);
			user.setAge(RandomUtils.nextInt(50));
			user.setMoney(RandomUtils.nextInt(1000000));
			userDao.add(user);
		}
	}

	@Test
	public void testGet() {
		User user = userDao.get("752e6720e92b4feda99b0ba5059b31cb");
		System.out.println(user.getUsername() + ":" + user.getNickname());
	}

	@Test
	public void testUpdate() {
		User user = userDao.get("752e6720e92b4feda99b0ba5059b31cb");
		String nickname = "nickname:" + System.currentTimeMillis();
		user.setNickname(nickname);
		userDao.update(user);
		user = userDao.get("752e6720e92b4feda99b0ba5059b31cb");
		Assert.assertEquals(user.getNickname(), nickname);
		System.out.println("nickname:" + user.getNickname());
	}

	@Test
	public void testDelete() {
		User user = userDao.get("752e6720e92b4feda99b0ba5059b31cb");
		Assert.assertNotNull(user);
		userDao.delete(user.getUserId());
		user = userDao.get("752e6720e92b4feda99b0ba5059b31cb");
		Assert.assertNull(user);
	}

	@Test
	public void testGetList() {
		List<User> list = userDao.getList(0, 10);
		for (User user : list) {
			System.out.println("nickname:" + user.getNickname());
		}
	}

	@Test
	public void testGetListWithParentId() {
		int parentId = 1;
		List<User> list = userDao.getList(parentId, 0, 10);
		for (User user : list) {
			System.out.println("nickname:" + user.getNickname());
		}
	}
}
