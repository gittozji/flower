package me.zji.entity;

import me.zji.constants.OrderFormState;

import java.sql.Timestamp;

public class OrderForm extends Id {
    Long userId;
    Long flowerId;
    Integer count;
    Integer state;
    String date;

    User user;
    Flower flower;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(Long flowerId) {
        this.flowerId = flowerId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getState() {
        return state;
    }

    public String getStateDisplay() {
        return OrderFormState.valueOf("取消").getName();
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public String getDate() {
        return date;
    }
}
