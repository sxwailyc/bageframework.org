package com.bageframework.coder.render;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bageframework.coder.exception.RenderException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerRender implements Render {

	protected static Log logger = LogFactory.getLog(FreemarkerRender.class);

	private Configuration cfg;

	public FreemarkerRender() {
		init();
	}

	private void init() {

		cfg = new Configuration(Configuration.VERSION_2_3_23);

		cfg.setClassForTemplateLoading(this.getClass(), "/");

		cfg.setDefaultEncoding("UTF-8");

		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

		cfg.setLogTemplateExceptions(false);
	}

	public String doRender(String tpl, Object data) {
		try {
			Template tmplate = cfg.getTemplate(tpl);
			Writer writer = new StringWriter();
			tmplate.process(data, writer);
			return writer.toString();
		} catch (TemplateException | IOException ex) {
			logger.error(ex.getMessage(), ex);
			throw new RenderException("page render error.template[" + tpl + "]");
		}
	}

}
