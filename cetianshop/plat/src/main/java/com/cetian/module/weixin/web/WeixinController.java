package com.cetian.module.weixin.web;

import com.cetian.core.util.weixin.WeixinUtil;
import com.cetian.module.weixin.service.PlatWeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 平台对微信平台交互对接Controller
 * @author liuzidong
 */
@RestController
@RequestMapping(value = "weixin")
public class WeixinController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlatWeixinService platWeixinService;
	
	/**
	 * 接收微信服务端校验
	 * @param 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String weixin(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		log.info("user-agent = " + userAgent);
		String signature = request.getParameter("signature");
		log.info("signature = " + signature);
		
		String timestamp = request.getParameter("timestamp");
		log.info("timestamp = " + timestamp);
		
		String nonce = request.getParameter("nonce");
		log.info("nonce = " + nonce);
		String echostr = request.getParameter("echostr");
		log.info("echostr = " + echostr);
		if (this.platWeixinService.checkSignature(signature, timestamp, nonce)) {
			return echostr;
		} else {
			return "check failed";
		}
	}

    /**
     * 接收微信消息，并做对应的响应
     * @param request
     * @param response
     */
	@RequestMapping(method = RequestMethod.POST)
	public void weixin(HttpServletRequest request, HttpServletResponse response){
		System.out.println("test");
        Map<String,String> map = WeixinUtil.parseXml(request);
        if ("event".equals(map.get("MsgType"))){

        } else {

        }
	}

}
