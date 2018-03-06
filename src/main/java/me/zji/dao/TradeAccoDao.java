package me.zji.dao;

import me.zji.entity.TradeAcco;

import java.util.List;

/**
 * 交易账号 DAO
 * Created by imyu on 2017/3/4.
 */
public interface TradeAccoDao {
    TradeAcco queryByTradeAcco(String tradeAcco);
    List<TradeAcco> queryByCustNo(String custNo);
}
