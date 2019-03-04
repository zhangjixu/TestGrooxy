package com.cn.dhb.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovyObject;
import org.junit.Test;

import java.io.File;

/**
 * @Author: zhangjixu
 * @CreateDate: 2019/3/1
 * @Description:
 * @Version: 1.0.0
 */
public class TestGrooxy {

    @Test
    public void testParseJson() {
        try {
            GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
            File sourceFile = new File("/Users/jixuzhang/Documents/workspaces/homeGit/TestGrooxy/src/main/java/com/cn/dhb/grooxy/ParseJson.groovy");
            Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
            GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();
            String str = "{\"name\":\"张三\"}";
            JSONObject json = (JSONObject) instance.invokeMethod("parseJson", str);
            System.out.println(json);
            instance = null;
            testGroovyClass = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testParseInnnerJson() {
        try {
            GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
            File sourceFile = new File("/Users/jixuzhang/Documents/workspaces/homeGit/TestGrooxy/src/main/java/com/cn/dhb/grooxy/ParseJson.groovy");
            Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
            GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();
            String str = "{\"name\":\"张三\", \"json\": {\"name\": \"李四\"}}\n";
            JSONObject json = (JSONObject) instance.invokeMethod("parseInnerJson", str);
            System.out.println(json);
            instance = null;
            testGroovyClass = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testParseArrayJson() {
        try {
            GroovyClassLoader classLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader());
            File sourceFile = new File("/Users/jixuzhang/Documents/workspaces/homeGit/TestGrooxy/src/main/java/com/cn/dhb/grooxy/ParseJson.groovy");
            Class testGroovyClass = classLoader.parseClass(new GroovyCodeSource(sourceFile));
            GroovyObject instance = (GroovyObject) testGroovyClass.newInstance();
            String str = "{\"name\":\"张三\", \"json\": {\"name\": \"李四\"}, \"arrJson\":[{\"name\":\"赵\"}, {\"name\":\"钱\"}]}";
            JSONArray arrayJson = (JSONArray) instance.invokeMethod("parseArrayJson", str);
            for (int i = 0; i < arrayJson.size(); i++) {
                System.out.println(arrayJson.get(i));
            }
            instance = null;
            testGroovyClass = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
