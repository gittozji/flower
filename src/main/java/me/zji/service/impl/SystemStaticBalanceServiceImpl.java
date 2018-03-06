package me.zji.service.impl;

import me.zji.constants.CommonConstants;
import me.zji.dao.SystemStaticBalanceDao;
import me.zji.entity.StaticTradeBalance;
import me.zji.entity.SystemStaticBalance;
import me.zji.service.SystemStaticBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统静态资金信息服务
 * Created by imyu on 2017/3/5.
 */
@Service
public class SystemStaticBalanceServiceImpl implements SystemStaticBalanceService {
    @Autowired
    SystemStaticBalanceDao systemStaticBalanceDao;
    public Map income(String bankAcco, String moneyType, Double count) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "存入成功";
        /** 资金存入逻辑 */
        {
            // 查询记录
            SystemStaticBalance systemStaticBalance = systemStaticBalanceDao.queryByBankAccoAndMoneyType(new SystemStaticBalance(bankAcco, moneyType));
            // 没有该记录，则创建
            if(systemStaticBalance == null) {
                systemStaticBalance = new SystemStaticBalance(bankAcco, moneyType, count);
                systemStaticBalanceDao.create(systemStaticBalance);
            } else {
                systemStaticBalance.setBalance(systemStaticBalance.getBalance() + count);
                systemStaticBalanceDao.update(systemStaticBalance);
            }
        }
        Map result = new HashMap();
        result.put("resultCode", resultCode);
        result.put("errorInfo", errorInfo);
        return result;
    }

    public Map expend(String bankAcco, String moneyType, Double count) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "支出成功";
        /** 资金支出逻辑 */
        {
            // 查询记录
            SystemStaticBalance systemStaticBalance = systemStaticBalanceDao.queryByBankAccoAndMoneyType(new SystemStaticBalance(bankAcco, moneyType));
            // 没有该记录，则创建
            if(systemStaticBalance == null) {
                systemStaticBalance = new SystemStaticBalance(bankAcco, moneyType, Double.valueOf(0.00));
                systemStaticBalanceDao.create(systemStaticBalance);
                resultCode = CommonConstants.RESULT_FAILURE;
                errorInfo = "资金不足";
            } else {
                if(systemStaticBalance.getBalance() > count) {
                    systemStaticBalance.setBalance(systemStaticBalance.getBalance() - count);
                    systemStaticBalanceDao.update(systemStaticBalance);
                } else {
                    resultCode = CommonConstants.RESULT_FAILURE;
                    errorInfo = "资金不足";
                }
            }
        }
        Map result = new HashMap();
        result.put("resultCode", resultCode);
        result.put("errorInfo", errorInfo);
        return result;
    }
}
