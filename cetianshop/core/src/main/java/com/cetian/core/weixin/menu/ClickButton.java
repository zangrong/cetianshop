package com.cetian.core.weixin.menu;

/**
 * 自定义click按钮
 * @Author liuzidong
 * @Date 2016-11-16
 * @version 1.0
 */
public class ClickButton extends Button{

    private String type = "click";

    private  String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
