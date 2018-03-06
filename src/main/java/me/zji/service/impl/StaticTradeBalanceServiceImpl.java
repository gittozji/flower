package me.zji.service.impl;

import me.zji.constants.CommonConstants;
import me.zji.dao.StaticTradeBalanceDao;
import me.zji.entity.StaticTradeBalance;
import me.zji.service.StaticTradeBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 交易账号静态资金服务
 * Created by imyu on 2017/3/5.
 */
@Service
public class StaticTradeBalanceServiceImpl implements StaticTradeBalanceService {
    @Autowired
    StaticTradeBalanceDao staticTradeBalanceDao;
    public Map income(String tradeAcco, String moneyType, Double count) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "存入成功";
        /** 资金存入逻辑 */
        {
            // 查询记录
            StaticTradeBalance staticTradeBalance = staticTradeBalanceDao.queryByTradeAccoAndMoneyType(new StaticTradeBalance(tradeAcco, moneyType));
            // 没有该记录，则创建
            if(staticTradeBalance == null) {
                staticTradeBalance = new StaticTradeBalance(tradeAcco, moneyType, Double.valueOf(0.00), Double.valueOf(0.00), Double.valueOf(0.00));
                staticTradeBalanceDao.create(staticTradeBalance);
            }
            staticTradeBalance.setBalance(staticTradeBalance.getBalance() + count);
            staticTradeBalance.setEnBalance(staticTradeBalance.getEnBalance() + count);
            staticTradeBalanceDao.update(staticTradeBalance);
        }
        Map result = new HashMap();
        result.put("resultCode", resultCode);
        result.put("errorInfo", errorInfo);
        return result;
    }

    public Map expend(String tradeAcco, String moneyType, Double count) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "支出成功";
        /** 资金支出逻辑 */
        {
            // 查询记录
            StaticTradeBalance staticTradeBalance = staticTradeBalanceDao.queryByTradeAccoAndMoneyType(new StaticTradeBalance(tradeAcco, moneyType));
            // 没有该记录，则创建
            if(staticTradeBalance == null) {
                staticTradeBalance = new StaticTradeBalance(tradeAcco, moneyType, Double.valueOf(0.00), Double.valueOf(0.00), Double.valueOf(0.00));
                staticTradeBalanceDao.create(staticTradeBalance);
            }
            if (staticTradeBalance.getEnBalance() > count) {
                staticTradeBalance.setBalance(staticTradeBalance.getBalance() - count);
                staticTradeBalance.setEnBalance(staticTradeBalance.getEnBalance() - count);
                staticTradeBalanceDao.update(staticTradeBalance);
            } else {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "资金不足";
            }
        }
        Map result = new HashMap();
        result.put("resultCode", resultCode);
        result.put("errorInfo", errorInfo);
        return result;
    }

    public Map expendEI(String tradeAcco, String moneyType, Double count) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "支出成功";
        /** 资金支出逻辑 */
        {
            // 查询记录
            StaticTradeBalance staticTradeBalance = staticTradeBalanceDao.queryByTradeAccoAndMoneyType(new StaticTradeBalance(tradeAcco, moneyType));
            // 没有该记录，则创建
            if(staticTradeBalance == null) {
                staticTradeBalance = new StaticTradeBalance(tradeAcco, moneyType, Double.valueOf(0.00), Double.valueOf(0.00), Double.valueOf(0.00));
                staticTradeBalanceDao.create(staticTradeBalance);
            }
            if (staticTradeBalance.getEnBalance() > count) {
                staticTradeBalance.setImBalance(staticTradeBalance.getImBalance() + count);
                staticTradeBalance.setEnBalance(staticTradeBalance.getEnBalance() - count);
                staticTradeBalanceDao.update(staticTradeBalance);
            } else {
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "资金不足";
            }
        }
        Map result = new HashMap();
        result.put("resultCode", resultCode);
        result.put("errorInfo", errorInfo);
        return result;
    }
}
