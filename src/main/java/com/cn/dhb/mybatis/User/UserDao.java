package com.cn.dhb.mybatis.User;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: zhangjixu
 * @CreateDate: 2019/3/13
 * @Description:
 * @Version: 1.0.0
 */
@Repository
public interface UserDao {

    List<User> queryAllUser();
    User queryUser(int id);

}
