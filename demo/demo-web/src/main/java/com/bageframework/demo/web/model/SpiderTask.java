package com.bageframework.demo.web.model;

import com.bageframework.dao.annotation.AutoDate;
import java.util.Date;
import com.bageframework.dao.annotation.PrimaryKey;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class SpiderTask {

	@PrimaryKey
	private Integer id;

	private Integer type;

	private String param;

	@AutoDate
	private Date createdTime;

	@AutoDate
	private Date lastSpiderTime;

	private Integer status;

	private int categoryId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getLastSpiderTime() {
		return lastSpiderTime;
	}

	public void setLastSpiderTime(Date lastSpiderTime) {
		this.lastSpiderTime = lastSpiderTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	

}