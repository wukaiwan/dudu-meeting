package com.qfjy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @RequestMapping("demo")
    @ResponseBody
    public String index(){
        return "demoController 成功返回";
    }
}
