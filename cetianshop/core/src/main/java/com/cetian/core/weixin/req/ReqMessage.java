package com.cetian.core.weixin.req;
/**
 * 请求消息基类
 * 因为微信设计原因，这里的属性命名以大写开头
 * 与本系统代码规范不一致，但暂时也只能这样了
 * 
 * @author zangrong
 *
 */
public class ReqMessage {
	// 开发者微信号
	private String ToUserName;
	// 用户微信号（openid）
	private String FromUserName;
	// 消息创建时间
	private long CreateTime;
	// 消息类型（text/image/location/link/voice）
	private String MsgType;
	// 消息ID，64位整型
	private long MsgId;
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public long getMsgId() {
		return MsgId;
	}
	public void setMsgId(long msgId) {
		MsgId = msgId;
	}
	
}
