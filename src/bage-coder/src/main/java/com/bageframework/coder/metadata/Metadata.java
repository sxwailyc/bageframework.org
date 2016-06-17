package com.bageframework.coder.metadata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Metadata {

	private Set<String> modelImports = new HashSet<String>();

	private String author;

	private String packageName;

	private String className;

	private List<Field> fields = new ArrayList<Field>();

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Set<String> getModelImports() {
		return modelImports;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void appendField(Field field) {
		fields.add(field);
	}

	public void appendModelImport(String impt) {
		modelImports.add(impt);
	}
}
