/*******************************************************************************
 * Copyright (c) 2014, 2016 cetian tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cetian.module.weixin.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Clock;
import org.springside.modules.utils.Encodes;

import com.cetian.core.exception.ServiceException;
import com.cetian.module.weixin.dao.WeixinUserDao;
import com.cetian.module.weixin.model.ShiroUser;
import com.cetian.module.weixin.model.WeixinUser;

/**
 * 用户管理类.
 * 
 * 
 */
// Spring Service Bean的标识.
@Component
@Transactional
public class WeixinUserService {

	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	private static final int SALT_SIZE = 8;

	private static Logger logger = LoggerFactory.getLogger(WeixinUserService.class);

	@Autowired
	private WeixinUserDao weixinUserDao;
	private Clock clock = Clock.DEFAULT;

	public List<WeixinUser> getAllUser() {
		return (List<WeixinUser>) weixinUserDao.findAll();
	}

	public WeixinUser getUser(Long id) {
		return weixinUserDao.findOne(id);
	}

	public WeixinUser findUserByLoginName(String loginName) {
		return weixinUserDao.findByLoginName(loginName);
	}

	public void registerUser(WeixinUser user) {
		entryptPassword(user);
		user.setRoles("user");
		user.setRegisterDate(clock.getCurrentDate());

		weixinUserDao.save(user);
	}

	public void updateUser(WeixinUser user) {
		if (StringUtils.isNotBlank(user.getPlainPassword())) {
			entryptPassword(user);
		}
		weixinUserDao.save(user);
	}

	public void deleteUser(Long id) {
		if (isSupervisor(id)) {
			logger.warn("操作员{}尝试删除超级管理员用户", getCurrentUserName());
			throw new ServiceException("不能删除超级管理员用户");
		}
		weixinUserDao.delete(id);
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}

	/**
	 * 取出Shiro中的当前用户LoginName.
	 */
	private String getCurrentUserName() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.loginName;
	}

	/**
	 * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
	 */
	private void entryptPassword(WeixinUser user) {
		byte[] salt = Digests.generateSalt(SALT_SIZE);
		user.setSalt(Encodes.encodeHex(salt));

		byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, HASH_INTERATIONS);
		user.setPassword(Encodes.encodeHex(hashPassword));
	}
	
	public void setClock(Clock clock) {
		this.clock = clock;
	}
}
