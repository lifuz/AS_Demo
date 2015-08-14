package com.lifuz.testvolley.bean;

/**
 * 作者：李富 on 2015/8/14.
 * 邮箱：lifuzz@163.com
 */
public class Car {

    private String vid;
    private String name;
    private String desc;

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Car{" +
                "vid='" + vid + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
