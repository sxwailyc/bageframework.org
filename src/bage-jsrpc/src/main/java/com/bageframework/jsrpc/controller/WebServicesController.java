package com.bageframework.jsrpc.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.bageframework.jsrpc.model.RpcRequest;
import com.bageframework.jsrpc.util.DateUtil;
import com.bageframework.jsrpc.util.Json;

@Controller
@RequestMapping(value = WebServicesController.DIR)
public class WebServicesController {

	public static final String DIR = "/services/";

	protected static Log logger = LogFactory.getLog(WebServicesController.class);

	/**
	 * 得到方法,约定service里不允许同名方法
	 * 
	 * @param obj
	 * @param method
	 * @return
	 */
	private Method getMethod(Object obj, String methodName) {
		Method[] methods = obj.getClass().getMethods();
		for (Method method : methods) {
			if (isMatcher(method, methodName)) {
				return method;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param method
	 * @return
	 */
	private boolean isMatcher(Method method, String methodName) {
		if (!methodName.equals(method.getName())) {
			return false;
		}

		Class<?>[] clas = method.getParameterTypes();
		for (Class<?> cls : clas) {
			if (cls.equals(Object.class)) {
				return false;
			}
		}
		return true;
	}

	private Object[] getParams(HttpServletRequest request, Object[] params, Class<?>[] paramTypes) {

		Object[] newParams = null;

		int pos = 0;
		if (paramTypes.length > 0 && paramTypes[0] == HttpServletRequest.class) {
			newParams = new Object[params.length + 1];
			newParams[0] = request;
			pos = 1;
		} else {
			newParams = new Object[params.length];
		}

		for (int i = 0; i < params.length; i++) {
			Class<?> cls = paramTypes[pos + i];
			Object o = null;

			Object param = params[i];

			if (logger.isDebugEnabled()) {
				logger.debug("cls[" + cls.getName() + "], param[" + param + "]");
			}

			if (param == null) {
				newParams[i + pos] = o;
				continue;
			}
			if (cls == Integer.class || cls == int.class) {
				o = Integer.parseInt(params[i].toString());
			} else if (cls == String.class) {
				o = params[i].toString();
			} else if (cls == Long.class || cls == long.class) {
				o = Long.parseLong(params[i].toString());
			} else if (cls == Double.class || cls == double.class) {
				o = Double.parseDouble(params[i].toString());
			} else if (cls == Date.class) {
				try {
					o = DateUtil.str2Date(params[i].toString());
				} catch (Exception e) {
					logger.debug(e.getMessage(), e);
					o = params[i];
				}
			} else {
				try {
					o = Json.toObject(Json.toJson(params[i]), cls, true);
				} catch (Exception e) {
					logger.warn(e.getMessage(), e);
					o = params[i];
				}
			}
			newParams[i + pos] = o;
		}
		return newParams;
	}

	private Object getService(ApplicationContext context, String serviceName) {
		try {
			if (context.containsBean(serviceName + "WebServiceImpl")) {
				return context.getBean(serviceName + "WebServiceImpl");
			}

			if (context.containsBean(serviceName + "WebService")) {
				return context.getBean(serviceName + "WebService");
			}

		} catch (Exception e) {
			logger.info("service[" + serviceName + "]不存在");
		}

		return null;
	}

	@ResponseBody
	@RequestMapping(value = "{serviceName}")
	public Map<String, Object> call(HttpServletRequest request, @RequestBody RpcRequest rpcRequest, @PathVariable String serviceName) {

		ApplicationContext context = RequestContextUtils.findWebApplicationContext(request);

		Map<String, Object> result = new HashMap<String, Object>();
		String error = null;

		logger.info("serviceName[" + serviceName + "], method[" + rpcRequest.getMethod() + "].params[" + rpcRequest.getParams() + "]");

		Object service = this.getService(context, serviceName);

		if (service != null) {

			Method m = null;

			m = getMethod(service, rpcRequest.getMethod());

			if (m != null) {

				if (logger.isDebugEnabled()) {
					logger.debug("method[" + m + "]");
				}

				try {

					Object[] params = getParams(request, rpcRequest.getParams().toArray(), m.getParameterTypes());
					Object obj = m.invoke(service, params);
					result.put("result", obj);

				} catch (InvocationTargetException ie) {
					logger.error(ie.getMessage(), ie);
					error = ie.getTargetException().getMessage();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					error = "服务器异常";
				}
			} else {
				error = "service对应的方法不存在.[" + rpcRequest.getMethod() + "]";
			}

		} else {
			error = "service不存在.[" + serviceName + "]";
		}

		result.put("error", error);
		result.put("id", rpcRequest.getId());

		return result;
	}
}
