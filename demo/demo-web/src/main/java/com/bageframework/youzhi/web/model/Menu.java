package com.bageframework.youzhi.web.model;

import java.util.Date;

import com.bageframework.dao.annotation.IgnoreUpdate;
import com.bageframework.dao.annotation.OrderAsc;
import com.bageframework.dao.annotation.ParentID;
import com.bageframework.dao.annotation.PrimaryKey;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
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

	private String style;

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

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}