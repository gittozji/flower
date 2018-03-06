package me.zji.web;

import me.zji.constants.CommonConstants;
import me.zji.dao.StaticTradeBalanceDao;
import me.zji.dao.TradeAccoDao;
import me.zji.dto.AdminUser;
import me.zji.dto.CustUser;
import me.zji.entity.*;
import me.zji.security.UsernamePasswordUsertypeToken;
import me.zji.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 客户页面控制器（7*24小时渠道）
 * Created by imyu on 2017/3/15.
 */
@Controller
public class CustomController {
    @Autowired
    CustInfoService custInfoService;
    @Autowired
    DynamicSelectService dynamicSelectService;
    @Autowired
    PasswordService passwordService;
    @Autowired
    TradeAccoDao tradeAccoDao;
    @Autowired
    BuyService buyService;
    @Autowired
    StaticShareService staticShareService;
    @Autowired
    DynamicProductInfoService dynamicProductInfoService;
    @Autowired
    StaticTradeBalanceDao staticTradeBalanceDao;

    /**
     * View 管理员系统管理页面
     * @return
     */
    @RequestMapping(value = "/custom/index.html")
    public String user(@ModelAttribute("errorInfo") String errorInfo, Model model) {
        model.addAttribute("errorInfo", errorInfo);
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        CustUser custUser = (CustUser) session.getAttribute("user");

        CustInfo custInfo = custInfoService.queryByUsername(custUser.getUsername());
        model.addAttribute("custInfo", custInfo);
        if (custInfo == null) {
            return "/custom/index";
        }

        /**下拉框*/
        Map<String, Object> selectItemMap = new HashMap<String, Object>();
        selectItemMap.put("taCodeSelect", dynamicSelectService.selectTaCode());
        selectItemMap.put("taAccoSelect", dynamicSelectService.selectTaAccoByCustNo(custInfo.getCustNo()));
        selectItemMap.put("tradeAccoSelect", dynamicSelectService.selectTradeAccoByCustNo(custInfo.getCustNo()));
        selectItemMap.put("offerProductSelect", dynamicSelectService.selectProductByStatus("0"));
        selectItemMap.put("applyProductSelect", dynamicSelectService.selectProductByStatus("1"));
        selectItemMap.put("productSelect", dynamicSelectService.selectProductByStatus("-1"));
        model.addAttribute("selectItemMap", selectItemMap);

        return "/custom/index";
    }

    /**
     * Action 绑定客户编号
     * @return
     */
    @RequestMapping(value = "/custom/dobind")
    public String doLogin(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        String viewName = "redirect:/custom/index.html";
        String errorInfo = null;

        String custNo = httpServletRequest.getParameter("custNo");
        String tradeAcco = httpServletRequest.getParameter("tradeAcco");
        String password = httpServletRequest.getParameter("password");

        /**判断交易账号是否对应正确的客户编号*/
        TradeAcco temp = tradeAccoDao.queryByTradeAcco(tradeAcco);
        if (temp == null || !custNo.equals(temp.getCustNo())) {
            errorInfo = "客户编号或交易账号不正确";
        }

        /**判断密码*/
        Map map = passwordService.verificateTradeAccoPassword(tradeAcco,password);
        if (Integer.valueOf(map.get("resultCode").toString()) == CommonConstants.RESULT_FAILURE) {
            errorInfo = (String) map.get("errorInfo");
        }

        if(errorInfo != null) {
            redirectAttributes.addFlashAttribute("errorInfo",errorInfo);
        } else {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            CustUser custUser = (CustUser) session.getAttribute("user");
            CustInfo custInfo = custInfoService.queryByCustNo(custNo);
            custInfo.setUserName(custUser.getUsername());
            custInfoService.update(custInfo);
        }
        return viewName;
    }

    /**
     * Action 认购提交
     * @return
     */
    @RequestMapping(value = "/custom/addoffertobuy")
    @ResponseBody
    public Object addOfferToBuy(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "认购成功";
        Map data = new HashMap();
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
     * Action 申购提交
     * @return
     */
    @RequestMapping(value = "/custom/addapplytobuy")
    @ResponseBody
    public Object addApplyToBuy(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "申购成功";
        Map data = new HashMap();
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
     * Action 赎回提交
     * @return
     */
    @RequestMapping(value = "/custom/addatonefor")
    @ResponseBody
    public Object addAtoneFor(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "赎回成功";
        Map data = new HashMap();
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

    /**
     * Action 静态份额查询
     * @return
     */
    @RequestMapping(value = "/custom/searchshare")
    @ResponseBody
    public Object searchShare(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "查询成功";

        StaticShare staticShare = new StaticShare();
        staticShare.setProductCode((String) params.get("productCode"));
        staticShare.setTaAcco((String) params.get("taAcco"));
        staticShare = staticShareService.queryByCodeAndAcco(staticShare);

        if (staticShare == null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "未找到记录";
        }

        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        model.put("staticShare", staticShare);
        return model;
    }

    /**
     * Action 静态份额查询
     * @return
     */
    @RequestMapping(value = "/custom/searchstnav")
    @ResponseBody
    public Object searchStnav(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "查询成功";

        DynamicProductInfo dynamicProductInfo = dynamicProductInfoService.queryByProductCode((String) params.get("productCode"));

        if (dynamicProductInfo == null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "未找到记录";
        }

        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        model.put("dynamicProductInfo", dynamicProductInfo);
        return model;
    }

    /**
     * Action 静态资金查询
     * @return
     */
    @RequestMapping(value = "/custom/searchbalance")
    @ResponseBody
    public Object searchBalance(@RequestBody Map params) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "查询成功";

        StaticTradeBalance staticTradeBalance = new StaticTradeBalance();
        staticTradeBalance.setTradeAcco((String) params.get("tradeAcco"));
        staticTradeBalance.setMoneyType((String) params.get("moneyType"));
        staticTradeBalance = staticTradeBalanceDao.queryByTradeAccoAndMoneyType(staticTradeBalance);

        if (staticTradeBalance == null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "未找到记录";
        }

        Map model = new HashMap();
        model.put("resultCode", resultCode);
        model.put("errorInfo", errorInfo);
        model.put("staticTradeBalance", staticTradeBalance);
        return model;
    }
}
