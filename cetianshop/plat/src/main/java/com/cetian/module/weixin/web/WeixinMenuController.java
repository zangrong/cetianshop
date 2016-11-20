package com.cetian.module.weixin.web;

import com.cetian.core.weixin.menu.Button;
import com.cetian.core.weixin.menu.ClickButton;
import com.cetian.core.weixin.menu.Menu;
import com.cetian.core.weixin.menu.ViewButton;
import com.cetian.module.weixin.service.PlatWeixinService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 微信菜单管理Controller
 * @author liuzodong
 * @date 2016-11-17
 * @version 1.0
 */
@Controller
@RequestMapping(value = "weixin/menu")
public class WeixinMenuController {
	
	@Autowired
	private PlatWeixinService platWeixinService;
	
	/**
	 * 微信菜单设置
	 * @return 菜单页面设置地址
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		return "weixin/index";
	}

    /**
     * 微信菜单创建
     */
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	public void create(){
		Menu menu = new Menu();
		ClickButton clickButton = new ClickButton();
		clickButton.setName("赞");
		clickButton.setKey("click");

		ViewButton viewButton = new ViewButton();
		viewButton.setName("微官网");
		viewButton.setUrl("https://www.baidu.com");
		menu.setButton(new Button[]{clickButton,viewButton});
		platWeixinService.menuCreate(menu);
	}

    /**
     * 微信菜单查询
     * @return JSONObject
     */
	@RequestMapping(value="get", method = RequestMethod.GET)
	public JSONObject get(){
		JSONObject jsonObject = platWeixinService.menuGet();
        return jsonObject;
	}

    /**
     * 微信菜单删除
     * @return 删除状态，成功返回"ok",失败返回"error"
     */
	@RequestMapping(value="delete", method = RequestMethod.GET)
    @ResponseBody
	public String delete(){
		JSONObject jsonObject = platWeixinService.menuDelete();
        if (jsonObject != null){
            return jsonObject.get("errmsg").toString();
        } else {
            return "error";
        }
	}

}
