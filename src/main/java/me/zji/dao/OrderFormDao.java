package me.zji.dao;

import me.zji.entity.OrderForm;

import java.util.List;

public interface OrderFormDao {
    void create(OrderForm orderForm);
    List<OrderForm> selectByUserId(Long id);
    List<OrderForm> selectAll();
    OrderForm select(Long id);
    void updateState(OrderForm orderForm);
}
