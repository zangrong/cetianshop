/*******************************************************************************
 * Copyright (c) 2014, 2016 cetian tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cetian.plat.data;

import org.springside.modules.test.data.RandomData;

import com.cetian.module.plat.model.SystemUser;

public class UserData {

	public static SystemUser randomNewUser() {
		SystemUser user = new SystemUser();
		user.setLoginName(RandomData.randomName("user"));
		user.setName(RandomData.randomName("WeixinUser"));
		user.setPlainPassword(RandomData.randomName("password"));

		return user;
	}
}
