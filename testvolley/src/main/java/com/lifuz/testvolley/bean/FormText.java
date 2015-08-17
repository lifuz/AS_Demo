package com.lifuz.testvolley.bean;

/**
 * 作者：李富 on 2015/8/17.
 * 邮箱：lifuzz@163.com
 */
public class FormText {

    private String name;
    private String value;

    public FormText(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FormText{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
