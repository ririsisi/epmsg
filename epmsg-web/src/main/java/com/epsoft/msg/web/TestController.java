package com.epsoft.msg.web;

import com.alibaba.fastjson.JSON;
import com.epsoft.msg.support.dao.MessageTemplateDAO;
import com.epsoft.msg.support.domain.MessageTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private MessageTemplateDAO messageTemplateDao;

    @RequestMapping("/test")
    private String test() {
        System.out.println("sout:测试sout");
        log.info("log:测试log");
        return "hello world";
    }

    @RequestMapping("/database")
    private String testDataBase() {
        List<MessageTemplate> list = messageTemplateDao.findAllByIsDeletedEquals(0, PageRequest.of(0, 10));
        return JSON.toJSONString(list);
    }

}
