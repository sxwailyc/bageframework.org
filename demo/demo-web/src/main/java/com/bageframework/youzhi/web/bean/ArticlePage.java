package com.bageframework.demo.web.bean;

import java.util.ArrayList;
import java.util.List;

public class ArticlePage {

	private int total;

	private int totalPage;

	private int page;

	private String category;

	private List<Integer> pages = new ArrayList<Integer>();

	public ArticlePage(String category, int total, int page) {
		this.category = category;
		this.total = total;
		this.page = page;
		if (this.total % 10 == 0) {
			totalPage = this.total / 10;
		} else {
			totalPage = this.total / 10 + 1;
		}
		if (this.totalPage <= 10) {
			for (int i = 1; i <= this.totalPage; i++) {
				pages.add(i);
			}
		} else {
			int start;
			if (page - 5 < 1) {
				start = 1;
			} else {
				start = page - 5;
			}
			for (int i = start; i < 10; i++) {
				pages.add(i);
			}
		}
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

}
