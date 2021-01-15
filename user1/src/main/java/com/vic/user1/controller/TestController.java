package com.vic.user1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 测试相关参数
     * @return
     */
    @GetMapping("test1")
    public String test1() {
        return "user1 test1, " + request.getParameter("red");
    }

    /**
     * 测试通过 java 配置方式，对应api-gateway中GatewayConfig类
     * @return
     */
    @GetMapping("test2")
    public String test2() {
        return "user1 test2";
    }

    /**
     * 测试添加uri前缀
     * @return
     */
    @GetMapping("test3")
    public String test3() {
        return "user1 test3";
    }

    /**
     * 测试限流
     * @return
     */
    @GetMapping("test4")
    public String test4() {
        return "user1 test4";
    }

    /**
     * 测试重试
     * 访问 http://localhost:9768/test/test5，根据filter的Retry（retries）配置重试
     * @return
     */
    @GetMapping("test5")
    public String test5(@RequestParam(value = "id", required = false) Long id) {
        System.out.println("Start test5 method");
        if(id == null) {
            throw new NullPointerException("id is null");
        }
        System.out.println(id);
        return "user1 test5";
    }
}
