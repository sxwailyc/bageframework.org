package com.bageframework.authority.model;

import java.util.Date;

import com.bageframework.data.annotation.IgnoreUpdate;
import com.bageframework.data.annotation.OrderAsc;
import com.bageframework.data.annotation.ParentID;
import com.bageframework.data.annotation.PrimaryKey;

public class Menu {

	@PrimaryKey
	private Integer id;

	private String name;

	@ParentID
	@IgnoreUpdate
	private Integer parentId;

	@IgnoreUpdate
	private Date createdTime;

	@OrderAsc
	private Integer sort;

	private String path;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
