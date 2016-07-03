package com.bageframework.coder.metadata;

import java.util.ArrayList;
import java.util.List;

import com.bageframework.coder.model.ColModel;
import com.bageframework.coder.model.Form;

public class ViewMetadata {

	private String viewName;

	private String modelClassName;

	private String modelObjectName;

	private String title;

	private List<Form> searchForms = new ArrayList<Form>();

	private List<Form> editForms = new ArrayList<Form>();

	private List<ColModel> colModels = new ArrayList<ColModel>();

	public String getModelClassName() {
		return modelClassName;
	}

	public void setModelClassName(String modelClassName) {
		this.modelClassName = modelClassName;
	}

	public String getModelObjectName() {
		return modelObjectName;
	}

	public void setModelObjectName(String modelObjectName) {
		this.modelObjectName = modelObjectName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ColModel> getColModels() {
		return colModels;
	}

	public void addColModel(ColModel colModel) {
		this.colModels.add(colModel);
	}

	public List<Form> getSearchForms() {
		return searchForms;
	}

	public void addSearchForm(Form form) {
		this.searchForms.add(form);
	}

	public List<Form> getEditForms() {
		return editForms;
	}

	public void addEditForm(Form form) {
		this.editForms.add(form);
	}

	public String getViewName() {
		return viewName;
	}

	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

}
