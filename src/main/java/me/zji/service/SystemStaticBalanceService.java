package me.zji.service;

import me.zji.entity.SystemStaticBalance;

import java.util.Map;

/**
 * 系统静态资金信息服务
 * Created by imyu on 2017/3/5.
 */
public interface SystemStaticBalanceService {
    Map income(String bankAcco, String moneyType, Double count);
    Map expend(String bankAcco, String moneyType, Double count);
}
