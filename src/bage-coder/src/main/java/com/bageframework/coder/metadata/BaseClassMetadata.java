package com.bageframework.coder.metadata;

import java.util.HashSet;
import java.util.Set;

public class BaseClassMetadata extends Metadata {

	private Set<String> imports = new HashSet<String>();

	private String author;

	private String packageName;

	private String className;

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

	public Set<String> getImports() {
		return imports;
	}

	public void appendImport(String impt) {
		imports.add(impt);
	}
}
