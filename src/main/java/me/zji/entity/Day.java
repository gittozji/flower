package me.zji.entity;

/**
 * 工作日设置
 * Created by qian yun on 2018/3/10.
 */
public class Day extends Id {
    String day;
    Integer workFlag;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Integer getWorkFlag() {
        return workFlag;
    }

    public void setWorkFlag(Integer workFlag) {
        this.workFlag = workFlag;
    }
}
