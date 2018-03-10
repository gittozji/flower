package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.entity.*;
import me.zji.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 产品维护控制器
 * Created by imyu on 2017/3/1.
 */
@Controller
public class AdminMaintainController {
    @Autowired
    FlowerService flowerService;

    /**
     * View 产品信息维护首页
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/index.html")
    public String index() {
        return "/admin/maintain/index";
    }

    /**
     * View 查看产品页面
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/showpage.html")
    public ModelAndView showpage(ModelAndView modelAndView) {
        modelAndView.setViewName("/admin/maintain/showpage");
        modelAndView.addObject("data", flowerService.query());
        return modelAndView;
    }

    /**
     * View 新增产品页面
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/addpage.html")
    public String addpage() {
        return "/admin/maintain/addpage";
    }

    /**
     * Action 新增产品请求
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/add")
    @ResponseBody
    public Object add(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Flower flower = new Flower();
        flower.setName((String) param.get("name"));
        flower.setPrice(Float.parseFloat(param.get("price").toString()));
        flower.setCount(Integer.parseInt(param.get("count").toString()));
        flower.setFid((String) param.get("fid"));

        if (flowerService.queryByName(flower.getName()) != null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "已经存在该产品";
        } else {
            flowerService.create(flower);
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * Action 删除产品请求
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/delete")
    @ResponseBody
    public Object delete(@RequestBody Map param) {
        try {
            flowerService.deleteByName((String) param.get("name"));
        } catch (Exception e) {
            Map model = new HashMap();
            model.put("resultCode", CommonConstants.RESULT_FAILURE);
            model.put("errorInfo", "该产品已有订单，不允许删除");
            return model;
        }
        Map model = new HashMap();
        model.put("resultCode", CommonConstants.RESULT_SUCEESS);
        return model;
    }
}
