package me.zji.entity;

/**
 * 工作日设置
 * Created by imyu on 2017/3/1.
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
