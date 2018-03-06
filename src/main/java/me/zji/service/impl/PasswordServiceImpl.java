package me.zji.service.impl;

import me.zji.constants.CommonConstants;
import me.zji.dao.TradeAccoDao;
import me.zji.entity.TradeAcco;
import me.zji.security.PasswordUtils;
import me.zji.service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 密码服务
 * Created by imyu on 2017/3/6.
 */
@Service
public class PasswordServiceImpl implements PasswordService {
    @Autowired
    TradeAccoDao tradeAccoDao;
    public Map verificateTradeAccoPassword(String tradeAcco, String password) {
        int resultCode = CommonConstants.RESULT_SUCEESS;
        String errorInfo = "交易通过";
        TradeAcco tradeAccoEntity = tradeAccoDao.queryByTradeAcco(tradeAcco);
        if (tradeAccoEntity == null) {
            resultCode = CommonConstants.RESULT_FAILURE;
            errorInfo = "交易账号不存在";
        } else {
            if (password != null) {
                try {
                    if (!PasswordUtils.encryptPassword(password).equals(tradeAccoEntity.getPassword())) {
                        resultCode = CommonConstants.RESULT_FAILURE;
                        errorInfo = "密码不正确";
                    }
                } catch (Exception e){
                    resultCode = CommonConstants.RESULT_FAILURE;
                    errorInfo = "密码校验服务出错";
                }
            }
        }
        Map result = new HashMap();
        result.put("resultCode", resultCode);
        result.put("errorInfo", errorInfo);
        return result;
    }
}
