package com.cetian.core.weixin.event;

/**
 * 自定义菜单事件
 * @author zangrong
 *
 */
public class MenuEvent extends BaseEvent {
	// 与自定义菜单中的key值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
}
