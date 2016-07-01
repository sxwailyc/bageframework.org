package com.bageframework.authority.service;

public interface AuthorityService {

	public boolean hasPrivilege(String userId, String path, String code);
}
