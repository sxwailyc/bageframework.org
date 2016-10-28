package com.bageframework.youzhi.web.vo;

import java.util.Date;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class MenuVO {

	private Integer id;

	private String name;

	private Integer parentId;

	private Date createdTime;

	private Integer sort;

	private Integer path;

	private Integer isLeaf;

	private Integer isBtn;

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

	public Integer getPath() {
		return path;
	}

	public void setPath(Integer path) {
		this.path = path;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getIsBtn() {
		return isBtn;
	}

	public void setIsBtn(Integer isBtn) {
		this.isBtn = isBtn;
	}

}