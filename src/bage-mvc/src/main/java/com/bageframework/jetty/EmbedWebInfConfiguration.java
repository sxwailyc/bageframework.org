package com.bageframework.jetty;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;

public class EmbedWebInfConfiguration extends WebInfConfiguration {

	@Override
	protected List<Resource> findJars(WebAppContext context) throws Exception {
		List<Resource> list = super.findJars(context);
		if (list == null) {
			list = new ArrayList<Resource>();
		}
		ClassLoader aLoader = getClass().getClassLoader();
		if (aLoader instanceof URLClassLoader) {
			URL[] _urls = ((URLClassLoader) aLoader).getURLs();
			for (URL _url : _urls) {
				list.add(Resource.newResource(_url));
			}
			{
				// String jarFile = new TldJarConfig().addLeopardTld(context,
				// list);
				// if (jarFile != null) {
				// this.changeClassLoader(context, jarFile);
				// }
				// System.err.println("jarFile:" + jarFile);
			}
		}

		return list;
	}

	protected void changeClassLoader(WebAppContext webContext, String jarFile) throws IOException {
		// System.err.println("start test");
		// System.err.println("webinf:" + webContext.getBaseResource());
		ClassLoader classLoader = webContext.getClassLoader();

		URL[] urls = new URL[1];
		urls[0] = new File(jarFile).toURI().toURL();
		// urls[0] = new
		// File("/work/news/leopard/leopard-web/src/main/resources/").toURI().toURL();
		URLClassLoader urlClassLoader = new URLClassLoader(urls, classLoader);
		webContext.setClassLoader(urlClassLoader);
	}
}