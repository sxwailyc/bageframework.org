package com.bageframework.mvc.propertyeditors;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.text.ParseException;

public class CustomNumberEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			Number num = NumberFormat.getInstance().parse(text);
			setValue(num);
		} catch (ParseException pe) {
			throw new IllegalArgumentException("number param is error");
		}
	}

}
