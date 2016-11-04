/*******************************************************************************
 * Copyright (c) 2014, 2016 cetian tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cetian.plat.data;

import org.springside.modules.test.data.RandomData;

import com.cetian.module.plat.model.SystemUser;
import com.cetian.module.task.model.Task;

/**
 * Task相关实体测试数据生成.
 * 
 * 
 */
public class TaskData {

	public static Task randomTask() {
		Task task = new Task();
		task.setTitle(randomTitle());
		SystemUser user = new SystemUser(1L);
		task.setSystemUser(user);
		return task;
	}

	public static String randomTitle() {
		return RandomData.randomName("Task");
	}
}
