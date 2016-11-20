package com.cetian.module.weixin.service;

import com.cetian.core.util.weixin.WeixinUtil;
import com.cetian.core.weixin.menu.Menu;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 平台微信Service
 * @author liuzidong
 */
@Component
@Transactional
public class PlatWeixinService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${app.id}")
	private String APP_ID;

	@Value("${app.secret}")
	private String APP_SECRET;
	
	@Value("${url.template.access.token}")
	private String URL_TEMPLATE_ACCESS_TOKEN;
	
	@Value("${url.template.jsapi.ticket}")
	private String URL_TEMPLATE_JSAPI_TICKET;
	
	@Value("${url.menu.create}")
	private String URL_MENU_CREATE;
	
	@Value("${url.menu.get}")
	private String URL_MENU_GET;
	
	@Value("${url.menu.delete}")
	private String URL_MENU_DELETE;
	
	@Value("${url.message.set.industry}")
	private String URL_MESSAGE_SET_INDUSTRY;
	
	@Value("${url.message.add.template}")
	private String URL_MESSAGE_ADD_TEMPLATE;
	
	@Value("${url.message.get.template}")
	private String URL_MESSAGE_GET_TEMPLATE;
	
	@Value("${url.message.send}")
	private String URL_MESSAGE_SEND;
	
	@Value("${chitian.token}")
	private String CHITIAN_TOKEN;
	
	@Value("${message.mode}")
	private String MESSAGE_MODE;//消息模式
	
	@Value("${encoding.aes.key}")
	private String ENCODING_AES_KEY;//EncodingAESKey(消息加解密密钥)
	
	// 中控 access token 缓存，定时刷新获取
	private static String ACCESS_TOKEN = "";
	
	// 微信 js 分享所用票据， 缓存定时刷新获取
	private static String JSAPI_TICKET = "";
	
	/**
	 * 为其他组件提供 access token
	 * @return
	 */
	public String accessToken(){
		return ACCESS_TOKEN;
	}
	
	/**
	 * 为其他组件提供 jsapi ticket
	 * @return
	 */
	public String jsApiTicket(){
		return JSAPI_TICKET;
	}
	
	/**
	 * 与微信服务器初始交互校验
	 * 
	 * @param signature 微信服务器加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return
	 */
	public boolean checkSignature(String signature, String timestamp, String nonce) {
		return WeixinUtil.checkSignature(signature, timestamp, nonce, CHITIAN_TOKEN);
	}

	@PostConstruct
	public void init(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                getAccessToken();
            }
        };
        Timer timer = new Timer();
        long delay = 5 * 1000;
        // 每隔10分钟刷新一次
        long intevalPeriod = 600 * 1000;
        timer.schedule(task, delay, intevalPeriod);
    }

	/**
	 * 获取系统中控通用 access token
	 */
	private void getAccessToken() {
        String getAccessTokenUrl = String.format(URL_TEMPLATE_ACCESS_TOKEN, APP_ID, APP_SECRET);
        try {
            JSONObject jo = WeixinUtil.doGetRequest(getAccessTokenUrl);
            ACCESS_TOKEN = jo.getString("access_token");
            System.out.println("ACCESS_TOKEN:"+ACCESS_TOKEN);
            logger.info("获取系统中控ACCESS_TOKEN:"+ACCESS_TOKEN);
        } catch (NoSuchAlgorithmException e) {
            logger.error("获取系统中控ACCESS_TOKEN失败："+e.getMessage());
        } catch (KeyStoreException e) {
            logger.error("获取系统中控ACCESS_TOKEN失败："+e.getMessage());
        } catch (KeyManagementException e) {
            logger.error("获取系统中控ACCESS_TOKEN失败："+e.getMessage());
        } catch (IOException e) {
            logger.error("获取系统中控ACCESS_TOKEN失败："+e.getMessage());
        }
    }

    public void menuCreate(Menu menu){
        String requestUrl = String.format(URL_MENU_CREATE,ACCESS_TOKEN);
        JSONObject jsonObject = new JSONObject(menu);
        try {
            WeixinUtil.doPostRequest(requestUrl,jsonObject.toString());
            logger.info("创建微信菜单");
        } catch (NoSuchAlgorithmException e) {
            logger.error("创建微信菜单失败："+e.getMessage());
        } catch (KeyStoreException e) {
            logger.error("创建微信菜单失败："+e.getMessage());
        } catch (KeyManagementException e) {
            logger.error("创建微信菜单失败："+e.getMessage());
        } catch (IOException e) {
            logger.error("创建微信菜单失败："+e.getMessage());
        }
    }

    /**
     * 微信菜单查询
     * @return JSONObject 微信菜单的jsonobject对象
     */
    public JSONObject menuGet(){
        String requestUrl = String.format(URL_MENU_GET,ACCESS_TOKEN);
        JSONObject jsonObject = null;
        try {
            jsonObject = WeixinUtil.doGetRequest(requestUrl);
            logger.info("微信菜单获取");
        } catch (NoSuchAlgorithmException e) {
            logger.error("微信菜单获取失败："+e.getMessage());
        } catch (KeyStoreException e) {
            logger.error("微信菜单获取失败："+e.getMessage());
        } catch (KeyManagementException e) {
            logger.error("微信菜单获取失败："+e.getMessage());
        } catch (IOException e) {
            logger.error("微信菜单获取失败："+e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 微信菜单删除
     * @return JSONObject
     */
    public JSONObject menuDelete(){
        String requestUrl = String.format(URL_MENU_DELETE,ACCESS_TOKEN);
        JSONObject jsonObject = null;
        try {
            jsonObject = WeixinUtil.doGetRequest(requestUrl);
            logger.info("微信菜单删除");
        } catch (NoSuchAlgorithmException e) {
            logger.error("微信菜单获取失败："+e.getMessage());
        } catch (KeyStoreException e) {
            logger.error("微信菜单获取失败："+e.getMessage());
        } catch (KeyManagementException e) {
            logger.error("微信菜单获取失败："+e.getMessage());
        } catch (IOException e) {
            logger.error("微信菜单获取失败："+e.getMessage());
        }
        return jsonObject;
    }

    /**
     * 处理微信的请求
     * @param map 包含微信请求数据的map集合
     * @return 包含处理过的数据的字符串
     */
    public String processRequest(Map<String,String> map){
        if ("event".equals(map.get("MsgType"))){

        } else {

        }
        return  "test";
    }

}
