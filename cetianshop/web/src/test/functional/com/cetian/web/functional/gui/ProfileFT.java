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

public class ProfileFT extends BaseSeleniumTestCase {

	/**
	 * 修改用户资料.
	 */
	@Test
	public void editProfile() {
		s.open("/profile");
		s.type(By.id("name"), "Kevin");
		s.click(By.id("submit_btn"));
		assertThat(s.isTextPresent("Kevin")).as("没有成功消息").isTrue();
	}
}
