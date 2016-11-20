package com.cetian.core.weixin.req;

/**
 * 文本消息
 * @author zangrong
 *
 */
public class ReqTextMessage extends ReqMessage {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
