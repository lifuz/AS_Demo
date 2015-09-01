package com.lifuz.testdraw.bean;

/**
 * 作者：李富 on 2015/8/31.
 * 邮箱：lifuzz@163.com
 */
public class Work {

    private String name;
    private String bj_name;
    private String count;
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBj_name() {
        return bj_name;
    }

    public void setBj_name(String bj_name) {
        this.bj_name = bj_name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Work{" +
                "name='" + name + '\'' +
                ", bj_name='" + bj_name + '\'' +
                ", count='" + count + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
