package com.bageframework.coder.metadata;

import java.util.ArrayList;
import java.util.List;

public class ModelMetadata extends BaseClassMetadata {

	private List<Field> fields = new ArrayList<Field>();

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void appendField(Field field) {
		fields.add(field);
	}

}
