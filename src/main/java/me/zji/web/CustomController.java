package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.constants.OrderFormState;
import me.zji.constants.OrderFormStateMap;
import me.zji.dto.CustUser;
import me.zji.entity.*;
import me.zji.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 客户页面控制器（7*24小时渠道）
 * Created by imyu on 2017/3/15.
 */
@Controller
public class CustomController {
    @Autowired
    FlowerService flowerService;
    @Autowired
    OrderFormService orderFormService;
    @Autowired
    UserService userService;

    /**
     * View 个人资料页面
     * @return
     */
    @RequestMapping(value = "/custom/my.html")
    public ModelAndView my(ModelAndView modelAndView) {
        modelAndView.setViewName("/custom/my");
        CustUser user = (CustUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        modelAndView.addObject("user", userService.getUser(user.getId()));
        return modelAndView;
    }

    /**
     * Action 个人资料修改请求
     *
     * @return
     */
    @RequestMapping(value = "/custom/modify")
    @ResponseBody
    public Object modify(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        User user = userService.getUser(Long.parseLong(param.get("id").toString()));
        user.setNikename((String) param.get("nike"));

        UserDetail detail = new UserDetail();
        detail.setUserId(user.getId());
        detail.setAddress((String) param.get("address"));
        detail.setTel((String) param.get("tel"));

        userService.update(user, detail);

        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * View 鲜花下单页面
     * @return
     */
    @RequestMapping(value = "/custom/index.html")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("/custom/index");
        modelAndView.addObject("data", flowerService.query());
        return modelAndView;
    }

    /**
     * Action 下单请求
     *
     * @return
     */
    @RequestMapping(value = "/custom/buy")
    @ResponseBody
    public Object buy(@RequestBody Map param) {
        if (StringUtils.isEmpty(param.get("count"))) {
            Map model = new HashMap();
            model.put("resultCode", CommonConstants.RESULT_FAILURE);
            model.put("errorInfo", "请输入订购数量");
            return model;
        }

        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Flower flower = flowerService.queryByName((String) param.get("name"));
        int count = Integer.parseInt(param.get("count").toString());
        if (flower == null || flower.getCount() < count) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "鲜花库存不足";
        } else if (count <= 0) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "购买数量输入错误";
        } else {
            CustUser user = (CustUser) SecurityUtils.getSubject().getSession().getAttribute("user");
            OrderForm orderForm = new OrderForm();
            orderForm.setUserId(user.getId());
            orderForm.setFlowerId(flower.getId());
            orderForm.setCount(count);
            orderForm.setState(Integer.parseInt(param.get("state").toString()));

            // 产品数量减少
            flower.setCount(flower.getCount() - count);
            flowerService.updateCount(flower);

            // 订单入库
            orderFormService.create(orderForm);
        }

        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * View 鲜花订单查询页面
     * @return
     */
    @RequestMapping(value = "/custom/showpage.html")
    public ModelAndView showpage(ModelAndView modelAndView) {
        modelAndView.setViewName("/custom/showpage");
        CustUser user = (CustUser) SecurityUtils.getSubject().getSession().getAttribute("user");
        modelAndView.addObject("data", orderFormService.queryByUserId(user.getId()));
        modelAndView.addObject("map", OrderFormStateMap.map);
        return modelAndView;
    }

    /**
     * Action 订单取消
     *
     * @return
     */
    @RequestMapping(value = "/custom/cancel")
    @ResponseBody
    public Object cancel(@RequestBody Map param) {
        OrderForm orderForm = orderFormService.getOrderForm(Long.parseLong(param.get("id").toString()));
        Flower flower = flowerService.getFlower(orderForm.getFlowerId());

        int state = orderForm.getState();
        // 派送中的、已取消的、已结束的订单不允许再进行取消操作
        if (state >= 4) {
            Map model = new HashMap();
            model.put("resultCode", CommonConstants.RESULT_FAILURE);
            model.put("errorInfo", "订单状态不能进行取消");
            return model;
        }

        if (flower != null) {
            flower.setCount(flower.getCount() + orderForm.getCount());
            // 产品数量增加
            flowerService.updateCount(flower);
        }

        // 更新订单状态
        orderForm.setState(OrderFormState.CANCEL.getValue());
        orderFormService.updateState(orderForm);

        Map model = new HashMap();
        model.put("resultCode", CommonConstants.RESULT_SUCEESS);
        return model;
    }
}
