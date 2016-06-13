package com.bageframework.dao.beans;

import java.util.List;

/**
 * 
 * @author shixiangwen03@gmail.com
 * 
 * @param <BEAN>
 */
public class Page<BEAN> {

	private List<BEAN> data;

	private int count;

	public Page(List<BEAN> data, int count) {
		this.data = data;
		this.count = count;
	}

	public List<BEAN> getData() {
		return data;
	}

	public void setData(List<BEAN> data) {
		this.data = data;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
