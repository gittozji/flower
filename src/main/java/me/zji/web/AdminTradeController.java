package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.entity.CustInfo;
import me.zji.entity.ProductInfo;
import me.zji.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 业务交易控制器
 * Created by imyu on 2017/3/4.
 */
@Controller
public class AdminTradeController {

    @Autowired
    DynamicSelectService dynamicSelectService;
    @Autowired
    CustInfoService custInfoService;
    @Autowired
    CustUserService custUserService;
    @Autowired
    DealProcessService dealProcessService;
    @Autowired
    StaticTradeBalanceService staticTradeBalanceService;
    @Autowired
    SystemStaticBalanceService systemStaticBalanceService;
    @Autowired
    PasswordService passwordService;
    @Autowired
    BuyService buyService;

    /**
     * View 业务交易管理首页
     * @return
     */
    @RequestMapping(value = "/admin/trade/index.html")
    public String index() {
        return "/admin/trade/index";
    }

    /**
     * View 开户
     * @return
     */
    @RequestMapping(value = "/admin/trade/accoopen.html")
    public String accoOpen(Model model) {
        Map<String, Object> selectItemMap = new HashMap<String, Object>();
        selectItemMap.put("taCodeSelect", dynamicSelectService.selectTaCode());

        model.addAttribute("selectItemMap", selectItemMap);
        return "/admin/trade/accoopen";
    }

    /**
     * Action 开户
     * @return
     */
    @RequestMapping(value = "/admin/trade/addaccoopen")
    @ResponseBody
    public Object addAccoOpen(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = null;
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 业务交易逻辑 */
        {
            if (custInfoService.queryByIdentityNo((String) params.get("identityNo")) != null) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "该证件号码已经开过户";
            } else {
                data = custInfoService.create(params);
                data.put("requestNo",new Date().getTime());
            }
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        model.put("data", data);
        return model;
    }

    /**
     * View 资金存入
     * @return
     */
    @RequestMapping(value = "/admin/trade/income.html")
    public String income(Model model) {
        Map<String, Object> selectItemMap = new HashMap<String, Object>();
        selectItemMap.put("bankAccoSelect", dynamicSelectService.selectBankAccoInfo());

        model.addAttribute("selectItemMap", selectItemMap);
        return "/admin/trade/income";
    }

