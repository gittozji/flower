package me.zji.service;

import me.zji.entity.StaticTradeBalance;

import java.util.Map;

/**
 * 交易账号静态资金服务
 * Created by imyu on 2017/3/5.
 */
public interface StaticTradeBalanceService {
    /**
     * 资金存入
     * @param tradeAcco
     * @param moneyType
     * @param count
     * @return
     */
    Map income(String tradeAcco, String moneyType, Double count);

    /**
     * 资金支出
     * @param tradeAcco
     * @param moneyType
     * @param count
     * @return
     */
    Map expend(String tradeAcco, String moneyType, Double count);

    /**
     * 资金支出（认申购）
     * @param tradeAcco
     * @param moneyType
     * @param count
     * @return
     */
    Map expendEI(String tradeAcco, String moneyType, Double count);
}
