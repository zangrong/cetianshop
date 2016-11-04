/*******************************************************************************
 * Copyright (c) 2014, 2016 cetian tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cetian.module.plat.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cetian.module.plat.model.SystemUser;

public interface SystemUserDao extends PagingAndSortingRepository<SystemUser, Long> {
	SystemUser findByLoginName(String loginName);
}
