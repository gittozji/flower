package me.zji.service;

import me.zji.entity.OrderForm;

import java.util.List;

public interface OrderFormService {
    void create(OrderForm orderForm);
    List<OrderForm> queryByUserId(Long id);
    List<OrderForm> query();
    OrderForm getOrderForm(Long id);
    void updateState(OrderForm orderForm);
}
