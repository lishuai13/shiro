package com.lishuai.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lishuai
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        System.out.println("跳转至主页");
        return "index";
    }


    /**
     * 权限校验
     * @return
     */
    @RequiresRoles(value={"admin"})
    @RequestMapping("/save")
    public String save(){
        System.out.println("成功进入方法");
        return "redirect:/index";
    }
}
