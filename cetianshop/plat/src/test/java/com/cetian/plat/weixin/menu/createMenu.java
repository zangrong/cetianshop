package com.cetian.plat.weixin.menu;

import com.cetian.core.util.weixin.WeixinUtil;
import com.cetian.core.weixin.menu.Button;
import com.cetian.core.weixin.menu.ClickButton;
import com.cetian.core.weixin.menu.Menu;
import com.cetian.core.weixin.menu.ViewButton;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author Alcott Hawk
 * @Date 11/20/2016
 */
public class createMenu {


    @Test
    public void createTest(){
        Menu menu = new Menu();
        ClickButton clickButton = new ClickButton();
        clickButton.setName("菜单创建测试");
        clickButton.setKey("click");

        ViewButton viewButton = new ViewButton();
        viewButton.setName("微官网");
        viewButton.setUrl("https://www.baidu.com");
        menu.setButton(new Button[]{clickButton,viewButton});

        String getAccessTokenUrl = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", "wx66f67263ca7816c2", "d4624c36b6795d1d99dcf0547af5443d");
        JSONObject jo = null;
        try {
            jo = WeixinUtil.doGetRequest(getAccessTokenUrl);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String ACCESS_TOKEN = jo.getString("access_token");
        System.out.println(ACCESS_TOKEN);

        String requestUrl = String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s", ACCESS_TOKEN);
        JSONObject jsonObject = new JSONObject(menu);
        try {
            WeixinUtil.doPostRequest(requestUrl,jsonObject.toString());
            System.out.println("创建微信菜单");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("创建微信菜单失败："+e.getMessage());
        } catch (KeyStoreException e) {
            System.out.println("创建微信菜单失败："+e.getMessage());
        } catch (KeyManagementException e) {
            System.out.println("创建微信菜单失败："+e.getMessage());
        } catch (IOException e) {
            System.out.println("创建微信菜单失败："+e.getMessage());
        }
    }
}
