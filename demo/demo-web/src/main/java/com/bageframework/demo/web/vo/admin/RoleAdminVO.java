package com.bageframework.demo.web.vo.admin;

import org.springframework.beans.BeanUtils;
import com.bageframework.demo.web.model.Role;
import java.util.Date;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class RoleAdminVO {

	private Integer id;

	private String name;

	private Date createdTime;

	private String roleId;

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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public static RoleAdminVO create(Role bean) {
		RoleAdminVO vo = new RoleAdminVO();
		BeanUtils.copyProperties(bean, vo);
		return vo;
	}

}