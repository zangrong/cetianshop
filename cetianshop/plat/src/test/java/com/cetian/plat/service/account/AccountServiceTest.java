/*******************************************************************************
 * Copyright (c) 2014, 2016 cetian tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cetian.plat.service.account;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springside.modules.test.security.shiro.ShiroTestUtils;
import org.springside.modules.utils.Clock.MockClock;

import com.cetian.core.exception.ServiceException;
import com.cetian.module.plat.dao.SystemUserDao;
import com.cetian.module.plat.model.ShiroUser;
import com.cetian.module.plat.model.SystemUser;
import com.cetian.module.plat.service.AccountService;
import com.cetian.module.task.dao.TaskDao;
import com.cetian.plat.data.UserData;

/**
 * AccountService的测试用例, 测试Service层的业务逻辑.
 * 
 * 
 */
public class AccountServiceTest {

	@InjectMocks
	private AccountService accountService;

	@Mock
	private SystemUserDao mockUserDao;

	@Mock
	private TaskDao mockTaskDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ShiroTestUtils.mockSubject(new ShiroUser(3L, "foo", "Foo"));
	}

	@Test
	public void registerUser() {
		SystemUser user = UserData.randomNewUser();
		Date currentTime = new Date();
		accountService.setClock(new MockClock(currentTime));

		accountService.registerUser(user);

		// 验证user的角色，注册日期和加密后的密码都被自动更新了。
		assertThat(user.getRoles()).isEqualTo("user");
		assertThat(user.getRegisterDate()).isEqualTo(currentTime);
		assertThat(user.getPassword()).isNotNull();
		assertThat(user.getSalt()).isNotNull();
	}

	@Test
	public void updateUser() {
		// 如果明文密码不为空，加密密码会被更新.
		SystemUser user = UserData.randomNewUser();
		accountService.updateUser(user);
		assertThat(user.getSalt()).isNotNull();

		// 如果明文密码为空，加密密码无变化。
		SystemUser user2 = UserData.randomNewUser();
		user2.setPlainPassword(null);
		accountService.updateUser(user2);
		assertThat(user2.getSalt()).isNull();
	}

	@Test
	public void deleteUser() {
		// 正常删除用户.
		accountService.deleteUser(2L);
		Mockito.verify(mockUserDao).delete(2L);

		// 删除超级管理用户抛出异常, userDao没有被执行
		try {
			accountService.deleteUser(1L);
			failBecauseExceptionWasNotThrown(ServiceException.class);
		} catch (ServiceException e) {
			// expected exception
		}
		Mockito.verify(mockUserDao, Mockito.never()).delete(1L);
	}

}
