package me.zji.service.impl;

import me.zji.dao.OrderFormDao;
import me.zji.entity.OrderForm;
import me.zji.entity.User;
import me.zji.service.FlowerService;
import me.zji.service.OrderFormService;
import me.zji.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderFormImpl implements OrderFormService {
    @Autowired
    OrderFormDao orderFormDao;
    @Autowired
    UserService userService;
    @Autowired
    FlowerService flowerService;
    public void create(OrderForm orderForm) {
        orderFormDao.create(orderForm);
    }
    public List<OrderForm> queryByUserId(Long id) {
        List<OrderForm> list = orderFormDao.selectByUserId(id);
        list = list == null ? Collections.EMPTY_LIST : list;
        User user = userService.getUser(id);
        for (OrderForm orderForm: list) {
            orderForm.setUser(user);
            orderForm.setFlower(flowerService.getFlower(orderForm.getFlowerId()));
        }
        return list;
    }

    public List<OrderForm> query() {
        List<OrderForm> list = orderFormDao.selectAll();
        list = list == null ? Collections.EMPTY_LIST : list;
        for (OrderForm orderForm: list) {
            orderForm.setUser(userService.getUser(orderForm.getUserId()));
            orderForm.setFlower(flowerService.getFlower(orderForm.getFlowerId()));
        }
        return list;
    }

    public OrderForm getOrderForm(Long id) {
        return orderFormDao.select(id);
    }

    public void updateState(OrderForm orderForm) {
        orderFormDao.updateState(orderForm);
    }
}
