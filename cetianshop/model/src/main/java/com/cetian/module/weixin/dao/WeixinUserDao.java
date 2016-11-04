/*******************************************************************************
 * Copyright (c) 2014, 2016 cetian tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cetian.module.weixin.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cetian.module.weixin.model.WeixinUser;

public interface WeixinUserDao extends PagingAndSortingRepository<WeixinUser, Long> {
	WeixinUser findByLoginName(String loginName);
}
