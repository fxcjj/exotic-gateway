package com.vic.user2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author victor
 * date: 2020/11/30 19:26
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    HttpServletRequest request;

    @GetMapping("test1")
    public String test1() {
        return "user2 test1, " + request.getParameter("red");
    }

    /**
     * 为 api-gateway中GatewayConfig类使用
     * @return
     */
    @GetMapping("test2")
    public String test2() {
        return "user1 test2";
    }

    @GetMapping("test3")
    public String test3() {
        return "user2 test3";
    }
}
