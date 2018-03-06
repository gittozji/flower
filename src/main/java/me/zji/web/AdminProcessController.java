package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.dao.DayDao;
import me.zji.entity.Day;
import me.zji.entity.DealProcess;
import me.zji.service.DealProcessService;
import me.zji.service.TaCommunicationService;
import me.zji.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.krb5.internal.PAData;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流程控制管理控制器
 * Created by imyu on 2017/2/25.
 */
@Controller
public class AdminProcessController {
    @Autowired
    DealProcessService dealProcessService;
    @Autowired
    DayDao dayDao;
    @Autowired
    TaCommunicationService taCommunicationService;
    /**
     * View 流程控制管理首页
     * @return
     */
    @RequestMapping(value = "/admin/process/index.html")
    public String index() {
        return "/admin/process/index";
    }/**
     * View 流程控制管理指导页面
     * @return
     */
    @RequestMapping(value = "/admin/process/guide.html")
    public String guide() {
        return "/admin/process/guide";
    }

    /**
     * View 流程控制操作页面
     * @return
     */
    @RequestMapping(value = "/admin/process/edit.html")
    public String edit(Model model) {
        Map<String, DealProcess> dealProcessMap = DealProcess.listToMap(dealProcessService.getCurrentDealProcess());
        model.addAttribute("dealProcessMap", dealProcessMap);
        return "/admin/process/edit";
    }

    /**
     * 流程操作
     * @return
     */
    @RequestMapping(value = "/admin/process/doedit")
    @ResponseBody
    public Object doEdit(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map model = new HashMap();

        DealProcess dealProcess = new DealProcess();
        dealProcess.setProcedurCode((String) param.get("procedurCode"));

        /** 日初始化特殊点 */
        if ("dayinit".equals(dealProcess.getProcedurCode())) {
            Day day = dayDao.queryByDay(DateUtil.getNowDate());
            if (day != null && day.getWorkFlag() == 1) {
                model.put("resultCode", CommonConstants.RESULT_FAILURE);
                model.put("errorInfo", "今天是休息日");
                return model;
            }
            dealProcessService.dayInit();
        } else {
            if ("exprequest".equals(dealProcess.getProcedurCode())) { // 导出申请数据
                if (!taCommunicationService.taOutput()) {
                    model.put("resultCode", CommonConstants.RESULT_FAILURE);
                    model.put("errorInfo", "导出申请数据失败");
                    return model;
                }
            } else if ("importdata".equals(dealProcess.getProcedurCode())) { // 导入确认数据
                if (!taCommunicationService.importData()) {
                    model.put("resultCode", CommonConstants.RESULT_FAILURE);
                    model.put("errorInfo", "导入确认数据失败");
                    return model;
                }
            } else if ("receivemarket".equals(dealProcess.getProcedurCode())) { // 接收行情
                if (!taCommunicationService.receiveMarket()) {
                    model.put("resultCode", CommonConstants.RESULT_FAILURE);
                    model.put("errorInfo", "接收行情失败");
                    return model;
                }
            } else if ("checkdata".equals(dealProcess.getProcedurCode())) { // 交易预处理
                if (!taCommunicationService.checkData()) {
                    model.put("resultCode", CommonConstants.RESULT_FAILURE);
                    model.put("errorInfo", "交易预处理失败");
                    return model;
                }
            }
            /** 其他 */
            dealProcessService.update(dealProcess);

        }

        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }
}
