package me.zji.dao;

import me.zji.entity.StaticTradeBalance;

import java.util.Map;

/**
 * 交易账号静态资金 DAO
 * Created by imyu on 2017/3/5.
 */
public interface StaticTradeBalanceDao {
    void create(StaticTradeBalance staticTradeBalance);
    StaticTradeBalance queryByTradeAccoAndMoneyType(StaticTradeBalance staticTradeBalance);
    void update(StaticTradeBalance staticTradeBalance);
}
