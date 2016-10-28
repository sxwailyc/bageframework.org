package com.bageframework.youzhi.web.vo;

import com.bageframework.dao.annotation.AutoDate;
import com.bageframework.dao.annotation.PrimaryKey;
import com.bageframework.youzhi.web.model.Category;

import java.util.Date;

import org.springframework.beans.BeanUtils;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class CategoryVO {

	@PrimaryKey
	private Integer id;

	private String name;

	private String remark;

	private Integer sort;

	@AutoDate
	private Date createdTime;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public static CategoryVO create(Category bean) {
		CategoryVO vo = new CategoryVO();
		BeanUtils.copyProperties(bean, vo);
		return vo;
	}

}