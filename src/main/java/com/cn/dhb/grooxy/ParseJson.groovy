package com.cn.dhb.grooxy

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import groovy.json.JsonSlurper
import groovy.sql.Sql
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @Author: zhangjixu
 * @CreateDate: 2018/6/26
 * @Description:
 * @Version: 1.0.0
 */
class ParseJson {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParseJson.class)

    def sql = Sql.newInstance("jdbc:mysql:///test", "", "", "com.mysql.jdbc.Driver")

    static void main(String[] args) {
        def sql = Sql.newInstance("jdbc:mysql:///test", "", "", "com.mysql.jdbc.Driver")
        //def batchSql = "INSERT INTO `local` (`_id`, name, age) VALUES (?, ?, ?)"
        def batchSql = "INSERT INTO `local` (`_id`) VALUES (?)"
        def arr = [-120]
        Boolean flag = true
        try {
            def num = sql.withBatch(1, batchSql) { ps ->
                for (i in arr) {
                    ps.addBatch(i)
                }
            }
            println(" &&&&&&&&&&&&&& " + num)
        } catch (Exception e) {
            flag = false
            LOGGER.error("批量保存出错 msg : {}", e.message)
            e.printStackTrace()
        }

        println "==================== " + flag
    }

    /**
     *  保存数据
     * @param str
     * @return
     */
    def saveMysql(String str) {
        Boolean flag = true
        JsonSlurper jsonSlurper = new JsonSlurper()
        try {
            def obj = jsonSlurper.parseText(str)
            String _id = obj._id
            String age = obj.age
            String name = obj.name
            String date = obj.date
            String saveSql = "insert into `local`(`_id`, `age`, `name`, `date`) values(?, ?, ?, ?)"
            sql.execute(saveSql, [_id, age, name, date])
            LOGGER.error(" 保存成功 ")
        } catch (Exception e) {
            flag = false
            LOGGER.error("保存出错 msg : {}", e.message)
        }
        flag
    }

    /**
     *  批量保存数据
     * @param sql
     * @return
     */
    def batchInsert(String sql) {
        Boolean flag = true
        def batchSql = "INSERT INTO local (_id) VALUES (?)"
        def arr = ["1", "2"]
        try {
            sql.with(arr.size(), batchSql) { ps ->
                for (i in arr) {
                    ps.addBatch(i)
                }
            }
        } catch (Exception e) {
            flag = false
            LOGGER.error("批量保存出错 msg : {}", e.message)
        }
        flag
    }

    /**
     *  事务提交
     * @param str
     * @return
     */
    def transaction(String str) {
        Boolean flag = true
        // 关闭事务自动提交
        sql.connection.autoCommit = false
        try {
            sql.execute("insert into `local`(`_id`) values(1)")
            sql.execute("insert into `local`(`_id`) values(1)")
            // 手动提交事务
            sql.commit()
        } catch (Exception e) {
            flag = false
            // 回滚事务
            sql.rollback()
            LOGGER.error("事务保存语句出错 msg : {}", e.message)
        }
        flag
    }

    /**
     *  解析 json
     * @param str
     */
    JSONObject parseJson(String str) throws Exception {
        JsonSlurper jsonSlurper = new JsonSlurper()
        def obj = jsonSlurper.parseText(str)
        JSONObject json = new JSONObject()
        String name = obj.name
        json.put("name", name)
        json
    }

    /**
     *  解析 json 中内嵌 json
     * @param str
     */
    JSONObject parseInnerJson(String str) throws Exception {
        JsonSlurper jsonSlurper = new JsonSlurper()
        def obj = jsonSlurper.parseText(str)
        JSONObject json = new JSONObject()
        // 取值要知道原 json 的数据格式
        String name = obj.json.name
        json.put("name", name)
        json
    }

    /**
     *  解析 json 数组
     * @param str
     */
    JSONArray parseArrayJson(String str) throws Exception {
        JsonSlurper jsonSlurper = new JsonSlurper()
        def obj = jsonSlurper.parseText(str)
        JSONArray jsonArray = new JSONArray()
        for (arr in obj.arrJson) {
            JSONObject json = new JSONObject()
            String name = arr.name
            json.put("name", name)
            jsonArray.add(json)
        }
        jsonArray
    }
}
