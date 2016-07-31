package com.bageframework.util;

public class ResourcesUtil {

	private final static String[] STATIC_RESOURCE = { "/css", "/img", "/js", "/html", "/fonts", "/plugin" };

	private final static String[] STATIC_RESOURCE_EXTENSION = { ".jpg", ".css", ".html", ".js", ".ico", ".png" };

	public final static boolean isResource(String uri) {
		if (uri == null) {
			return false;
		}
		for (String staticResource : STATIC_RESOURCE) {
			if (uri.startsWith(staticResource)) {
				return true;
			}
		}
		for (String staticResourceExtension : STATIC_RESOURCE_EXTENSION) {
			if (uri.endsWith(staticResourceExtension)) {
				return true;
			}
		}
		return false;
	}

}
