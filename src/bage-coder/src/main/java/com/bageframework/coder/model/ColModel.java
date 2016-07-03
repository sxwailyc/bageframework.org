package com.bageframework.coder.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.bageframework.util.Json;

public class ColModel {

	private String label;

	private String name;

	private String index;

	private String editable = "false";

	private int width = 10;

	private String sorttype = "string";

	private String formatter = "string";

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getEditable() {
		return editable;
	}

	public void setEditable(String editable) {
		this.editable = editable;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getSorttype() {
		return sorttype;
	}

	public void setSorttype(String sorttype) {
		this.sorttype = sorttype;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		// {name:'id',index:'id', editable: true, width:10, sorttype:"string",
		// formatter:"string"},
		// {name:'table',index:'table', editable: true, width:10},
		// {name:'javaClass',index:'java_class', width:10, align:"right",
		// formatter:"string"},
		// {name:'remark', editable: true, width:10},

		Map<String, Object> m = new LinkedHashMap<String, Object>();
		m.put("name", name);
		m.put("label", label);
		m.put("width", width);
		m.put("formatter", formatter);

		String s = Json.toJson(m);

		System.out.println(s);

		return s;
	}
}
