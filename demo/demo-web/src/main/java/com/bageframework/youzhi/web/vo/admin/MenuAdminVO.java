package com.bageframework.demo.web.vo.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.bageframework.demo.web.model.Menu;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class MenuAdminVO {

	private Integer id;

	private String title;

	private Integer parentId;

	private Integer depth;

	private Integer sort;

	private String path;

	private String style;

	private List<MenuAdminVO> nodes = new ArrayList<MenuAdminVO>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public List<MenuAdminVO> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuAdminVO> nodes) {
		this.nodes = nodes;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public static MenuAdminVO create(Menu bean) {
		MenuAdminVO vo = new MenuAdminVO();
		BeanUtils.copyProperties(bean, vo);
		vo.setTitle(bean.getName());
		return vo;
	}

}