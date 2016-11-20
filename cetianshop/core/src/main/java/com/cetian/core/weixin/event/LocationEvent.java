package com.cetian.core.weixin.event;

/**
 * 上报地理位置事件
 * @author zangrong
 *
 */
public class LocationEvent extends BaseEvent {
	// 纬度
	private String Latitude;
	// 经度
	private String Longitude;
	// 地理位置精度
	private String Precision;
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getPrecision() {
		return Precision;
	}
	public void setPrecision(String precision) {
		Precision = precision;
	}
}
