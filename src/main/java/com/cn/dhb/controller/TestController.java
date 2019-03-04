package com.cn.dhb.controller;

import com.alibaba.fastjson.JSONObject;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @Author: zhangjixu
 * @CreateDate: 2019/3/1
 * @Description:
 * @Version: 1.0.0
 */
@RestController
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/testGrooxy", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public Object testGrooxy() {
        JSONObject json = null;
        try {
            GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
            File sourceFile = new File("/Users/jixuzhang/Documents/workspaces/homeGit/TestGrooxy/src/main/java/com/cn/dhb/grooxy/ParseJson.groovy");
            Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
            GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();
            String str = "{\"name\":\"张三\"}";
            json = (JSONObject) instance.invokeMethod("parseJson", str);
            instance = null;
            testGroovyClass = null;
        } catch (Exception e) {
            LOGGER.error(" exception : {} ", e.getMessage());
        }
        return json;
    }


    @RequestMapping(value = "/jsonGrooxy", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public Object parseJson(@RequestBody String params) {
        JSONObject jsons = JSONObject.parseObject(params);
        String path = jsons.getString("path");
        JSONObject json = null;
        try {
            GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
            File sourceFile = new File(path);
            Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
            GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();
            String str = "{\"name\":\"张三\"}";
            json = (JSONObject) instance.invokeMethod("parseJson", str);
            instance = null;
            testGroovyClass = null;
        } catch (Exception e) {
            LOGGER.error(" exception : {} ", e.getMessage());
        }
        return json;
    }

    @RequestMapping(value = "/saveMysql", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    public Object saveMysql(@RequestBody String params) {
        JSONObject json = JSONObject.parseObject(params);
        Boolean flag = null;
        try {
            String path = json.getString("path");
            String method = json.getString("method");
            GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
            File sourceFile = new File(path);
            Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
            GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();
            flag = (Boolean) instance.invokeMethod(method, params);
            instance = null;
            testGroovyClass = null;
        } catch (Exception e) {
            LOGGER.error(" exception : {} ", e.getMessage());
        }
        return flag;
    }

}
