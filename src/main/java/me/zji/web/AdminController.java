package me.zji.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员控制器
 * Created by imyu on 2017/2/18.
 */
@Controller
public class AdminController {
    /**
     * View 管理员首页
     * @return
     */
    @RequestMapping(value = "/admin/index.html")
    public String admin(){
        return "/admin/index";
    }
}
