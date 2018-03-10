package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.constants.OrderFormState;
import me.zji.constants.OrderFormStateMap;
import me.zji.entity.OrderForm;
import me.zji.service.FlowerService;
import me.zji.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程控制管理控制器
 * Created by imyu on 2017/2/25.
 */
@Controller
public class AdminProcessController {
    @Autowired
    OrderFormService orderFormService;
    @Autowired
    FlowerService flowerService;
    /**
     * View 派送管理页面
     * @return
     */
    @RequestMapping(value = "/admin/process/index.html")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/process/index");
        modelAndView.addObject("data", orderFormService.query());
        modelAndView.addObject("map", OrderFormStateMap.map);
        return modelAndView;
    }

    /**
     * Action 置派送
     *
     * @return
     */
    @RequestMapping(value = "/admin/process/dispatch")
    @ResponseBody
    public Object dispatch(@RequestBody Map param) {
        OrderForm orderForm = orderFormService.getOrderForm(Long.parseLong(param.get("id").toString()));
        int state = orderForm.getState();
        // 只有现金未付款、线上未付款、线上已付款状态才可派送花朵
        if (state >= 4) {
            Map model = new HashMap();
            model.put("resultCode", CommonConstants.RESULT_FAILURE);
            model.put("errorInfo", "订单状态不能进行置派送");
            return model;
        }

        // 更新订单状态
        if (state == 0) {
            orderForm.setState(OrderFormState.DISPATCHING_NON_PAY.getValue());
        } else {
            orderForm.setState(OrderFormState.DISPATCHING.getValue());
        }
        orderFormService.updateState(orderForm);

        Map model = new HashMap();
        model.put("resultCode", CommonConstants.RESULT_SUCEESS);
        return model;
    }

    /**
     * Action 置派送
     *
     * @return
     */
    @RequestMapping(value = "/admin/process/finish")
    @ResponseBody
    public Object finish(@RequestBody Map param) {
        OrderForm orderForm = orderFormService.getOrderForm(Long.parseLong(param.get("id").toString()));
        int state = orderForm.getState();
        // 只有派送状态才可置派送完成
        if (state != 4 && state != 40) {
            Map model = new HashMap();
            model.put("resultCode", CommonConstants.RESULT_FAILURE);
            model.put("errorInfo", "订单状态不能进行置完成");
            return model;
        }

        // 更新订单状态
        orderForm.setState(OrderFormState.FINISH.getValue());
        orderFormService.updateState(orderForm);

        Map model = new HashMap();
        model.put("resultCode", CommonConstants.RESULT_SUCEESS);
        return model;
    }
}
