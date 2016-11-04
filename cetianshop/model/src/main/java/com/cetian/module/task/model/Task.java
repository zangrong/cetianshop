/*******************************************************************************
 * Copyright (c) 2014, 2016 cetian tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cetian.module.task.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.cetian.model.IdEntity;
import com.cetian.module.plat.model.SystemUser;

//JPA标识
@Entity
@Table(name = "ct_task")
public class Task extends IdEntity {

	private String title;
	private String description;
	private SystemUser user;

	// JSR303 BeanValidator的校验规则
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// JPA 基于USER_ID列的多对一关系定义
	@ManyToOne
	@JoinColumn(name = "user_id")
	public SystemUser getSystemUser() {
		return user;
	}

	public void setSystemUser(SystemUser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
