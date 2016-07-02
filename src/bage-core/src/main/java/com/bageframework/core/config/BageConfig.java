package com.bageframework.core.config;

import java.util.ArrayList;
import java.util.List;

import com.bageframework.core.authority.AuthorityChecker;

public class BageConfig {

	private AuthorityChecker authorityChecker;

	private boolean enableDistributedSession = false;

	private String loginUrl;

	private List<String> excludeUris = new ArrayList<String>();

	private final static List<String> STATIC_RESOURCE_URI_LIST = new ArrayList<String>();

	static {
		STATIC_RESOURCE_URI_LIST.add("/css/");
		STATIC_RESOURCE_URI_LIST.add("/img/");
		STATIC_RESOURCE_URI_LIST.add("/js/");
	}

	public boolean isEnableDistributedSession() {
		return enableDistributedSession;
	}

	public void setEnableDistributedSession(boolean enableDistributedSession) {
		this.enableDistributedSession = enableDistributedSession;
	}

	public boolean isNeedLogin(String uri) {

		if (uri.startsWith(loginUrl)) {
			return false;
		}

		for (String staticResourceUri : STATIC_RESOURCE_URI_LIST) {
			if (uri.startsWith(staticResourceUri)) {
				return false;
			}
		}

		for (String excludeUri : excludeUris) {
			if (uri.startsWith(excludeUri)) {
				return false;
			}
		}
		return true;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public List<String> getExcludeUris() {
		return excludeUris;
	}

	public void setExcludeUris(List<String> excludeUris) {
		this.excludeUris = excludeUris;
	}

	public AuthorityChecker getAuthorityChecker() {
		return authorityChecker;
	}

	public void setAuthorityChecker(AuthorityChecker authorityChecker) {
		this.authorityChecker = authorityChecker;
	}

}
