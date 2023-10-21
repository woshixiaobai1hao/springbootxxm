package com.yh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("to")
public class PageController {
//    页面统一跳转
    @RequestMapping("{pageName}")
    public String toPage(@PathVariable String pageName) {
        return pageName;
    }
}
