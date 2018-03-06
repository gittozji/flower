package me.zji.service;

import java.util.Map;

/**
 * 密码服务
 * Created by imyu on 2017/3/6.
 */
public interface PasswordService {
    /**
     * 校验校验账号密码
     * @param tradeAcco 交易账号
     * @param password 未加密的密码
     * @return
     */
    Map verificateTradeAccoPassword(String tradeAcco, String password);
}