    /**
     * Action 资金存入
     * @return
     */
    @RequestMapping(value = "/admin/trade/addincome")
    @ResponseBody
    public Object addIncome(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "存入成功";
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 密码校验 */
        {
            Map map = passwordService.verificateTradeAccoPassword((String) params.get("tradeAcco"),(String) params.get("password"));
            if (Integer.valueOf(map.get("resultCode").toString()) == CommonConstants.RESULT_FAILURE) {
                return map;
            }
        }
        /** 业务交易逻辑 */
        {
            Map result1 = systemStaticBalanceService.income((String) params.get("bankAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
            if (Integer.valueOf(result1.get("resultCode").toString()) == CommonConstants.RESULT_SUCEESS) {
                Map result2 = staticTradeBalanceService.income((String) params.get("tradeAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
                if(Integer.valueOf(result2.get("resultCode").toString()) == CommonConstants.RESULT_SUCEESS) {
                    data.put("tradeAcco", params.get("tradeAcco"));
                    data.put("bankAcco", params.get("bankAcco"));
                    data.put("requestNo",new Date().getTime());
                } else {
                    systemStaticBalanceService.expend((String) params.get("bankAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
                    resultCode = CommonConstants.RESULT_FAILURE;
                    errorInfo = (String) result2.get("errorInfo");
                }
            } else {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = (String) result1.get("errorInfo");
            }
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        model.put("data", data);
        return model;
    }


    /**
     * View 资金支出
     * @return
     */
    @RequestMapping(value = "/admin/trade/expend.html")
    public String expend(Model model) {
        Map<String, Object> selectItemMap = new HashMap<String, Object>();
        selectItemMap.put("bankAccoSelect", dynamicSelectService.selectBankAccoInfo());

        model.addAttribute("selectItemMap", selectItemMap);
        return "/admin/trade/expend";
    }

    /**
     * Action 资金支出
     * @return
     */
    @RequestMapping(value = "/admin/trade/addexpend")
    @ResponseBody
    public Object addExpend(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "支出失败";
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 密码校验 */
        {
            System.out.println("haaaa:"+passwordService.hashCode());
            Map map = passwordService.verificateTradeAccoPassword((String) params.get("tradeAcco"),(String) params.get("password"));
            if (Integer.valueOf(map.get("resultCode").toString()) == CommonConstants.RESULT_FAILURE) {
                return map;
            }
        }
        /** 业务交易逻辑 */
        {
            Map result1 = staticTradeBalanceService.expend((String) params.get("tradeAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
            if (Integer.valueOf(result1.get("resultCode").toString()) == CommonConstants.RESULT_SUCEESS) {
                Map result2 = systemStaticBalanceService.expend((String) params.get("bankAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
                if (Integer.valueOf(result2.get("resultCode").toString()) == CommonConstants.RESULT_SUCEESS) {
                    data.put("tradeAcco", params.get("tradeAcco"));
                    data.put("bankAcco", params.get("bankAcco"));
                    data.put("requestNo", new Date().getTime());
                } else {
                    staticTradeBalanceService.income((String) params.get("tradeAcco"), (String) params.get("moneyType"), Double.valueOf(params.get("count").toString()));
                    resultCode = CommonConstants.RESULT_FAILURE;
                    errorInfo = "系统清算账户余额不足";
                }
            } else {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = (String) result1.get("errorInfo");
            }
        }
        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        model.put("data", data);
        return model;
    }

    /**
     * View 认购
     * @return
     */
    @RequestMapping(value = "/admin/trade/offertobuy.html")
    public String offerToBuy() {
        return "/admin/trade/offertobuy";
    }

    /**
     * Action 认购搜索
     * @return
     */
    @RequestMapping(value = "/admin/trade/offertobuysearch")
    @ResponseBody
    public Object offerToBuySearch(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "查询成功";
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 密码校验 */
        {
            Map map = passwordService.verificateTradeAccoPassword((String) params.get("tradeAcco"),(String) params.get("password"));
            if (Integer.valueOf(map.get("resultCode").toString()) == CommonConstants.RESULT_FAILURE) {
                return map;
            }
        }
        /** 业务交易逻辑 */
        {
            data = buyService.queryDataByTradeAccoForOffer((String) params.get("tradeAcco"));
        }
        Map model = new HashMap();
        model.put("resultCode", data.get("resultCode"));
        model.put("errorInfo", data.get("errorInfo"));
        model.put("data", data);
        return model;
    }

    /**
     * Action 认购提交
     * @return
     */
    @RequestMapping(value = "/admin/trade/addoffertobuy")
    @ResponseBody
    public Object addOfferToBuy(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "支出成功";
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 密码校验 */
        {
            Map map = passwordService.verificateTradeAccoPassword((String) params.get("tradeAcco"),(String) params.get("password"));
            if (Integer.valueOf(map.get("resultCode").toString()) == CommonConstants.RESULT_FAILURE) {
                return map;
            }
        }
        /** 业务交易逻辑 */
        {
            data = buyService.offerToBuy(params);
        }
        Map model = new HashMap();
        model.put("resultCode", data.get("resultCode"));
        model.put("errorInfo", data.get("errorInfo"));
        model.put("data", data);
        return model;
    }

    /**
     * View 申购
     * @return
     */
    @RequestMapping(value = "/admin/trade/applytobuy.html")
    public String applyToBuy() {
        return "/admin/trade/applytobuy";
    }

    /**
     * Action 申购搜索
     * @return
     */
    @RequestMapping(value = "/admin/trade/applytobuysearch")
    @ResponseBody
    public Object applyToBuySearch(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "查询成功";
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 密码校验 */
        {
            Map map = passwordService.verificateTradeAccoPassword((String) params.get("tradeAcco"),(String) params.get("password"));
            if (Integer.valueOf(map.get("resultCode").toString()) == CommonConstants.RESULT_FAILURE) {
                return map;
            }
        }
        /** 业务交易逻辑 */
        {
            data = buyService.queryDataByTradeAccoForApply((String) params.get("tradeAcco"));
        }
        Map model = new HashMap();
        model.put("resultCode", data.get("resultCode"));
        model.put("errorInfo", data.get("errorInfo"));
        model.put("data", data);
        return model;
    }

    /**
     * Action 申购提交
     * @return
     */
    @RequestMapping(value = "/admin/trade/addapplytobuy")
    @ResponseBody
    public Object addApplyToBuy(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "支出成功";
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 密码校验 */
        {
            Map map = passwordService.verificateTradeAccoPassword((String) params.get("tradeAcco"),(String) params.get("password"));
            if (Integer.valueOf(map.get("resultCode").toString()) == CommonConstants.RESULT_FAILURE) {
                return map;
            }
        }
        /** 业务交易逻辑 */
        {
            data = buyService.applyToBuy(params);
        }
        Map model = new HashMap();
        model.put("resultCode", data.get("resultCode"));
        model.put("errorInfo", data.get("errorInfo"));
        model.put("data", data);
        return model;
    }

    /**
     * View 赎回
     * @return
     */
    @RequestMapping(value = "/admin/trade/atonefor.html")
    public String atoneFor() {
        return "/admin/trade/atonefor";
    }

    /**
     * Action 赎回搜索
     * @return
     */
    @RequestMapping(value = "/admin/trade/atoneforsearch")
    @ResponseBody
    public Object atoneForSearch(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "查询成功";
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 密码校验 */
        {
            Map map = passwordService.verificateTradeAccoPassword((String) params.get("tradeAcco"),(String) params.get("password"));
            if (Integer.valueOf(map.get("resultCode").toString()) == CommonConstants.RESULT_FAILURE) {
                return map;
            }
        }
        /** 业务交易逻辑 */
        {
            data = buyService.queryDataByTradeAccoForAtone((String) params.get("tradeAcco"));
        }
        Map model = new HashMap();
        model.put("resultCode", data.get("resultCode"));
        model.put("errorInfo", data.get("errorInfo"));
        model.put("data", data);
        return model;
    }

    /**
     * Action 赎回提交
     * @return
     */
    @RequestMapping(value = "/admin/trade/addatonefor")
    @ResponseBody
    public Object addAtoneFor(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "支出成功";
        Map data = new HashMap();
        /** 校验交易时间 */
        {
            if (!dealProcessService.isTradeTime()) {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "现在不是柜台交易时间";
                Map model = new HashMap();
                model.put("resultCode", resultCode);
                model.put("errorInfo", errorInfo);
                return model;
            }
        }
        /** 密码校验 */
        {
            Map map = passwordService.verificateTradeAccoPassword((String) params.get("tradeAcco"),(String) params.get("password"));
            if (Integer.valueOf(map.get("resultCode").toString()) == CommonConstants.RESULT_FAILURE) {
                return map;
            }
        }
        /** 业务交易逻辑 */
        {
            data = buyService.atoneFor(params);
        }
        Map model = new HashMap();
        model.put("resultCode", data.get("resultCode"));
        model.put("errorInfo", data.get("errorInfo"));
        model.put("data", data);
        return model;
    }



}
