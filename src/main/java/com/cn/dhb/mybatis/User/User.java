package com.cn.dhb.mybatis.User;

/**
 * @Author: zhangjixu
 * @CreateDate: 2019/3/13
 * @Description:
 * @Version: 1.0.0
 */
public class User {

    private int id;
    private String name;
    private int age;
    private String cdate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", cdate='" + cdate + '\'' +
                '}';
    }
}
