package com.epsoft.msg.support;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Throwables;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        try {
            LombokTest lombokTest1 = LombokTest.builder().id("1").name("杨九").build();
            LombokTest lombokTest2 = LombokTest.builder().id("2").name("杨渊").build();
            List<LombokTest> lombokTests = Arrays.asList(lombokTest1, lombokTest2);
            if (CollUtil.isNotEmpty(lombokTests)) {
                System.out.println(JSON.toJSONString(lombokTests));
            }
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
        }

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LombokTest{
        private String name;
        private String id;
    }
}
