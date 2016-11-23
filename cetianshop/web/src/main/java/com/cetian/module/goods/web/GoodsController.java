package com.cetian.module.goods.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Alcott Hawk
 * @Date 11/23/2016
 */
@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "goods/list";
    }

}
