package com.bageframework.util;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Test {

	public static void main(String[] args) throws Exception {

		try (final WebClient webClient = new WebClient()) {

			String url = "http://127.0.0.1/test/test.do";
			final HtmlPage page = webClient.getPage(url);
			System.out.println(page.asXml());

		}
	}
}
