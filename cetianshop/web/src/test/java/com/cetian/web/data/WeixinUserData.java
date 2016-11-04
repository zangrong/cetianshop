/*******************************************************************************
 * Copyright (c) 2014, 2016 cetian tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cetian.web.data;

import org.springside.modules.test.data.RandomData;

import com.cetian.module.weixin.model.WeixinUser;

public class WeixinUserData {

	public static WeixinUser randomNewUser() {
		WeixinUser user = new WeixinUser();
		user.setLoginName(RandomData.randomName("user"));
		user.setName(RandomData.randomName("User"));
		user.setPlainPassword(RandomData.randomName("password"));

		return user;
	}
}
