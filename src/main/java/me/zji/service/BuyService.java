package me.zji.service;

import java.util.Map;

/**
 * 认申购服务
 * Created by imyu on 2017/3/6.
 */
public interface BuyService {
    /**
     * 认购界面查询返填
     * @param tradeAcco
     * @return
     */
    Map queryDataByTradeAccoForOffer(String tradeAcco);

    /**
     * 申购界面查询返填
     * @param tradeAcco
     * @return
     */
    Map queryDataByTradeAccoForApply(String tradeAcco);

    /**
     * 赎回信息查询返填
     * @param tradeAcco
     * @return
     */
    Map queryDataByTradeAccoForAtone(String tradeAcco);

    /**
     * 认购
     * @param param
     * @return
     */
    Map offerToBuy(Map param);

    /**
     * 申购
     * @param param
     * @return
     */
    Map applyToBuy(Map param);

    /**
     * 赎回
     * @param param
     * @return
     */
    Map atoneFor(Map param);
}
