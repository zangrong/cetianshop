package com.cetian.core.weixin.resp;

/**
 * 音乐消息
 * 
 * @author zangrong
 *
 */
public class RespMusicMessage extends RespMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
