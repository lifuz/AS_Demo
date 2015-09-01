package com.lifuz.testdraw.bean;

/**
 * 作者：李富 on 2015/8/25.
 * 邮箱：lifuzz@163.com
 */
public class Chart {

    private String date;
    private String count;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "date='" + date + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
