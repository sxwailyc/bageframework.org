package com.bageframework.coder.model;

import java.util.Date;

import com.bageframework.data.annotation.AutoDate;
import com.bageframework.data.annotation.AutoUUID;
import com.bageframework.data.annotation.PrimaryKey;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class Metadata {

	@PrimaryKey
	@AutoUUID
	private String id;

	private String table;

	private String javaClass;

	private String remark;

	@AutoDate
	private Date createdTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getJavaClass() {
		return javaClass;
	}

	public void setJavaClass(String javaClass) {
		this.javaClass = javaClass;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}