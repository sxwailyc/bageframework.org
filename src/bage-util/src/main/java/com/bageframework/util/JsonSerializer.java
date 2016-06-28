package com.bageframework.util;

import java.util.Date;

import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonSerializer {

	/**
	 * json 序列化
	 * 
	 * @param obj
	 * @return
	 */
	public static String serialize(Object obj) {
		return com.alibaba.fastjson.JSON.toJSONString(obj, SerializerFeature.WriteClassName);
	}

	/**
	 * json反序列化
	 * 
	 * @param s
	 * @return
	 */
	public static Object deserialize(String s) {
		return com.alibaba.fastjson.JSON.parse(s);
	}

	public static void main(String[] args) {
		User user = new User();
		user.setUsername("username");
		String text = serialize(user);
		User user2 = (User) deserialize(text);
		System.out.println(user2.getUsername());

		System.out.println(serialize("test"));

		System.out.println(deserialize(serialize("test")));

		System.out.println(deserialize(serialize(1)));

		System.out.println(deserialize(serialize(new Date())));
	}
}

class User {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
