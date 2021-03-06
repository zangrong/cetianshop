/*******************************************************************************
 * Copyright (c) 2014, 2016 cetian tech
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.cetian.web.functional.gui;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.openqa.selenium.By;

import com.cetian.web.functional.BaseSeleniumTestCase;

public class RegisterFT extends BaseSeleniumTestCase {

	@Test
	public void register() {
		// 注册用户
		s.open("/logout");
		s.click(By.linkText("注册"));

		s.type(By.id("loginName"), "user2");
		s.type(By.id("name"), "Kevin");
		s.type(By.id("plainPassword"), "user2");
		s.type(By.id("confirmPassword"), "user2");

		s.click(By.id("submit_btn"));

		// 跳转到登录页
		s.waitForTitleContains("登录页");
		assertThat(s.getValue(By.name("username"))).isEqualTo("user2");

		s.type(By.name("password"), "user2");
		s.click(By.id("submit_btn"));

		// 登陆成功
		s.waitForTitleContains("任务管理");

		// 退出用户
		s.open("/logout");
	}

	@Test
	public void inputInValidateValue() {
		s.open("/register");
		s.click(By.id("submit_btn"));

		assertThat(s.getText(By.xpath("//fieldset/div/div/span"))).isEqualTo("必选字段");
	}

}
