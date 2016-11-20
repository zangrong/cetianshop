package com.cetian.core.weixin.menu;

/**
 * 自定义复合菜单
 * @author liuzidong
 * @date 2016-11-17
 * @version 1.0
 */
public class ComplexButton extends Button {

	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}

}
