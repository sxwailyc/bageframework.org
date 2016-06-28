package com.bageframework.mvc.session;

import java.util.Collection;
import java.util.Enumeration;

public class BageEnumeration<T> implements Enumeration<T> {

	private Collection<T> data;

	public BageEnumeration(Collection<T> list) {
		this.data = list;
	}

	public boolean hasMoreElements() {
		return data.iterator().hasNext();
	}

	public T nextElement() {
		return data.iterator().next();
	}

}
