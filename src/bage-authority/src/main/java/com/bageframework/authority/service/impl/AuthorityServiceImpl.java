package com.bageframework.authority.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bageframework.authority.dao.MenuDao;
import com.bageframework.authority.model.Menu;
import com.bageframework.authority.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	public static final Log logger = LogFactory.getLog(AuthorityServiceImpl.class);

	//@Autowired
	private MenuDao menuDao;

	@Override
	public boolean hasPrivilege(String userId, String path, String code) {

		logger.debug("has privilege check.userId[" + userId + "], path[" + path + "], code[" + code + "]");

		Menu menu = menuDao.get("path", path);
		if (menu == null) {
			logger.warn("menu with path not exist.path[" + path + "]");
			return false;
		}
		
		

		return true;
	}
}
