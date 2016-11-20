package com.cetian.core.weixin.resp;
/**
 * 文本消息
 * @author zangrong
 *
 */
public class RespTextMessage extends RespMessage {
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
