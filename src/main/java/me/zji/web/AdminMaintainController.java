package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.entity.*;
import me.zji.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    DayService dayService;
    @Autowired
    TaService taService;
    @Autowired
    NetStationService netStationService;
    @Autowired
    ProductInfoService productInfoService;
    @Autowired
    DynamicSelectService dynamicSelectService;
    @Autowired
    BankInfoService bankInfoService;

    @Autowired
    FlowerService flowerService;

    /**
     * View 流程控制管理首页
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/index.html")
    public String index() {
        return "/admin/maintain/index";
    }

    /**
     * View 工作日设置
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/day.html")
    public String day() {
        return "admin/maintain/showpage";
    }

    /**
     * Action 添加工作日
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/addday")
    @ResponseBody
    public Object addDay(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;

        Day day = new Day();
        day.setDay((String) param.get("day"));
        String workFlag = param.get("workFlag").toString();
        if ("true".equals(workFlag)) {
            day.setWorkFlag(0);
        } else {
            day.setWorkFlag(1);
        }
        dayService.createOrUpdate(day);
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * View 产品信息设置
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/productinfo.html")
    public String productInfo(Model model) {
        Map<String, Object> selectItemMap = new HashMap<String, Object>();
        selectItemMap.put("taCodeSelect", dynamicSelectService.selectTaCode());
        selectItemMap.put("bankAccoSelect", dynamicSelectService.selectBankAccoInfo());

        model.addAttribute("selectItemMap", selectItemMap);
        return "/admin/maintain/productinfo";
    }

    /**
     * Action 添加产品信息
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/addproductinfo")
    @ResponseBody
    public Object addProductInfo(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;

        ProductInfo productInfo = new ProductInfo();
        productInfo.setTaCode((String) param.get("taCode"));
        productInfo.setBankAcco((String) param.get("bankAcco"));
        productInfo.setChargeType((String) param.get("chargeType"));
        productInfo.setDividendMethod((String) param.get("dividendMethod"));
        productInfo.setInvestDirection((String) param.get("investDirection"));
        productInfo.setInvestRegion((String) param.get("investRegion"));
        productInfo.setIssueStartDate((String) param.get("issueStartDate"));
        productInfo.setManageRatio(Float.valueOf(param.get("manageRatio").toString()));
        productInfo.setMoneyType((String) param.get("moneyType"));
        productInfo.setProductOperation((String) param.get("productOperation"));
        productInfo.setProductStatus((String) param.get("productStatus"));
        productInfo.setProductRiskLevel((String) param.get("productRiskLevel"));
        productInfo.setProductCategory((String) param.get("productCategory"));
        productInfo.setProductCode((String) param.get("productCode"));
        productInfo.setProductName((String) param.get("productName"));


        if (productInfoService.queryByProductCode(productInfo.getProductCode()) != null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "已经存在该产品代码";
        } else {
            productInfoService.create(productInfo);
        }

        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * View TA信息
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/ta.html")
    public String ta() {
        return "/admin/maintain/ta";
    }

    /**
     * Action 添加TA信息
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/addta")
    @ResponseBody
    public Object addTa(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;

        Ta ta = new Ta();
        ta.setTaCode((String) param.get("code"));
        ta.setTaName((String) param.get("name"));
        if (taService.queryByCode(ta.getTaCode()) != null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "已经存在该TA代码";
        } else {
            taService.create(ta);
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
    }

    /**
     * View 网点设置
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/netstation.html")
    public String netStation() {
        return "/admin/maintain/netstation";
    }

    /**
     * Action 添加网点信息
     *
     * @return
     */
    @RequestMapping(value = "/admin/maintain/addnetstation")
    @ResponseBody
    public Object addNetStation(@RequestBody Map param) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;

        NetStation netStation = new NetStation();
        netStation.setNetNo((String) param.get("netNo"));
        netStation.setNetName((String) param.get("netName"));
        netStation.setAddress((String) param.get("address"));
        if (netStationService.queryByNetNo(netStation.getNetNo()) != null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "已经存在该网点编号";
        } else {
            netStationService.create(netStation);
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        return model;
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


}
