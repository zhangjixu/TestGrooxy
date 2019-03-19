package com.cn.dhb.test;

import com.cn.dhb.mybatis.User.User;
import com.cn.dhb.mybatis.User.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: zhangjixu
 * @CreateDate: 2019/3/13
 * @Description:
 * @Version: 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:springmvc-servlet.xml", "classpath*:applicationContext.xml"})
public class TestMybatis {

    @Autowired
    private UserDao userDao;

    @Test
    public void test() {
        List<User> userList = userDao.queryAllUser();
        for (int i = 0; i < userList.size(); i++) {
            System.out.println(userList.get(i));
        }

        System.out.println(userDao.queryUser(2));

    }

}
