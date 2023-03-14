package com.epsoft.msg.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {
    @RequestMapping("/test")
    private String test() {
        System.out.println("sout:测试sout");
        log.info("log:测试log");
        return "hello world";
    }

}
